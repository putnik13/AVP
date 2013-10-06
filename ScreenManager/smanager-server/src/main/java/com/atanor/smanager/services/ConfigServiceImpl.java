package com.atanor.smanager.services;

import java.util.Arrays;
import java.util.List;

import com.atanor.smanager.rpc.services.ConfigService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Singleton;

/**
 * The server side implementation of the RPC service.
 */
@Singleton
@SuppressWarnings("serial")
public class ConfigServiceImpl extends RemoteServiceServlet implements
		ConfigService {
	
	public List<String> getAvailableConfigurations() {
		return Arrays.asList("CONFIG1","CONFIG2","CONFIG3");
	}

}
