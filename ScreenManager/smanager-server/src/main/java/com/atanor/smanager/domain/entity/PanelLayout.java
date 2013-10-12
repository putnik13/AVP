package com.atanor.smanager.domain.entity;

import java.util.EnumSet;
import java.util.Map;

import com.google.common.collect.Maps;

public enum PanelLayout {

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

	private Integer panelQuantity;

	private static Map<String, PanelLayout> layouts = Maps.newHashMap();
	static {
		for (PanelLayout l : EnumSet.allOf(PanelLayout.class)) {
			layouts.put(l.description, l);
		}
	}

	private PanelLayout(final String description, final Integer panelQuantity) {
		this.description = description;
		this.panelQuantity = panelQuantity;
	}

	public String getDescription() {
		return description;
	}

	public Integer getPanelQuantity() {
		return panelQuantity;
	}

	public static PanelLayout getLayout(String description) {
		return layouts.get(description);
	}

}