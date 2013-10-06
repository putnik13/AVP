package com.atanor.smanager.injector;

import com.atanor.smanager.domain.dao.DisplayDao;
import com.atanor.smanager.domain.dao.GenericDao;
import com.atanor.smanager.domain.dao.HardwareDao;
import com.atanor.smanager.domain.dao.PresetDao;
import com.atanor.smanager.domain.entity.Display;
import com.atanor.smanager.domain.entity.Hardware;
import com.atanor.smanager.domain.entity.Preset;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class AppPersistenceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<GenericDao<Hardware, Long>>() {}).to(new TypeLiteral<HardwareDao>() {});
		bind(new TypeLiteral<GenericDao<Display, Long>>() {}).to(new TypeLiteral<DisplayDao>() {});
		bind(new TypeLiteral<GenericDao<Preset, Long>>() {}).to(new TypeLiteral<PresetDao>() {});
	}

}
