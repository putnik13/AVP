package com.atanor.smanager.client;

import com.atanor.smanager.client.injector.ClientInjector;
import com.atanor.smanager.client.mvp.views.EditPresetView;
import com.atanor.smanager.client.mvp.views.NavigatePresetView;
import com.atanor.smanager.client.ui.DisplayPanel;
import com.atanor.smanager.client.ui.MainPane;
import com.atanor.smanager.rpc.services.ConfigServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

public class Client {

	private static final ClientInjector injector = GWT.create(ClientInjector.class);

	public static void goTo(Place place) {
		injector.getPlaceController().goTo(place);
	}

	public static PlaceController getPlaceController() {
		return injector.getPlaceController();
	}

	public static EventBus getEventBus() {
		return injector.getEventBus();
	}

	public static NavigatePresetView getNavigatePresetView() {
		return injector.getNavigatePresetView();
	}

	public static EditPresetView getEditPresetView() {
		return injector.getEditPresetView();
	}

	public static ConfigServiceAsync getConfigService() {
		return injector.getConfigService();
	}

	public static MainPane getMainPane(){
		return injector.getMainPane();
	}
	
	public static DisplayPanel getNavigateDisplay(){
		return injector.getMainPane().asWidget().getNavigateDisplay();
	}
	
	public static DisplayPanel getEditDisplay(){
		return injector.getMainPane().asWidget().getEditDisplay();
	}
}
