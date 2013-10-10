package com.atanor.smanager.domain.converter;

import org.apache.commons.lang3.Validate;

import com.atanor.smanager.domain.entity.Preset;
import com.atanor.smanager.rpc.dto.PresetDto;
import com.google.inject.Inject;

public class PresetConverter extends AbstractConverter<PresetDto, Preset> {

	@Inject
	private PanelLayoutConverter layoutConverter;
	@Inject
	private WindowConverter winConverter;

	@Override
	public PresetDto convert(Preset entity) {
		Validate.notNull(entity, "entity param can not be null");
		
		final PresetDto dto = new PresetDto(entity.getId());
		dto.setLayout(layoutConverter.convert(entity.getLayout()));
		dto.setWindows(convertList(winConverter, entity.getWindows()));

		return dto;
	}
}
