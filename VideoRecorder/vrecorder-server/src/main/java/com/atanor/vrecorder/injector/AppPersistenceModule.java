package com.atanor.vrecorder.injector;

import com.atanor.vrecorder.domain.dao.GenericDao;
import com.atanor.vrecorder.domain.dao.RecordingDao;
import com.atanor.vrecorder.domain.entity.Recording;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class AppPersistenceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<GenericDao<Recording, Long>>() {}).to(new TypeLiteral<RecordingDao>() {});
	}

}
