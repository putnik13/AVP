package com.atanor.smanager.client.event;

import com.google.gwt.event.shared.SimpleEventBus;

public class Client {
	private static SimpleEventBus bus = new SimpleEventBus();
	
	public static SimpleEventBus getBus(){
	    return bus;
	}

}
