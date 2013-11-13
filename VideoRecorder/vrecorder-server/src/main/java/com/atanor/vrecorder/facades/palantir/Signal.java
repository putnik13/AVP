package com.atanor.vrecorder.facades.palantir;

public enum Signal {

	START_RECORDING("Start"), STOP_RECORDING("Stop"), NO_FREE_SPACE("NoSpace"), NO_VIDEO_AVAILABLE("NoSource");

	private String value;

	private Signal(final String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
