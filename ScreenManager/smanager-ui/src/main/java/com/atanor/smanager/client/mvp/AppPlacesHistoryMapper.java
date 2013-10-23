package com.atanor.smanager.client.mvp;

import com.atanor.smanager.client.mvp.places.NoPresetSelectedPlace;
import com.atanor.smanager.client.mvp.places.PresetSelectedPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ PresetSelectedPlace.Tokenizer.class, NoPresetSelectedPlace.Tokenizer.class })
public interface AppPlacesHistoryMapper extends PlaceHistoryMapper {
}
