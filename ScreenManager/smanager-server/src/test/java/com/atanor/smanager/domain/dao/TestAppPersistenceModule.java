package com.atanor.smanager.domain.dao;

import com.atanor.smanager.injector.AppPersistenceModule;
import com.google.inject.persist.jpa.JpaPersistModule;

public class TestAppPersistenceModule extends AppPersistenceModule {

	@Override
	protected void configure() {
		install(new JpaPersistModule("SMANAGER-JPA"));
		bind(TestJPAInitializer.class).asEagerSingleton();
		super.configure();
	}

}
