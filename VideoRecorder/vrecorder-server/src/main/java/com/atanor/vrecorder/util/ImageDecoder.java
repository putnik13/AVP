package com.atanor.vrecorder.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

public class ImageDecoder {

	public static String encodeImage(final String fileName) {
		File file = new File(fileName);

		try {
			byte buffer[] = FileUtils.readFileToByteArray(file);
			return Base64.encodeBase64String(buffer);
		} catch (IOException e) {
			throw new IllegalStateException("Error. Can not encode image", e);
		}
	}
}
