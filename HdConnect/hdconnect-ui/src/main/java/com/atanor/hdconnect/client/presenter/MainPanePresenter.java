package com.atanor.hdconnect.client.presenter;

import com.atanor.hdconnect.client.events.SnapshotReceivedEvent;
import com.atanor.hdconnect.client.events.SnapshotReceivedHandler;
import com.atanor.hdconnect.client.ui.MainPane;

public class MainPanePresenter implements SnapshotReceivedHandler {

	private MainPane view;

	public void setView(final MainPane view) {
		this.view = view;
	}

	@Override
	public void onSnapshotReceived(final SnapshotReceivedEvent event) {
		view.addSnapshot(event.getSnapshot());
	}

}
