package com.atanor.smanager.client.ui;

import com.atanor.smanager.client.Client;
import com.atanor.smanager.client.mvp.events.CleanWindowSelectionEvent;
import com.atanor.smanager.client.mvp.events.CleanWindowSelectionHandler;
import com.atanor.smanager.client.ui.style.EditWindowStyleApplier;
import com.atanor.smanager.rpc.dto.WindowDto;
import com.google.common.primitives.Ints;
import com.smartgwt.client.widgets.Label;

public class WindowLabel extends Label implements CleanWindowSelectionHandler {

	private final WindowDto dto;
	private final Double scaleFactor;

	private Boolean selected = Boolean.FALSE;
	private Boolean dirty = Boolean.FALSE;
	private Long leftOffset;
	private Long topOffset;

	public WindowLabel(final WindowDto dto, final Double scaleFactor) {
		this.dto = dto;
		this.scaleFactor = scaleFactor;
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

	public void setLeftOffset(final Long leftOffset) {
		this.leftOffset = leftOffset;
	}

	public void setTopOffset(final Long topOffset) {
		this.topOffset = topOffset;
	}

	public WindowLabel clone() {
		final WindowLabel clone = new WindowLabel(dto, scaleFactor);

		clone.setTop(getTop());
		clone.setLeft(getLeft());
		clone.setWidth(getWidth());
		clone.setHeight(getHeight());
		clone.setContents(getContents());
		clone.setZIndex(getZIndex());
		clone.setLeftOffset(leftOffset);
		clone.setTopOffset(topOffset);

		new EditWindowStyleApplier().applyStyle(clone);

		// add clean window selection handler
		Client.getEventBus().addHandler(CleanWindowSelectionEvent.getType(), clone);

		return clone;
	}

	public void updateDto() {
		if (isDirty()) {

			final Long xTopLeft = Math.round((new Long(getLeft()).doubleValue() - leftOffset.doubleValue())
					/ scaleFactor);
			dto.setXTopLeft(Ints.checkedCast(xTopLeft));

			final Long yTopLeft = Math
					.round((new Long(getTop()).doubleValue() - topOffset.doubleValue()) / scaleFactor);
			dto.setYTopLeft(Ints.checkedCast(yTopLeft));

			final Long xBottomRight = Math.round((new Long(getLeft()).doubleValue()
					+ new Long(getWidth()).doubleValue() - leftOffset.doubleValue())
					/ scaleFactor);
			dto.setXBottomRight(Ints.checkedCast(xBottomRight));
			
			final Long yBottomRight = Math.round((new Long(getTop()).doubleValue()
					+ new Long(getHeight()).doubleValue() - topOffset.doubleValue())
					/ scaleFactor);
			dto.setYBottomRight(Ints.checkedCast(yBottomRight));
		}
	}

	@Override
	public void onCleanWindowSelection(CleanWindowSelectionEvent event) {
		if (isSelected()) {
			setSelected(false);
			new EditWindowStyleApplier().applyStyle(this);
		}
	}

}
