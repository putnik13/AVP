package com.atanor.vserver.async;

import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;

public class AsyncConnector {

	private static final String SUBSCRIBE_RECEIVE_SNAPSHOTS = "Subscribe to receive snapshots";

	public static Broadcaster getBroadcaster() {
		return BroadcasterFactory.getDefault().lookup(SUBSCRIBE_RECEIVE_SNAPSHOTS, true);
	}

	public static void broadcast(final Object obj) {
		BroadcasterFactory.getDefault().lookup(SUBSCRIBE_RECEIVE_SNAPSHOTS, false).broadcast(obj);
	}
}
