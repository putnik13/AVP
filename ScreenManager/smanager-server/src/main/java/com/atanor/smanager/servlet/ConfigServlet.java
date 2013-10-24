package com.atanor.smanager.servlet;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.atanor.smanager.domain.converter.HardwareConverter;
import com.atanor.smanager.domain.converter.PresetConverter;
import com.atanor.smanager.rpc.dto.HardwareDto;
import com.atanor.smanager.rpc.dto.PresetDto;
import com.atanor.smanager.rpc.services.ConfigService;
import com.atanor.smanager.services.EquipmentConfigService;
import com.atanor.smanager.services.hardware.HardwareFacade;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Singleton
@SuppressWarnings("serial")
public class ConfigServlet extends RemoteServiceServlet implements ConfigService {

	@Inject
	private EquipmentConfigService equipService;

	@Inject
	private HardwareConverter hardwareConverter;

	@Inject
	private PresetConverter presetConverter;

	@Inject
	private HardwareFacade hardwareFacade;

	public HardwareDto getHardwareConfiguration() {
		return hardwareConverter.toDto(equipService.getActiveHardware());
	}

	@Override
	public Boolean applyPreset(PresetDto preset) {
		return hardwareFacade.sendPresetConfiguration(presetConverter.toEntity(preset));
	}

	@Override
	public PresetDto savePreset(PresetDto preset) {
		equipService.savePreset(presetConverter.toEntity(preset));
		return presetConverter.toDto(equipService.getPresetById(preset.getId()));
	}

}
