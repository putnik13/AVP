package com.atanor.vrecorder.domain.facades;

import java.text.SimpleDateFormat;
import java.util.Date;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class PlayerFacadeVlcjImpl implements PlayerFacade {

	private static final String OUTPUT_FOLDER = "d:/tmp/vlc-input";
	private static final String VLC_INTALLATION_PATH = "d:/Installed/VLC";
	private static final String MEDIA_RESOURCE_LOCATION = "file:///D:/tmp/vlc-input/test2.mp4";
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");

	private final EmbeddedMediaPlayer mediaPlayer;

	public PlayerFacadeVlcjImpl() {
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), VLC_INTALLATION_PATH);
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

		this.mediaPlayer = new MediaPlayerFactory().newEmbeddedMediaPlayer();
	}

	@Override
	public void startRecording() {		
		final String[] options = { ":sout=#transcode{vcodec=h264,acodec=mpga,ab=128,channels=2,samplerate=44100}:file{dst="
				+ buildRecordingName() + "}" };
		mediaPlayer.playMedia(MEDIA_RESOURCE_LOCATION, options);
	}

	@Override
	public void stopRecording() {
		mediaPlayer.stop();
	}

	private String buildRecordingName() {
		return OUTPUT_FOLDER + "/" + "RECORDING_" + df.format(new Date()) + ".mp4";
	}

}
