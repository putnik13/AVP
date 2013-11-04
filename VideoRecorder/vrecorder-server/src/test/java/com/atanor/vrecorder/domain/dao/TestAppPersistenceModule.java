package com.atanor.vrecorder.domain.dao;

import com.atanor.vrecorder.injector.AppPersistenceModule;
import com.google.inject.persist.jpa.JpaPersistModule;

public class TestAppPersistenceModule extends AppPersistenceModule {

	@Override
	protected void configure() {
		install(new JpaPersistModule("TEST-VRECORDER-JPA"));
		bind(TestJPAInitializer.class).asEagerSingleton();
		super.configure();
	}

}
