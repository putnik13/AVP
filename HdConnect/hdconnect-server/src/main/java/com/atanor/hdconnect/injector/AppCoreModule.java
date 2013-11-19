package com.atanor.hdconnect.injector;

import java.util.concurrent.Executors;

import com.atanor.hdconnect.events.DeadEventListener;
import com.atanor.hdconnect.facades.PlayerFacadeMock;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;

public class AppCoreModule extends AbstractModule {

	@Override
	protected void configure() {
		final EventBus eventBus = new AsyncEventBus(Executors.newCachedThreadPool());
		eventBus.register(new DeadEventListener());

		bind(EventBus.class).toInstance(eventBus);
		bind(PlayerFacadeMock.class).asEagerSingleton();
	}

}
