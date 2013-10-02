package com.atanor.smanager.server.services;

import java.util.Arrays;
import java.util.List;

import com.atanor.smanager.rpc.services.ConfigService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ConfigServiceImpl extends RemoteServiceServlet implements
		ConfigService {
	
	public List<String> getAvailableConfigurations() {
		return Arrays.asList("CONFIG1","CONFIG2","CONFIG3");
	}

}
