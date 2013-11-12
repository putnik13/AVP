package com.atanor.vrecorder.domain.facades;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.atanor.vrecorder.events.CreateAndSaveSnapshotEvent;
import com.atanor.vrecorder.services.RecordingDataService;
import com.atanor.vrecorder.shared.Constants;
import com.atanor.vrecorder.util.ImageDecoder;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class PlayerFacadeVlcjImpl implements PlayerFacade {

	private final String outputFolder;
	private final String mediaResourceLocation;
	private final String mediaOptions;

	private static final String SNAPSHOT_NAME = "vlcj-snapshot.png";
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");

	@Inject
	private RecordingDataService recordingService;

	private EventBus eventBus;

	private final EmbeddedMediaPlayer streamPlayer;
	private final EmbeddedMediaPlayer imagePlayer;

	private Long currentRecordingId;

	@Inject
	public PlayerFacadeVlcjImpl(final EventBus eventBus, @Named("output.folder") String outputFolder,
			@Named("vlc.installation.path") String vlcInstallationPath,
			@Named("media.resource.location") String mediaResourceLocation, @Named("media.options") String mediaOptions) {
		this.eventBus = eventBus;
		this.outputFolder = outputFolder;
		this.mediaResourceLocation = mediaResourceLocation;
		this.mediaOptions = mediaOptions;
		eventBus.register(this);

		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), vlcInstallationPath);
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

		this.streamPlayer = new MediaPlayerFactory().newEmbeddedMediaPlayer();

		this.imagePlayer = new MediaPlayerFactory().newEmbeddedMediaPlayer();
		imagePlayer.setSnapshotDirectory(outputFolder);
		imagePlayer.addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
			@Override
			public void snapshotTaken(MediaPlayer mediaPlayer, String filename) {
				System.out.println("snapshotTaken(filename=" + filename + ")");
			}
		});
	}

	@Override
	public void startRecording() {
		if (!isPlaying()) {
			final Date startTime = new Date();
			final String fileName = buildRecordingName(startTime);
			final String[] options = { String.format(mediaOptions, buildRecordingPath(fileName)) };
			streamPlayer.playMedia(mediaResourceLocation, options);
			imagePlayer.playMedia(mediaResourceLocation);

			currentRecordingId = recordingService.createRecording(fileName, startTime);
			eventBus.post(new CreateAndSaveSnapshotEvent(currentRecordingId));
		}
	}

	@Override
	public void stopRecording() {
		streamPlayer.stop();
		imagePlayer.stop();
		recordingService.updateDuration(currentRecordingId, new Date());
	}

	private void saveSnapshot() {
		if (imagePlayer.isPlaying()) {
			final String snapshotName = buildSnapshotName();
			final File file = new File(snapshotName);
			file.deleteOnExit();
			imagePlayer.saveSnapshot(file, Constants.SNAPSHOT_WIDTH, Constants.SNAPSHOT_HEIGHT);
			recordingService.saveSnapshot(currentRecordingId, snapshotName);
		}
	}

	private static String buildRecordingName(final Date date) {
		return "RECORDING-" + df.format(date) + ".mp4";
	}

	private String buildRecordingPath(final String recordingName) {
		return outputFolder + "/" + recordingName;
	}

	private String buildSnapshotName() {
		return outputFolder + "/" + SNAPSHOT_NAME;
	}

	private boolean isPlaying() {
		return streamPlayer.isPlaying() && imagePlayer.isPlaying();
	}

	@Subscribe
	public void onCreateAndSaveEvent(final CreateAndSaveSnapshotEvent event) throws InterruptedException {
		TimeUnit.SECONDS.sleep(10);
		saveSnapshot();
	}

	@Override
	public String getSnapshot() {
		if (imagePlayer.isPlaying()) {
			final BufferedImage bufImage = imagePlayer.getSnapshot(Constants.SNAPSHOT_WIDTH, Constants.SNAPSHOT_HEIGHT);
			if (bufImage != null) {
				return ImageDecoder.encodeImage(bufImage);
			}
		}
		return null;
	}
}
