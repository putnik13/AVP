package com.atanor.smanager.client.mvp.events;

import com.atanor.smanager.client.ui.WindowLabel;
import com.google.gwt.event.shared.GwtEvent;

public class ActivateGridEvent extends GwtEvent<ActivateGridHandler> {

	private static Type<ActivateGridHandler> TYPE;

	private Integer left;
	private Integer top;
	private Integer width;
	private Integer height;
	private WindowLabel w;
	public ActivateGridEvent(Integer left, Integer top, Integer width, Integer height, WindowLabel w) {
		this.left = left;
		this.top = top;
		this.width = width;
		this.height = height;
		this.w = w;
	}

	public Integer getLeft() {
		return left;
	}

	public Integer getTop() {
		return top;
	}

	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}

	public WindowLabel getW() {
		return w;
	}

	public static Type<ActivateGridHandler> getType() {
		if (TYPE == null) {
			TYPE = new Type<ActivateGridHandler>();
		}
		return TYPE;
	}

	@Override
	public final Type<ActivateGridHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ActivateGridHandler handler) {
		handler.onActivateGrid(this);
	}
}