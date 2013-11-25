package com.atanor.vserver.vsclient.client;

import com.atanor.vserver.vsclient.client.presenter.MainPanePresenter;
import com.atanor.vserver.vsclient.client.ui.MainPane;
import com.atanor.vserver.vsclient.client.ui.MainPaneImpl;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class Client {

	private static MainPane view;
	private static MainPanePresenter presenter;
	private static EventBus eventBus;

	public static MainPane getView() {
		if (view == null) {
			view = new MainPaneImpl();
		}
		return view;
	}

	public static MainPanePresenter getPresenter() {
		if (presenter == null) {
			presenter = new MainPanePresenter();
		}
		return presenter;
	}

	public static void bind() {
		getView().setPresenter(getPresenter());
		getPresenter().setView(getView());
	}

	public static EventBus getEventBus() {
		if (eventBus == null) {
			eventBus = new SimpleEventBus();
		}
		return eventBus;
	}
}
