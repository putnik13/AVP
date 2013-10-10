package com.atanor.smanager.servlet;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.atanor.smanager.rpc.services.ConfigService;
import com.atanor.smanager.services.PresetService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Singleton
@SuppressWarnings("serial")
public class ConfigServlet extends RemoteServiceServlet implements
		ConfigService {

	@Inject
	private PresetService presetService;
	
	public List<String> getAvailableConfigurations() {
		return Arrays.asList("CONFIG1","CONFIG2","CONFIG3");
	}

}
