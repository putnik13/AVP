package com.atanor.smanager.client.method;

import java.util.EnumSet;
import java.util.Map;
import com.google.common.collect.Maps;

public enum PresetState {

	Active("Active", 1), Cancel("Cancel", 2), Navigate("Navigate", 3), Apply(
			"Apply", 4), Save("Save", 5), Changed("Changed", 6), Default(
			"Default", 7);

	private int id;
	private String state;

	private static Map<String, PresetState> layouts = Maps.newHashMap();
	static {
		for (PresetState p : EnumSet.allOf(PresetState.class)) {
			layouts.put(p.state, p);
		}
	}

	private PresetState(String state, int id) {
		this.id = id;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public static Map<String, PresetState> getLayouts() {
		return layouts;
	}

	public static void setLayouts(Map<String, PresetState> layouts) {
		PresetState.layouts = layouts;
	}
}