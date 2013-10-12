package com.atanor.smanager.client;

import com.atanor.smanager.client.ui.MainArea;
import com.atanor.smanager.client.ui.NavigationArea;
import com.atanor.smanager.rpc.dto.HardwareDto;
import com.atanor.smanager.rpc.services.ConfigService;
import com.atanor.smanager.rpc.services.ConfigServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ScreenManager implements EntryPoint {

	private HLayout westLayout;
	private HLayout eastLayout;
	private HLayout northLayout;
	private VLayout mainLayout;

	/**
	 * Create a remote service proxy to talk to the server-side Config service.
	 */
	private final ConfigServiceAsync configService = GWT
			.create(ConfigService.class);
	SimpleEventBus bus = new SimpleEventBus();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		Window.enableScrolling(false);
		Window.setMargin("0px");

		// main layout occupies the whole area
		mainLayout = new VLayout();
		mainLayout.setWidth100();
		mainLayout.setHeight100();

		VLayout vLayout = new VLayout();

		westLayout = new NavigationArea(bus);
		westLayout.setWidth("15%");

		eastLayout = new MainArea(bus);
		eastLayout.setWidth("85%");

		northLayout = new HLayout();
		northLayout.setMembers(westLayout, eastLayout);

		mainLayout.addMember(northLayout);

		final NavigationArea navArea = new NavigationArea(bus);
		final MainArea area = new MainArea(bus);
		area.setWidth("70%");
		area.setHeight("400px");

		RootPanel.get().add(mainLayout);

	}

}
