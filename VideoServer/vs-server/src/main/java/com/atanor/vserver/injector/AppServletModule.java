package com.atanor.vserver.injector;

import com.atanor.vserver.servlet.HelloServlet;
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

		serve(BASE_ADMIN_URL + "/hello").with(HelloServlet.class);
		serve(BASE_CLIENT_URL + "/hello").with(HelloServlet.class);
	}

}
