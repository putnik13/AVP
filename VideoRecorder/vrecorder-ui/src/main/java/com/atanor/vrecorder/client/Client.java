package com.atanor.vrecorder.client;

import com.atanor.vrecorder.client.ui.MainPane;
import com.atanor.vrecorder.rpc.services.ConfigService;
import com.atanor.vrecorder.rpc.services.ConfigServiceAsync;
import com.atanor.vrecorder.rpc.services.RecordingService;
import com.atanor.vrecorder.rpc.services.RecordingServiceAsync;
import com.atanor.vrecorder.rpc.services.SnapshotService;
import com.atanor.vrecorder.rpc.services.SnapshotServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class Client {

	private static MainPane view;
	private static EventBus eventBus;
	private static RecordingServiceAsync recordingService;
	private static SnapshotServiceAsync snapshotService;
	private static ConfigServiceAsync configService;
	
	public static MainPane getView() {
		if (view == null) {
			view = new MainPane();
		}
		return view;
	}

	public static RecordingServiceAsync getRecordingService() {
		if (recordingService == null) {
			recordingService = GWT.create(RecordingService.class);
		}
		return recordingService;
	}

	public static SnapshotServiceAsync getSnapshotService() {
		if (snapshotService == null) {
			snapshotService = GWT.create(SnapshotService.class);
		}
		return snapshotService;
	}

	public static ConfigServiceAsync getConfigService() {
		if (configService == null) {
			configService = GWT.create(ConfigService.class);
		}
		return configService;
	}
	
	public static EventBus getEventBus() {
		if (eventBus == null) {
			eventBus = new SimpleEventBus();
		}
		return eventBus;
	}
}
