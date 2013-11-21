package com.atanor.hdconnect.client;

import com.atanor.hdconnect.client.presenter.MainPanePresenter;
import com.atanor.hdconnect.client.ui.MainPane;
import com.atanor.hdconnect.client.ui.MainPaneImpl;
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
