package com.atanor.hdconnect.client.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Snapshot implements Serializable {

	private String width;
	private String height;
	private String encodedImage;

	public Snapshot() {
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getEncodedImage() {
		return encodedImage;
	}

	public void setEncodedImage(String encodedImage) {
		this.encodedImage = encodedImage;
	}

	@Override
	public String toString() {
		return "Snapshot [width=" + width + ", height=" + height + ", encodedImage=" + encodedImage + "]";
	}

}
