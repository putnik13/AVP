package com.atanor.vrecorder.client;

import com.atanor.vrecorder.client.ui.MainPane;
import com.atanor.vrecorder.rpc.services.RecordingService;
import com.atanor.vrecorder.rpc.services.RecordingServiceAsync;
import com.google.gwt.core.client.GWT;

public class Client {

	private static MainPane view;
	private static RecordingServiceAsync recordingService;

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
}
