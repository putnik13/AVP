package com.atanor.smanager.injector;

import com.atanor.smanager.services.PresetService;
import com.atanor.smanager.services.PresetServiceImpl;
import com.google.inject.AbstractModule;

public class AppCoreModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(PresetService.class).to(PresetServiceImpl.class);
	}

}
