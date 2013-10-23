package com.atanor.smanager.domain.entity;

import java.util.EnumSet;
import java.util.Map;

import com.google.common.collect.Maps;

public enum PanelLayout {

	ONExONE("1x1", 1, 1), ONExTWO("1x2", 1, 2), ONExTHREE("1x3", 1, 3), ONExFOUR("1x4", 1, 4), ONExFIVE("1x5", 1, 5),
	// -------------------
	TWOxTWO("2x2", 2, 2), TWOxTHREE("2x3", 2, 3), TWOxFOUR("2x4", 2, 4), TWOxFIVE("2x5", 2, 5),
	// -------------------
	THREExTHREE("3x3", 3, 3), THREExFOUR("3x4", 3, 4), THREExFIVE("3x5", 3, 5),
	// -------------------
	FOURxFOUR("4x4", 4, 4), FOURxFIVE("4x5", 4, 5),
	// -------------------
	FIVExFIVE("5x5", 5, 5);

	private String description;

	private Integer rowPanelQuantity;
	private Integer columnPanelQuantity;

	private static Map<String, PanelLayout> layouts = Maps.newHashMap();
	static {
		for (PanelLayout l : EnumSet.allOf(PanelLayout.class)) {
			layouts.put(l.description, l);
		}
	}

	private PanelLayout(final String description, final Integer rowPanelQuantity, final Integer columnPanelQuantity) {
		this.description = description;
		this.rowPanelQuantity = rowPanelQuantity;
		this.columnPanelQuantity = columnPanelQuantity;
	}

	public String getDescription() {
		return description;
	}

	public Integer getRowPanelQuantity() {
		return rowPanelQuantity;
	}

	public Integer getColumnPanelQuantity() {
		return columnPanelQuantity;
	}

	public static PanelLayout getLayout(String description) {
		return layouts.get(description);
	}

}