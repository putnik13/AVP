package com.atanor.vserver.vsclient.client.json;

import org.atmosphere.gwt20.client.ClientSerializer;

import com.atanor.vserver.common.entity.Notification;
import com.atanor.vserver.common.entity.Snapshot;
import com.atanor.vserver.common.messages.Message;
import com.google.gwt.user.client.rpc.SerializationException;

public class JsonSerializer implements ClientSerializer {

	private static final String NOTIFICATION_MSG = Message.NOTIFCATION.name();
	private static final String SNAPSHOT_MSG = Message.SNAPSHOT.name();

	@Override
	public Object deserialize(String message) throws SerializationException {
		Object result = "Can not deserialize unexpected message format, message:" + message;

		if (isNotification(message)) {
			result = getNotification(message);
		} else if (isSnapshot(message)) {
			result = getSnapshot(message);
		}

		return result;
	}

	@Override
	public String serialize(Object message) throws SerializationException {
		throw new IllegalStateException("Operation is not supported");
	}

	private boolean isNotification(final String message) {
		return message.startsWith(NOTIFICATION_MSG);
	}

	private Notification getNotification(final String message) {
		final String jsonNotification = message.substring(NOTIFICATION_MSG.length());
		return JsonConverters.NOTIFICATION_READER.read(jsonNotification);
	}

	private boolean isSnapshot(final String message) {
		return message.startsWith(SNAPSHOT_MSG);
	}

	private Snapshot getSnapshot(final String message) {
		final String jsonSnapshot = message.substring(SNAPSHOT_MSG.length());
		return JsonConverters.SNAPSHOT_READER.read(jsonSnapshot);
	}

}
