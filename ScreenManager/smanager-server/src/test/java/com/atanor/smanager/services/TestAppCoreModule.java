package com.atanor.smanager.services;

import com.google.inject.AbstractModule;

public class TestAppCoreModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(EquipmentConfigService.class).to(EquipmentConfigServiceImpl.class);
	}

}
