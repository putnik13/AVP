package com.atanor.hdconnect.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

public class ImageDecoder {

	public static String encodeImage(final File imageFile) {

		try {
			byte buffer[] = FileUtils.readFileToByteArray(imageFile);
			return Base64.encodeBase64String(buffer);
		} catch (IOException e) {
			throw new IllegalStateException("Error. Can not encode image", e);
		}
	}

	public static String encodeImage(final BufferedImage bufImage) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			ImageIO.write(bufImage, "png", bos);
			byte buffer[] = bos.toByteArray();
			return Base64.encodeBase64String(buffer);
		} catch (IOException e) {
			throw new IllegalStateException("Error. Can not encode image", e);
		}
	}
}
