package com.atanor.vrecorder.domain.facades;

import java.text.SimpleDateFormat;
import java.util.Date;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class PlayerFacadeVlcjImpl implements PlayerFacade {

	private static final String OUTPUT_FOLDER = "d:/tmp/vlc-input";
	private static final String VLC_INTALLATION_PATH = "d:/Installed/VLC";
	private static final String MEDIA_RESOURCE_LOCATION = "file:///D:/tmp/vlc-input/test2.mp4";
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");

	private final EmbeddedMediaPlayer streamPlayer;
	private final EmbeddedMediaPlayer imagePlayer;

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
			final String[] options = { ":sout=#transcode{vcodec=h264,acodec=mpga,ab=128,channels=2,samplerate=44100}:file{dst="
					+ buildRecordingName() + "}" };
			streamPlayer.playMedia(MEDIA_RESOURCE_LOCATION, options);
			imagePlayer.playMedia(MEDIA_RESOURCE_LOCATION);
		}
	}

	@Override
	public void stopRecording() {
		if (isPlaying()) {
			streamPlayer.stop();
			imagePlayer.stop();
		}
	}

	@Override
	public void saveSnapshot() {
		if (isPlaying()) {
			imagePlayer.saveSnapshot();
		}
	}

	private String buildRecordingName() {
		return OUTPUT_FOLDER + "/" + "RECORDING-" + df.format(new Date()) + ".mp4";
	}

	private boolean isPlaying() {
		return streamPlayer.isPlaying() && imagePlayer.isPlaying();
	}

}
