package com.atanor.vrecorder.injector;

import com.atanor.vrecorder.servlet.RecordingServlet;
import com.google.inject.servlet.ServletModule;

public class AppServletModule extends ServletModule {

	private static final String BASE_URL = "/VideoRecorder";

	@Override
	protected void configureServlets() {
		serve(BASE_URL + "/recording").with(RecordingServlet.class);
	}

}
