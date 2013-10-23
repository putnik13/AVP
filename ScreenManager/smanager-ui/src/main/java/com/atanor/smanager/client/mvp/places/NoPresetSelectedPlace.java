package com.atanor.smanager.client.mvp.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class NoPresetSelectedPlace extends Place {

	public NoPresetSelectedPlace() {
	}

	public static class Tokenizer implements PlaceTokenizer<NoPresetSelectedPlace> {

		public NoPresetSelectedPlace getPlace(String token) {
			return new NoPresetSelectedPlace();
		}

		public String getToken(NoPresetSelectedPlace place) {
			return null;
		}
	}
}
