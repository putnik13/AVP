package com.atanor.smanager.client;

import com.atanor.smanager.rpc.dto.HardwareDto;
import com.atanor.smanager.rpc.services.ConfigService;
import com.atanor.smanager.rpc.services.ConfigServiceAsync;
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
	private final ConfigServiceAsync configService = GWT.create(ConfigService.class);

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
		configService.getHardwareConfiguration(new AsyncCallback<HardwareDto>() {

			@Override
			public void onSuccess(HardwareDto result) {
				SC.say("Fetched configuration for following hardware: " + result.getModelName());
			}

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Configurations are not available!");
				caught.printStackTrace();
			}
		});
	}

}
