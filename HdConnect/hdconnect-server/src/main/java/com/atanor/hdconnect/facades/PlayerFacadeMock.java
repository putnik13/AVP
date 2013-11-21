package com.atanor.hdconnect.facades;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import com.atanor.hdconnect.async.AsyncConnector;
import com.atanor.hdconnect.entity.Snapshot;
import com.atanor.hdconnect.events.CloseSessionEvent;
import com.atanor.hdconnect.events.GetSnapshotEvent;
import com.atanor.hdconnect.events.OpenSessionEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.gson.Gson;

public class PlayerFacadeMock {

	private static final int DELAY_TIME = 0;
	private static final int INTERVAL_TIME = 5000;
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

		final String jsonRepresentation = gson.toJson(new Snapshot("aaaaabbb", "100", "200"));
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
