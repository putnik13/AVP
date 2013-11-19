package com.atanor.hdconnect.injector;

import java.util.Map;

import org.atmosphere.cpr.AtmosphereServlet;

import com.atanor.hdconnect.async.ImageResource;
import com.atanor.hdconnect.servlet.CloseSessionServlet;
import com.atanor.hdconnect.servlet.OpenSessionServlet;
import com.google.common.collect.Maps;
import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;

public class AppServletModule extends ServletModule {

	private static final String BASE_URL = "/HdConnect";

	@Override
	protected void configureServlets() {
		bind(ImageResource.class);
		bind(AtmosphereServlet.class).in(Scopes.SINGLETON);
		
		final Map<String, String> params = Maps.newHashMap();
		params.put("org.atmosphere.cpr.packages", "com.atanor.hdconnect.server");
		serve(BASE_URL + "/atmosphere/*").with(AtmosphereServlet.class, params);
		serve(BASE_URL + "/open").with(OpenSessionServlet.class);
		serve(BASE_URL + "/close").with(CloseSessionServlet.class);
	}

}
