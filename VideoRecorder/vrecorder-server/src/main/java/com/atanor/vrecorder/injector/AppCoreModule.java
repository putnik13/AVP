package com.atanor.vrecorder.injector;

import com.atanor.vrecorder.domain.facades.PalantirFacade;
import com.atanor.vrecorder.domain.facades.PalantirFacadeImpl;
import com.atanor.vrecorder.domain.facades.PlayerFacade;
import com.atanor.vrecorder.domain.facades.PlayerFacadeMockImpl;
import com.atanor.vrecorder.services.RecordingDataService;
import com.atanor.vrecorder.services.RecordingDataServiceImpl;
import com.google.inject.AbstractModule;

public class AppCoreModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(RecordingDataService.class).to(RecordingDataServiceImpl.class);
		bind(PalantirFacade.class).to(PalantirFacadeImpl.class);
		bind(PlayerFacade.class).to(PlayerFacadeMockImpl.class);
	}

}
