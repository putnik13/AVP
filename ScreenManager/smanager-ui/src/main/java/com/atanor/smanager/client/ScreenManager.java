package com.atanor.smanager.client;

import java.util.List;

import com.atanor.smanager.rpc.services.ConfigService;
import com.atanor.smanager.rpc.services.ConfigServiceAsync;
import com.google.common.base.Joiner;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ScreenManager implements EntryPoint {

	/**
	 * Create a remote service proxy to talk to the server-side Config service.
	 */
	private final ConfigServiceAsync configService = GWT
			.create(ConfigService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final IButton button = new IButton();
		button.setTitle("Click!");

		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				getConfigurations();
			}

		});

		// Use RootPanel.get() to get the entire body element
		RootPanel.get().add(button);
	}

	private void getConfigurations() {
		configService
				.getAvailableConfigurations(new AsyncCallback<List<String>>() {

					@Override
					public void onSuccess(List<String> result) {
						String joined = Joiner.on("\t").join(result);
						SC.say("Hello Sergey! :) The server has following configurations: "
								+ joined);
					}

					@Override
					public void onFailure(Throwable caught) {
						SC.say("Configurations are not available!");
					}
				});
	}

}
