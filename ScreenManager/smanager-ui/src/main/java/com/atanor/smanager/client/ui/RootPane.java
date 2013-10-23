package com.atanor.smanager.client.ui;

import com.atanor.smanager.client.Client;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class RootPane<E extends Widget> extends Composite implements IsWidget {

	private final E container;

	public RootPane(E container) {
		this.container = container;

		bind(new ActivityMapper() {

			@Override
			public Activity getActivity(Place place) {
				onPlaceChange(place);
				return null;
			}

		}, new SimplePanel());
	}

	protected void onPlaceChange(Place place){
	}

	@Override
	public E asWidget() {
		return container;
	}

	protected void bind(ActivityMapper mapper, AcceptsOneWidget widget) {
		ActivityManager activityManager = new ActivityManager(mapper, Client.getEventBus());
		activityManager.setDisplay(widget);
	}

}
