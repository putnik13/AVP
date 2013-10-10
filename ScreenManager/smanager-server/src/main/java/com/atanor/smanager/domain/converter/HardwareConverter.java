package com.atanor.smanager.domain.converter;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;

import com.atanor.smanager.domain.entity.Hardware;
import com.atanor.smanager.rpc.dto.HardwareDto;

public class HardwareConverter extends AbstractConverter<HardwareDto, Hardware> {

	@Inject
	private DisplayConverter displayConverter;
	
	@Inject
	private PresetConverter presetConverter;

	@Override
	public HardwareDto convert(Hardware entity) {
		Validate.notNull(entity, "entity param can not be null");

		final HardwareDto dto = new HardwareDto(entity.getId());
		dto.setModelName(entity.getModelName());
		dto.setSources(entity.getSources());
		dto.setDisplay(displayConverter.convert(entity.getDisplay()));
		dto.setPresets(convertList(presetConverter, entity.getPresets()));

		return dto;
	}
}
