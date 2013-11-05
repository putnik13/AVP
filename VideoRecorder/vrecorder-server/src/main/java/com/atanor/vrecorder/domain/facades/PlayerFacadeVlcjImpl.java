package com.atanor.vrecorder.domain.facades;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.atanor.vrecorder.services.RecordingDataService;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class PlayerFacadeVlcjImpl implements PlayerFacade {

	private static final String OUTPUT_FOLDER = "d:/tmp/vlc-input";
	private static final String VLC_INTALLATION_PATH = "d:/Installed/VLC";
	private static final String MEDIA_RESOURCE_LOCATION = "file:///D:/tmp/vlc-input/test2.mp4";
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");

	@Inject
	private RecordingDataService recordingService;

	private final EmbeddedMediaPlayer streamPlayer;
	private final EmbeddedMediaPlayer imagePlayer;

	private Long currentRecordingId;

	public PlayerFacadeVlcjImpl() {
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), VLC_INTALLATION_PATH);
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

		this.streamPlayer = new MediaPlayerFactory().newEmbeddedMediaPlayer();

		this.imagePlayer = new MediaPlayerFactory().newEmbeddedMediaPlayer();
		imagePlayer.setSnapshotDirectory(OUTPUT_FOLDER);
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
			final String[] options = { ":sout=#transcode{vcodec=h264,acodec=mpga,ab=128,channels=2,samplerate=44100}:file{dst="
					+ buildRecordingPath(fileName) + "}" };
			streamPlayer.playMedia(MEDIA_RESOURCE_LOCATION, options);
			imagePlayer.playMedia(MEDIA_RESOURCE_LOCATION);

			currentRecordingId = recordingService.createRecording(fileName, startTime);
		}
	}

	@Override
	public void stopRecording() {
		if (isPlaying()) {
			streamPlayer.stop();
			imagePlayer.stop();
			recordingService.updateDuration(currentRecordingId, new Date());
		}
	}

	@Override
	public void saveSnapshot() {
		if (isPlaying()) {
			imagePlayer.saveSnapshot();
		}
	}

	private String buildRecordingName(final Date date) {
		return "RECORDING-" + df.format(date) + ".mp4";
	}

	private String buildRecordingPath(final String recordingName) {
		return OUTPUT_FOLDER + "/" + recordingName;
	}

	private boolean isPlaying() {
		return streamPlayer.isPlaying() && imagePlayer.isPlaying();
	}

}
