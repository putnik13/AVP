package com.atanor.vrecorder.injector;

import com.atanor.vrecorder.servlet.RecordingServlet;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;

public class AppServletModule extends ServletModule {

	private static final String BASE_URL = "/VideoRecorder";

	@Override
	protected void configureServlets() {
		// PersistFilter provides a new instance of EntityManager for each
		// request to the servlet container (Open Session In View pattern)
		install(new JpaPersistModule("VRECORDER-JPA"));
        filter("/*").through(PersistFilter.class);
        
		serve(BASE_URL + "/recording").with(RecordingServlet.class);
	}

}
