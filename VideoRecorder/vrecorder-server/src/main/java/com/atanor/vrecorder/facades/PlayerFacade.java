package com.atanor.vrecorder.facades;

public interface PlayerFacade {
	
	void startRecording();

	void stopRecording();	
	
	String getSnapshot();
}
