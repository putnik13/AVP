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
		
//		bind(new TypeLiteral<Map<String, String>>() {}).
//		annotatedWith(Names.named(AtmosphereGuiceServlet.JERSEY_PROPERTIES)).toInstance(Collections.<String, String> emptyMap());
//		
		final Map<String, String> params = Maps.newHashMap();
		//params.put(PackagesResourceConfig.PROPERTY_PACKAGES, "com.atanor.hdconnect.server");
		params.put("org.atmosphere.cpr.packages", "com.atanor.hdconnect.server");
//		params.put("org.atmosphere.useWebSocket", "true");
//		params.put("org.atmosphere.useNative", "true");

		serve(BASE_URL + "/atmosphere/*").with(AtmosphereServlet.class, params);
		//serve(BASE_URL + "/rest/*").with(GuiceContainer.class);
	}

}
