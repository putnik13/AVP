package com.atanor.vrecorder.injector;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executors;

import com.atanor.vrecorder.facades.PalantirFacade;
import com.atanor.vrecorder.facades.PlayerFacade;
import com.atanor.vrecorder.facades.palantir.PalantirFacadeImpl;
import com.atanor.vrecorder.facades.player.PlayerFacadeMockImpl;
import com.atanor.vrecorder.services.RecordingDataService;
import com.atanor.vrecorder.services.RecordingDataServiceImpl;
import com.atanor.vrecorder.util.AppUtils;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;

public class AppCoreModule extends AbstractModule {

	@Override
	protected void configure() {
		final EventBus eventBus = new AsyncEventBus(Executors.newCachedThreadPool());

		bind(EventBus.class).toInstance(eventBus);
		bind(RecordingDataService.class).to(RecordingDataServiceImpl.class);
		bind(PalantirFacade.class).to(PalantirFacadeImpl.class).in(Scopes.SINGLETON);
		bind(PlayerFacade.class).to(PlayerFacadeMockImpl.class).in(Scopes.SINGLETON);

		loadProperties();
	}

	private void loadProperties() {
		try {
			Properties properties = new Properties();
			properties.load(AppUtils.getServletContext().getResourceAsStream("/init.properties"));
			Names.bindProperties(binder(), properties);
		} catch (IOException e) {
			throw new IllegalStateException("Can not load init properties", e);
		}

	}

}
