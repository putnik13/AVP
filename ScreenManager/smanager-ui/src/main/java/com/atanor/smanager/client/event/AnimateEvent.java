package com.atanor.smanager.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class AnimateEvent extends GwtEvent<AnimateEventHandler> {
	
	private int layoutNumber;
	
	public AnimateEvent(int layoutNumber) {
		this.layoutNumber = layoutNumber;
	}

	public static Type<AnimateEventHandler> TYPE = new Type<AnimateEventHandler>();

	@Override
	public Type<AnimateEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AnimateEventHandler handler) {
		handler.animate(this);
	}

	public int getLayoutNumber() {
		return layoutNumber;
	}

}
