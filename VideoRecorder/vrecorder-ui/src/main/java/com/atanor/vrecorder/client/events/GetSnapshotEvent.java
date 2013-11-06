package com.atanor.vrecorder.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class GetSnapshotEvent extends GwtEvent<GetSnapshotHandler> {

	private static Type<GetSnapshotHandler> TYPE;

	public static Type<GetSnapshotHandler> getType() {
		if (TYPE == null) {
			TYPE = new Type<GetSnapshotHandler>();
		}
		return TYPE;
	}

	@Override
	public final Type<GetSnapshotHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(GetSnapshotHandler handler) {
		handler.onGetSnapshot(this);
	}
}