package com.atanor.vrecorder.client.ui;

import java.util.List;

import com.atanor.vrecorder.client.Client;
import com.atanor.vrecorder.rpc.dto.RecordingDto;
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
				refreshRecordings();
			}
		});
	}

	public void refreshRecordings() {
		Client.getRecordingService().getRecordings(new AsyncCallback<List<RecordingDto>>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Can not read recordings data");
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(List<RecordingDto> result) {
				view.setRecordings(result);
			}
		});
	}
}
