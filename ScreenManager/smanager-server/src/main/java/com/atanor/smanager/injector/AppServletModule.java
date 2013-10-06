package com.atanor.smanager.injector;

import com.atanor.smanager.services.ConfigServiceImpl;
import com.atanor.smanager.shared.AppConstants;
import com.google.inject.servlet.ServletModule;

public class AppServletModule extends ServletModule {

	private static final String BASE_URL = "/" + "ScreenManager" + "/";

	@Override
	protected void configureServlets() {
		serve(BASE_URL + AppConstants.CONFIG_PATH)
				.with(ConfigServiceImpl.class);
	}

}
