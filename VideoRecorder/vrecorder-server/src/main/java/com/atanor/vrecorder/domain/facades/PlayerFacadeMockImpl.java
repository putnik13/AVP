package com.atanor.vrecorder.domain.facades;

public class PlayerFacadeMockImpl implements PlayerFacade {

	@Override
	public void startRecording() {
		System.out.println("VLC Player starts recording ..");
	}

	@Override
	public void stopRecording() {
		System.out.println("VLC Player stops recording ..");
	}

	@Override
	public void saveSnapshot() {
		System.out.println("VLC Player saves snapshot ..");
	}

}
