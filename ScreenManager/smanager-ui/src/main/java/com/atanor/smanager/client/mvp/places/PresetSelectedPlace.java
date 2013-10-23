package com.atanor.smanager.client.mvp.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PresetSelectedPlace extends Place {

	private final Long presetId;

	public PresetSelectedPlace(Long presetId) {
		this.presetId = presetId;
	}

	public Long getPresetId() {
		return presetId;
	}

	public static class Tokenizer implements PlaceTokenizer<PresetSelectedPlace> {

		public PresetSelectedPlace getPlace(String token) {
			return new PresetSelectedPlace(Long.parseLong(token));
		}

		public String getToken(PresetSelectedPlace place) {
			return "" + place.getPresetId();
		}
	}
}
