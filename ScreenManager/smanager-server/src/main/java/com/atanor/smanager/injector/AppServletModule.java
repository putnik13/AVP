package com.atanor.smanager.injector;

import com.atanor.smanager.servlet.ConfigServlet;
import com.atanor.smanager.shared.AppConstants;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;

public class AppServletModule extends ServletModule {

	private static final String BASE_URL = "/" + "ScreenManager" + "/";

	@Override
	protected void configureServlets() {
		// PersistFilter provides a new instance of EntityManager for each
		// request to the servlet container (Open Session In View pattern)
		install(new JpaPersistModule("SMANAGER-JPA"));
        filter("/*").through(PersistFilter.class);
        
		serve(BASE_URL + AppConstants.CONFIG_PATH)
				.with(ConfigServlet.class);
	}

}
