package com.atanor.vrecorder.client.ui;

import java.util.List;

import com.atanor.vrecorder.client.Client;
import com.atanor.vrecorder.client.events.GetAvailableSpaceSizeEvent;
import com.atanor.vrecorder.client.events.GetAvailableSpaceSizeHandler;
import com.atanor.vrecorder.client.events.GetSnapshotEvent;
import com.atanor.vrecorder.client.events.GetSnapshotHandler;
import com.atanor.vrecorder.rpc.dto.RecordingDto;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;

public class MainPanePresenter implements GetSnapshotHandler, GetAvailableSpaceSizeHandler {

	private MainPane view;
	private Timer snapshotTimer;
	private Timer availableSpaceTimer;

	public MainPanePresenter(final MainPane view) {
		this.view = view;
		Client.getEventBus().addHandler(GetSnapshotEvent.getType(), this);
		Client.getEventBus().addHandler(GetAvailableSpaceSizeEvent.getType(), this);
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
				startGettingSnapshots();
				startGettingAvailableSpaceSize();
			}
		});
	}

	public void stopRecording() {
		Client.getRecordingService().stopRecording(new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				snapshotTimer.cancel();
				availableSpaceTimer.cancel();
				SC.say("Error. Can not stop recording");
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(Boolean result) {
				snapshotTimer.cancel();
				availableSpaceTimer.cancel();
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

	private void startGettingSnapshots() {
		snapshotTimer = new Timer() {
			public void run() {
				Client.getEventBus().fireEvent(new GetSnapshotEvent());
			}
		};
		snapshotTimer.scheduleRepeating(5000);
	}

	private void startGettingAvailableSpaceSize() {
		availableSpaceTimer = new Timer() {
			public void run() {
				Client.getEventBus().fireEvent(new GetAvailableSpaceSizeEvent());
			}
		};
		availableSpaceTimer.scheduleRepeating(5000);
	}

	@Override
	public void onGetSnapshot(GetSnapshotEvent event) {
		Client.getSnapshotService().getSnapshot(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				snapshotTimer.cancel();
				SC.say("Error. Can not get snapshot");
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(String encodedSnapshot) {
				if (encodedSnapshot != null) {
					view.setSnapshot(encodedSnapshot);
				}
			}
		});
	}

	@Override
	public void onGetAvailableSpaceSize(GetAvailableSpaceSizeEvent event) {
		Client.getConfigService().getAvailableSpaceSize(new AsyncCallback<Long>() {

			@Override
			public void onFailure(Throwable caught) {
				availableSpaceTimer.cancel();
				SC.say("Error. Can not get available space on disk");
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(Long result) {
				view.setAvailableSpaceSize(result);
			}
		});
	}

}
