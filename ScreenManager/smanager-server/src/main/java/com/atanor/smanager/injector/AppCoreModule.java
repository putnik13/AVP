package com.atanor.smanager.injector;

import com.atanor.smanager.services.EquipmentConfigService;
import com.atanor.smanager.services.EquipmentConfigServiceMockImpl;
import com.google.inject.AbstractModule;

public class AppCoreModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(EquipmentConfigService.class).to(EquipmentConfigServiceMockImpl.class);
	}

}
