package com.atanor.smanager.client.ui;

import com.atanor.smanager.rpc.dto.PresetDto;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;

public class PresetLabel extends Label {

	private final PresetDto dto;
	private Boolean selected = Boolean.FALSE;
	
	public PresetLabel(final PresetDto dto) {
		this.dto = dto;
	}

	public PresetDto getDto() {
		return dto;
	}

	public Long getId() {
		return dto.getId();
	}

	public Boolean isSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public PresetLabel clone() {
		PresetLabel clone = new PresetLabel(dto);

		clone.setWidth(getWidth());
		clone.setHeight(getHeight());

		for (Canvas child : getChildren()) {
			clone.addChild(child instanceof WindowLabel ? ((WindowLabel) child).clone() : child);
		}

		return clone;
	}

}
