package com.atanor.smanager.domain.entity;

import java.util.EnumSet;
import java.util.Map;

import com.google.common.collect.Maps;

public enum DisplayLayout {

	ONExONE("1x1"), ONExTWO("1x2"), ONExTHREE("1x3"),
	// -------------------
	TWOxTWO("2x2"), TWOxTHREE("2x3"),
	// -------------------
	THREExTHREE("3x3");

	private String description;

	private static Map<String, DisplayLayout> layouts = Maps.newHashMap();
	static {
		for (DisplayLayout l : EnumSet.allOf(DisplayLayout.class)) {
			layouts.put(l.toString(), l);
		}
	}

	private DisplayLayout(final String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static DisplayLayout getLayout(String description) {
		return layouts.get(description);
	}

}