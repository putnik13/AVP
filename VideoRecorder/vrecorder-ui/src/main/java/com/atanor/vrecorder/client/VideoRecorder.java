package com.atanor.vrecorder.client;

import com.atanor.vrecorder.client.ui.MainPanePresenter;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class VideoRecorder implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		RootPanel.get().add(Client.getView());
		bind();
	}

	private void bind() {
		final MainPanePresenter presenter = new MainPanePresenter(Client.getView());
		Client.getView().setPresenter(presenter);
	}

}
