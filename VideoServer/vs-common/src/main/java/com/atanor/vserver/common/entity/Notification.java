package com.atanor.vserver.common.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Notification implements Serializable {

	private String message;

	public Notification() {
	}

	public Notification(final String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

}
