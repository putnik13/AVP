package com.atanor.smanager.injector;

import com.atanor.smanager.services.ConfigurationService;
import com.atanor.smanager.services.ConfigurationServiceImpl;
import com.google.inject.AbstractModule;

public class AppCoreModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ConfigurationService.class).to(ConfigurationServiceImpl.class);		
	}

}
