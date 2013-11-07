package com.atanor.vrecorder.client;

import com.atanor.vrecorder.client.events.GetAvailableSpaceSizeEvent;
import com.atanor.vrecorder.client.ui.MainPanePresenter;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class VideoRecorder implements EntryPoint {

	private MainPanePresenter presenter;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		RootPanel.get().add(Client.getView());
		bind();

		initRecordings();
		initAvailabaleSpaceSize();
	}

	private void bind() {
		presenter = new MainPanePresenter(Client.getView());
		Client.getView().setPresenter(presenter);
	}

	private void initRecordings() {
		presenter.refreshRecordings();
	}

	private void initAvailabaleSpaceSize() {
		Client.getEventBus().fireEvent(new GetAvailableSpaceSizeEvent());
	}
}
