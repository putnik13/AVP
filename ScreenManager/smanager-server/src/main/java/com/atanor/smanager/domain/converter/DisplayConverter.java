package com.atanor.smanager.domain.converter;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;

import com.atanor.smanager.domain.entity.Display;
import com.atanor.smanager.rpc.dto.DisplayDto;

public class DisplayConverter extends AbstractConverter<DisplayDto, Display> {

	@Inject
	private PanelLayoutConverter layoutConverter;

	@Override
	public DisplayDto toDto(final Display entity) {
		Validate.notNull(entity, "entity param can not be null");

		final DisplayDto dto = new DisplayDto(entity.getId());
		dto.setWidth(entity.getWidth());
		dto.setHigh(entity.getHigh());
		dto.setLayout(layoutConverter.toDto(entity.getLayout()));

		return dto;
	}

	@Override
	public Display toEntity(final DisplayDto dto) {
		Validate.notNull(dto, "dto param can not be null");

		final Display entity = new Display();
		entity.setWidth(dto.getWidth());
		entity.setHigh(dto.getHigh());
		entity.setLayout(layoutConverter.toEntity(dto.getLayout()));

		return entity;
	}
}
