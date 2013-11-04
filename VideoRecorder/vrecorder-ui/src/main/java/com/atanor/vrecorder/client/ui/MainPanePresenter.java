package com.atanor.vrecorder.client.ui;

import com.atanor.vrecorder.client.Client;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;

public class MainPanePresenter {

	private MainPane view;

	public MainPanePresenter(final MainPane view) {
		this.view = view;
	}

	public void startRecording() {
		Client.getRecordingService().startRecording(new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Can not start recording");
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(Boolean result) {
				view.onRecordingStarted();
			}
		});
	}

	public void stopRecording() {
		Client.getRecordingService().stopRecording(new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Can not stop recording");
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(Boolean result) {
				view.onRecordingStopped();
			}
		});
	}
}
