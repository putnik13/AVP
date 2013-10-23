package com.atanor.smanager.client.mvp;

import com.atanor.smanager.client.mvp.activities.edit.NoPresetSelectedActivity;
import com.atanor.smanager.client.mvp.activities.edit.PresetSelectedActivity;
import com.atanor.smanager.client.mvp.places.NoPresetSelectedPlace;
import com.atanor.smanager.client.mvp.places.PresetSelectedPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class EditPresetMapper implements ActivityMapper {

	public EditPresetMapper() {
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
