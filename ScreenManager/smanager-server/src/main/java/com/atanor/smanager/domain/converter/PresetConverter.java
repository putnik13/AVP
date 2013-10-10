package com.atanor.smanager.domain.converter;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;

import com.atanor.smanager.domain.entity.Preset;
import com.atanor.smanager.rpc.dto.PresetDto;

public class PresetConverter extends AbstractConverter<PresetDto, Preset> {

	@Inject
	private WindowConverter winConverter;

	@Override
	public PresetDto convert(Preset entity) {
		Validate.notNull(entity, "entity param can not be null");
		
		final PresetDto dto = new PresetDto(entity.getId());
		dto.setWindows(convertList(winConverter, entity.getWindows()));

		return dto;
	}
}
