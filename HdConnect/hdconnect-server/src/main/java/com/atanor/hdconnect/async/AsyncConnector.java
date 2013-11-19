package com.atanor.hdconnect.async;

import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;

public class AsyncConnector {

	private static final String HD_CONNECT_RECEIVE_SNAPSHOTS = "Connect to receive snapshots";

	public static Broadcaster getBroadcaster() {
		return BroadcasterFactory.getDefault().lookup(HD_CONNECT_RECEIVE_SNAPSHOTS, true);
	}

	public static void broadcast(final Object obj) {
		BroadcasterFactory.getDefault().lookup(HD_CONNECT_RECEIVE_SNAPSHOTS, false).broadcast(obj);
	}
}
