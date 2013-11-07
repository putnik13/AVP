package com.atanor.vrecorder.servlet;

import javax.inject.Singleton;

import com.atanor.vrecorder.rpc.services.ConfigService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Singleton
@SuppressWarnings("serial")
public class ConfigServlet extends RemoteServiceServlet implements ConfigService {

	@Override
	public Long getAvailableSpaceSize() {
		System.out.println("getAvailableSpaceSize() called ..");
		return null;
	}

}
