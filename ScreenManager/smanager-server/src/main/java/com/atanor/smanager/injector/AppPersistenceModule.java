package com.atanor.smanager.injector;

import com.atanor.smanager.persistence.dao.DisplayDAO;
import com.atanor.smanager.persistence.dao.GenericDAO;
import com.atanor.smanager.persistence.dao.HardwareDAO;
import com.atanor.smanager.persistence.entity.Display;
import com.atanor.smanager.persistence.entity.Hardware;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.persist.jpa.JpaPersistModule;

public class AppPersistenceModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new JpaPersistModule("SMANAGER-JPA"));
		bind(new TypeLiteral<GenericDAO<Hardware, Long>>() {}).to(new TypeLiteral<HardwareDAO>() {});
		bind(new TypeLiteral<GenericDAO<Display, Long>>() {}).to(new TypeLiteral<DisplayDAO>() {});
	}

}
