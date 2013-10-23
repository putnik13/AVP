package com.atanor.smanager.client.mvp;

import com.atanor.smanager.client.mvp.activities.navigate.NoPresetSelectedActivity;
import com.atanor.smanager.client.mvp.activities.navigate.PresetSelectedActivity;
import com.atanor.smanager.client.mvp.places.NoPresetSelectedPlace;
import com.atanor.smanager.client.mvp.places.PresetSelectedPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class NavigatePresetMapper implements ActivityMapper {

	public NavigatePresetMapper() {
	}

	@Override
	public Activity getActivity(Place place) {

		if (place instanceof PresetSelectedPlace) {
			return new PresetSelectedActivity(((PresetSelectedPlace) place).getPresetId());
		} else if (place instanceof NoPresetSelectedPlace) {
			return new NoPresetSelectedActivity();
		}

		return null;
	}

}
