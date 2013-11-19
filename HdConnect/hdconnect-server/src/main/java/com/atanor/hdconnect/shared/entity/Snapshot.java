package com.atanor.hdconnect.shared.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Snapshot implements Serializable {

	private String width;
	private String height;
	private String encodedImage;

	public Snapshot(final String encodedImage, final String width, final String height) {
		this.encodedImage = encodedImage;
		this.width = width;
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public String getHeight() {
		return height;
	}

	public String getEncodedImage() {
		return encodedImage;
	}

}
