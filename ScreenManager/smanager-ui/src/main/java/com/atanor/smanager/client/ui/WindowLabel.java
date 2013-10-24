package com.atanor.smanager.client.ui;

import com.atanor.smanager.client.ui.style.EditWindowStyleApplier;
import com.atanor.smanager.rpc.dto.WindowDto;
import com.google.common.primitives.Ints;
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

	public void setSelected(final Boolean selected) {
		this.selected = selected;
	}

	public Boolean isDirty() {
		return dirty;
	}

	public void setDirty(final Boolean dirty) {
		this.dirty = dirty;
	}

	public WindowLabel clone() {
		final WindowLabel clone = new WindowLabel(dto);

		clone.setTop(getTop());
		clone.setLeft(getLeft());
		clone.setWidth(getWidth());
		clone.setHeight(getHeight());
		clone.setContents(getContents());

		new EditWindowStyleApplier().applyStyle(clone);

		return clone;
	}

	public void updateDto(final Double scaleFactor) {
		if (isDirty()) {
			dto.setSource(getContents());

			final Long xTopLeft = Math.round(new Long(getLeft()).doubleValue() / scaleFactor);
			dto.setXTopLeft(Ints.checkedCast(xTopLeft));

			final Long yTopLeft = Math.round(new Long(getTop()).doubleValue() / scaleFactor);
			dto.setYTopLeft(Ints.checkedCast(yTopLeft));

			final Long xBottomRight = Math.round((new Long(getLeft()).doubleValue() + new Long(getWidth())
					.doubleValue()) / scaleFactor);
			dto.setXBottomRight(Ints.checkedCast(xBottomRight));

			final Long yBottomRight = Math.round((new Long(getTop()).doubleValue() + new Long(getHeight())
					.doubleValue()) / scaleFactor);
			dto.setYBottomRight(Ints.checkedCast(yBottomRight));
		}
	}

}
