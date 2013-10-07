package com.atanor.smanager.domain.entity;

import java.util.EnumSet;
import java.util.Map;

import com.google.common.collect.Maps;

public enum DisplayLayout {

	ONExONE("1x1", 1), ONExTWO("1x2", 2), ONExTHREE("1x3", 3), ONExFOUR("1x4", 4), ONExFIVE("1x5", 5),
	// -------------------
	TWOxTWO("2x2", 4), TWOxTHREE("2x3", 6), TWOxFOUR("2x4", 8), TWOxFIVE("2x5", 10),
	// -------------------
	THREExTHREE("3x3", 9), THREExFOUR("3x4", 12), THREExFIVE("3x5", 15),
	// -------------------
	FOURxFOUR("4x4", 16), FOURxFIVE("4x5", 20),
	// -------------------
	FIVExFIVE("5x5", 25);

	private String description;

	private Integer sectionQuantity;

	private static Map<String, DisplayLayout> layouts = Maps.newHashMap();
	static {
		for (DisplayLayout l : EnumSet.allOf(DisplayLayout.class)) {
			layouts.put(l.toString(), l);
		}
	}

	private DisplayLayout(final String description, final Integer sectionQuantity) {
		this.description = description;
		this.sectionQuantity = sectionQuantity;
	}

	public String getDescription() {
		return description;
	}

	public Integer getSectionQuantity() {
		return sectionQuantity;
	}

	public static DisplayLayout getLayout(String description) {
		return layouts.get(description);
	}

}