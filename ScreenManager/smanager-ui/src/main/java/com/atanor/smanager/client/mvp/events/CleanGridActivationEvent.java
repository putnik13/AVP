package com.atanor.smanager.client.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

public class CleanGridActivationEvent extends GwtEvent<CleanGridActivationHandler> {

	private static Type<CleanGridActivationHandler> TYPE;

	public static Type<CleanGridActivationHandler> getType() {
		if (TYPE == null) {
			TYPE = new Type<CleanGridActivationHandler>();
		}
		return TYPE;
	}

	@Override
	public final Type<CleanGridActivationHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CleanGridActivationHandler handler) {
		handler.onCleanGridActivation(this);
	}
}