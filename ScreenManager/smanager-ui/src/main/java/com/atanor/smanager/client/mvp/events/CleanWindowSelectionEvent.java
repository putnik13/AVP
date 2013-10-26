package com.atanor.smanager.client.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

public class CleanWindowSelectionEvent extends GwtEvent<CleanWindowSelectionHandler> {

	private static Type<CleanWindowSelectionHandler> TYPE;

	public static Type<CleanWindowSelectionHandler> getType() {
		if (TYPE == null) {
			TYPE = new Type<CleanWindowSelectionHandler>();
		}
		return TYPE;
	}

	@Override
	public final Type<CleanWindowSelectionHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CleanWindowSelectionHandler handler) {
		handler.onCleanWindowSelection(this);
	}
}