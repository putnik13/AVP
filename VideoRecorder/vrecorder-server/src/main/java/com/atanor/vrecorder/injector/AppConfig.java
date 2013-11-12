package com.atanor.vrecorder.injector;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import com.atanor.vrecorder.util.AppUtils;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class AppConfig extends GuiceServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		saveServletContext(event.getServletContext());
		super.contextInitialized(event);
	}

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new AppServletModule(), new AppCoreModule(), new AppPersistenceModule());
	}

	private void saveServletContext(final ServletContext context) {
		AppUtils.setServletContext(context);
	}
}
