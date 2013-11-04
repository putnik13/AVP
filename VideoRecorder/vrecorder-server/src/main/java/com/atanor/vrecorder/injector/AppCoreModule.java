package com.atanor.vrecorder.injector;

import com.atanor.vrecorder.services.RecordingDataService;
import com.atanor.vrecorder.services.RecordingDataServiceImpl;
import com.google.inject.AbstractModule;

public class AppCoreModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(RecordingDataService.class).to(RecordingDataServiceImpl.class);
	}

}
