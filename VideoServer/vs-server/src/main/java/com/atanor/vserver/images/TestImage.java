package com.atanor.vserver.images;

import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvShowImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvWaitKey;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class TestImage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		open("d:/projects/AVP/VideoServer/vs-launch/src/main/webapp/images/test0.png");
	}

	public static void open(String filename) {
		IplImage image = cvLoadImage(filename);
		if (image != null) {
			cvShowImage(filename, image);
			cvWaitKey();
		}
	}

}
