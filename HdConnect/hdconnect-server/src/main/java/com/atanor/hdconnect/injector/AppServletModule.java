package com.atanor.hdconnect.injector;

import java.util.Map;

import org.atmosphere.cpr.AtmosphereServlet;

import com.atanor.hdconnect.server.ManagedGWTResource;
import com.google.common.collect.Maps;
import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;

public class AppServletModule extends ServletModule {

	private static final String BASE_URL = "/HdConnect";

	@Override
	protected void configureServlets() {
		bind(ManagedGWTResource.class);
		bind(AtmosphereServlet.class).in(Scopes.SINGLETON);
		
		final Map<String, String> params = Maps.newHashMap();
		params.put("org.atmosphere.cpr.packages", "com.atanor.hdconnect.server");
		serve(BASE_URL + "/atmosphere/*").with(AtmosphereServlet.class, params);
	}

}
