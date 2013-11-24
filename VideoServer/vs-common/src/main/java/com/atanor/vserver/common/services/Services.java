package com.atanor.vserver.common.services;

import com.google.gwt.core.client.GWT;

public class Services {

	private static HelloServiceAsync helloService;

	public static HelloServiceAsync getHelloService() {
		if (helloService == null) {
			helloService = GWT.create(HelloService.class);
		}
		return helloService;
	}
}
