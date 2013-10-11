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
	public HardwareDto toDto(final Hardware entity) {
		Validate.notNull(entity, "entity param can not be null");

		final HardwareDto dto = new HardwareDto(entity.getId());
		dto.setModelName(entity.getModelName());
		dto.setSources(entity.getSources());
		dto.setDisplay(displayConverter.toDto(entity.getDisplay()));
		dto.setPresets(convertEntityList(presetConverter, entity.getPresets()));

		return dto;
	}

	@Override
	public Hardware toEntity(final HardwareDto dto) {
		Validate.notNull(dto, "dto param can not be null");
		
		final Hardware entity = new Hardware();
		entity.setModelName(dto.getModelName());
		entity.setSources(dto.getSources());
		entity.setDisplay(displayConverter.toEntity(dto.getDisplay()));
		entity.setPresets(convertDtoList(presetConverter, dto.getPresets()));
		
		return entity;
	}
}
