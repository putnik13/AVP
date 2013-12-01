package com.atanor.vserver.async;

import org.apache.commons.lang3.Validate;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;

import com.atanor.vserver.common.entity.Notification;
import com.atanor.vserver.common.entity.Snapshot;
import com.atanor.vserver.common.messages.Message;
import com.google.gson.Gson;

public class AsyncConnector {

	private static final String SUBSCRIBE_RECEIVE_SNAPSHOTS = "Subscribe to receive snapshots";
	private static Gson gson = new Gson();

	public static Broadcaster getBroadcaster() {
		return BroadcasterFactory.getDefault().lookup(SUBSCRIBE_RECEIVE_SNAPSHOTS, true);
	}

	public static void broadcastNotification(final String msg) {
		Validate.notEmpty(msg, "msg can not be empty or null");
		
		final String jsonNotification = gson.toJson(new Notification(msg));
		final String jsonMsg = appendMessageType(Message.NOTIFCATION, jsonNotification);
		BroadcasterFactory.getDefault().lookup(SUBSCRIBE_RECEIVE_SNAPSHOTS, false).broadcast(jsonMsg);
	}

	public static void broadcastSnapshot(final Snapshot snapshot) {
		Validate.notNull(snapshot, "snapshot can not be null");
		
		final String jsonSnapshot = gson.toJson(snapshot);
		final String jsonMsg = appendMessageType(Message.SNAPSHOT, jsonSnapshot);
		BroadcasterFactory.getDefault().lookup(SUBSCRIBE_RECEIVE_SNAPSHOTS, false).broadcast(jsonMsg);
	}

	private static String appendMessageType(final Message msgType, final String message) {
		return msgType.name() + message;
	}
}
