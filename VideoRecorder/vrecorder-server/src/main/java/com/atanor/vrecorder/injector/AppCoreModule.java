package com.atanor.vrecorder.injector;

import java.util.concurrent.Executors;

import com.atanor.vrecorder.domain.facades.PalantirFacade;
import com.atanor.vrecorder.domain.facades.PalantirFacadeImpl;
import com.atanor.vrecorder.domain.facades.PlayerFacade;
import com.atanor.vrecorder.domain.facades.PlayerFacadeMockImpl;
import com.atanor.vrecorder.services.RecordingDataService;
import com.atanor.vrecorder.services.RecordingDataServiceImpl;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class AppCoreModule extends AbstractModule {

	@Override
	protected void configure() {
		final EventBus eventBus = new AsyncEventBus(Executors.newCachedThreadPool());

		bind(EventBus.class).toInstance(eventBus);
		bind(RecordingDataService.class).to(RecordingDataServiceImpl.class);
		bind(PalantirFacade.class).to(PalantirFacadeImpl.class).in(Scopes.SINGLETON);
		bind(PlayerFacade.class).to(PlayerFacadeMockImpl.class).in(Scopes.SINGLETON);
	}

}
