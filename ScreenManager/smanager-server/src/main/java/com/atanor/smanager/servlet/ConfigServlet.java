package com.atanor.smanager.servlet;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.atanor.smanager.domain.converter.HardwareConverter;
import com.atanor.smanager.rpc.dto.HardwareDto;
import com.atanor.smanager.rpc.services.ConfigService;
import com.atanor.smanager.services.EquipmentConfigService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Singleton
@SuppressWarnings("serial")
public class ConfigServlet extends RemoteServiceServlet implements ConfigService {

	@Inject
	private EquipmentConfigService equipService;

	@Inject
	private HardwareConverter hardwareConverter;

	public HardwareDto getHardwareConfiguration() {
		return hardwareConverter.convert(equipService.getActiveHardware());
	}

}
