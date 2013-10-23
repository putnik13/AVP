package com.atanor.smanager.client.injector;

import com.atanor.smanager.client.mvp.views.EditPresetView;
import com.atanor.smanager.client.mvp.views.NavigatePresetView;
import com.atanor.smanager.client.ui.MainPane;
import com.atanor.smanager.rpc.services.ConfigServiceAsync;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

@GinModules(ClientModule.class)
public interface ClientInjector extends Ginjector {

	EventBus getEventBus();

	PlaceController getPlaceController();

	NavigatePresetView getNavigatePresetView();

	EditPresetView getEditPresetView();
	
	ConfigServiceAsync getConfigService();
	
	MainPane getMainPane();
}
