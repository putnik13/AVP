package com.atanor.smanager.client.ui;

import com.atanor.smanager.client.ui.style.EditWindowStyleApplier;
import com.atanor.smanager.rpc.dto.WindowDto;
import com.smartgwt.client.widgets.Label;

public class WindowLabel extends Label {

	private final WindowDto dto;
	private Boolean selected = Boolean.FALSE;
	private Boolean dirty = Boolean.FALSE;
	
	public WindowLabel(final WindowDto dto) {
		this.dto = dto;
	}

	public WindowDto getDto() {
		return dto;
	}

	public Boolean isSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public Boolean isDirty() {
		return dirty;
	}

	public void setDirty(Boolean dirty) {
		this.dirty = dirty;
	}

	public WindowLabel clone() {
		WindowLabel clone = new WindowLabel(dto);
		
		clone.setTop(getTop());
		clone.setLeft(getLeft());
		clone.setWidth(getWidth());
		clone.setHeight(getHeight());
		clone.setContents(getContents());
				
		new EditWindowStyleApplier().applyStyle(clone);
		
		return clone;
	}

	
}
