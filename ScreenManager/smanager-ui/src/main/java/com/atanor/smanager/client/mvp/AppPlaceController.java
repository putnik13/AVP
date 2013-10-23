package com.atanor.smanager.client.mvp;

import javax.inject.Inject;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

public class AppPlaceController extends PlaceController {

	@Inject
	public AppPlaceController(EventBus eventBus) {
		super(eventBus);
	}
}
