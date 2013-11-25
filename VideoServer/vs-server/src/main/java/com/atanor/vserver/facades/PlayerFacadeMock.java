package com.atanor.vserver.facades;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import com.atanor.vserver.async.AsyncConnector;
import com.atanor.vserver.common.entity.Snapshot;
import com.atanor.vserver.events.CloseSessionEvent;
import com.atanor.vserver.events.GetSnapshotEvent;
import com.atanor.vserver.events.OpenSessionEvent;
import com.atanor.vserver.util.ImageDecoder;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.gson.Gson;

public class PlayerFacadeMock {

	private static final int DELAY_TIME = 0;
	private static final int INTERVAL_TIME = 3000;
	private final EventBus eventBus;
	private Timer timer;
	private Gson gson = new Gson();

	@Inject
	public PlayerFacadeMock(final EventBus eventBus) {
		this.eventBus = eventBus;
		eventBus.register(this);
	}

	@Subscribe
	public void onGetSnapshot(final GetSnapshotEvent event) {
		System.out.println("onGetSnapshot() called");

		final File file = new File("d:/temp/FOR DESIGN/2013-08-03_102606.png");
		if (!file.exists()) {
			throw new IllegalStateException("Snapshot is not exist!");
		}

		final String encodedImage = ImageDecoder.encodeImage(file);
		final String jsonRepresentation = gson.toJson(new Snapshot(encodedImage, "795", "586"));
		AsyncConnector.broadcast(jsonRepresentation);
	}

	@Subscribe
	public void onOpenSession(final OpenSessionEvent event) {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				eventBus.post(new GetSnapshotEvent());
			}
		}, DELAY_TIME, INTERVAL_TIME);
	}

	@Subscribe
	public void onCloseSession(final CloseSessionEvent event) {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}
}
