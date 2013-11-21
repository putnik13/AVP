package com.atanor.hdconnect.client.events;

import com.atanor.hdconnect.client.entity.Snapshot;
import com.google.gwt.event.shared.GwtEvent;

public class SnapshotReceivedEvent extends GwtEvent<SnapshotReceivedHandler> {

	private static Type<SnapshotReceivedHandler> TYPE;

	public static Type<SnapshotReceivedHandler> getType() {
		if (TYPE == null) {
			TYPE = new Type<SnapshotReceivedHandler>();
		}
		return TYPE;
	}

	private final Snapshot snapshot;
	
	public SnapshotReceivedEvent(final Snapshot snapshot){
		this.snapshot = snapshot;
	}
	
	public Snapshot getSnapshot() {
		return snapshot;
	}

	@Override
	public final Type<SnapshotReceivedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SnapshotReceivedHandler handler) {
		handler.onSnapshotReceived(this);
	}
}