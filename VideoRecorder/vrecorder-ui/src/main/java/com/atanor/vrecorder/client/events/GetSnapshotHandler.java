package com.atanor.vrecorder.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface GetSnapshotHandler extends EventHandler {

	void onGetSnapshot(GetSnapshotEvent event);
}
