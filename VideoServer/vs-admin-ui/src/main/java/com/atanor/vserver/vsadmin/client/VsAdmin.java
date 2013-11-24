package com.atanor.vserver.vsadmin.client;

import com.atanor.vserver.common.services.Services;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class VsAdmin implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Button button = new Button("Hello from Admin");
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Services.getHelloService().hello("Admin", new AsyncCallback<Void>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						
					}});
			}
		});
		
		RootPanel.get().add(button);
	}
}
