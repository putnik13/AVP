package com.atanor.smanager.client.injector;

import com.atanor.smanager.client.mvp.AppPlaceController;
import com.atanor.smanager.client.mvp.views.EditPresetView;
import com.atanor.smanager.client.mvp.views.NavigatePresetView;
import com.atanor.smanager.client.mvp.views.impl.EditPresetViewImpl;
import com.atanor.smanager.client.mvp.views.impl.NavigatePresetViewImpl;
import com.atanor.smanager.client.ui.MainPane;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class ClientModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(MainPane.class).in(Singleton.class);
		bind(NavigatePresetView.class).to(NavigatePresetViewImpl.class).in(Singleton.class);
		bind(EditPresetView.class).to(EditPresetViewImpl.class).in(Singleton.class);
		bind(PlaceController.class).to(AppPlaceController.class).in(Singleton.class);
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
	}

}
