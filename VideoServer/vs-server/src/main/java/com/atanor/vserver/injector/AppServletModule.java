package com.atanor.vserver.injector;

import java.util.Map;

import org.atmosphere.cpr.AtmosphereServlet;

import com.atanor.vserver.async.SnapshotResource;
import com.atanor.vserver.servlet.HelloServlet;
import com.atanor.vserver.servlet.SessionCloseServlet;
import com.atanor.vserver.servlet.SessionOpenServlet;
import com.google.common.collect.Maps;
import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;

public class AppServletModule extends ServletModule {

	private static final String BASE_ADMIN_URL = "/VsAdmin";
	private static final String BASE_CLIENT_URL = "/VsClient";

	@Override
	protected void configureServlets() {
		// PersistFilter provides a new instance of EntityManager for each
		// request to the servlet container (Open Session In View pattern)
		// install(new JpaPersistModule("VRECORDER-JPA"));
		// filter("/*").through(PersistFilter.class);

		bind(SnapshotResource.class);
		bind(AtmosphereServlet.class).in(Scopes.SINGLETON);
		
		final Map<String, String> params = Maps.newHashMap();
		params.put("org.atmosphere.cpr.packages", "com.atanor.vserver.async");
		serve(BASE_CLIENT_URL + "/atmosphere/*").with(AtmosphereServlet.class, params);
		serve(BASE_CLIENT_URL + "/open").with(SessionOpenServlet.class);
		serve(BASE_CLIENT_URL + "/close").with(SessionCloseServlet.class);
		
		serve(BASE_ADMIN_URL + "/hello").with(HelloServlet.class);
		serve(BASE_CLIENT_URL + "/hello").with(HelloServlet.class);
		
	}

}
