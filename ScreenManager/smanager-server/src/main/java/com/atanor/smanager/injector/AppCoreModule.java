package com.atanor.smanager.injector;

import com.atanor.smanager.services.EquipmentConfigService;
import com.atanor.smanager.services.EquipmentConfigServiceImpl;
import com.atanor.smanager.services.hardware.HardwareFacade;
import com.atanor.smanager.services.hardware.HardwareFacadeImpl;
import com.google.inject.AbstractModule;

public class AppCoreModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(EquipmentConfigService.class).to(EquipmentConfigServiceImpl.class);
		bind(HardwareFacade.class).to(HardwareFacadeImpl.class);
	}

}
