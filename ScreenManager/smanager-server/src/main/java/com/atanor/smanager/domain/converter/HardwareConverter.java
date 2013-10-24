package com.atanor.smanager.domain.converter;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;

import com.atanor.smanager.domain.entity.Hardware;
import com.atanor.smanager.rpc.dto.HardwareDto;
import com.google.common.collect.Lists;

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

		// wrapping is required since hibernate collections can not be
		// serialized and sent to client
		dto.setSources(wrap(entity.getSources()));

		dto.setDisplay(displayConverter.toDto(entity.getDisplay()));
		dto.setPresets(convertEntityList(presetConverter, entity.getPresets()));

		return dto;
	}

	@Override
	public Hardware toEntity(final HardwareDto dto) {
		Validate.notNull(dto, "dto param can not be null");

		final Hardware entity = new Hardware(dto.getId());
		entity.setModelName(dto.getModelName());
		entity.setSources(dto.getSources());
		entity.setDisplay(displayConverter.toEntity(dto.getDisplay()));
		entity.setPresets(convertDtoList(presetConverter, dto.getPresets()));

		return entity;
	}

	private static List<String> wrap(List<String> entities) {
		List<String> wrapper = Lists.newArrayList();
		wrapper.addAll(entities);
		return wrapper;
	}
}
