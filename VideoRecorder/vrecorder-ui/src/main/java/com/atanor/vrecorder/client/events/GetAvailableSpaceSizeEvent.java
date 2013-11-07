package com.atanor.vrecorder.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class GetAvailableSpaceSizeEvent extends GwtEvent<GetAvailableSpaceSizeHandler> {

	private static Type<GetAvailableSpaceSizeHandler> TYPE;

	public static Type<GetAvailableSpaceSizeHandler> getType() {
		if (TYPE == null) {
			TYPE = new Type<GetAvailableSpaceSizeHandler>();
		}
		return TYPE;
	}

	@Override
	public final Type<GetAvailableSpaceSizeHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(GetAvailableSpaceSizeHandler handler) {
		handler.onGetAvailableSpaceSize(this);
	}
}