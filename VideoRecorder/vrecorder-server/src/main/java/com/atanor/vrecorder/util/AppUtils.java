package com.atanor.vrecorder.util;

import javax.servlet.ServletContext;

public class AppUtils {

	private static ServletContext context;

	public static void setServletContext(final ServletContext context) {
		AppUtils.context = context;
	}

	public static ServletContext getServletContext() {
		return context;
	}
}
