package com.atanor.smanager.domain.converter;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;

import com.atanor.smanager.domain.entity.Display;
import com.atanor.smanager.rpc.dto.DisplayDto;

public class DisplayConverter extends AbstractConverter<DisplayDto, Display> {

	@Inject
	private PanelLayoutConverter layoutConverter;

	@Override
	public DisplayDto convert(Display entity) {
		Validate.notNull(entity, "entity param can not be null");

		final DisplayDto dto = new DisplayDto(entity.getId());
		dto.setWidth(entity.getWidth());
		dto.setHigh(entity.getHigh());
		dto.setLayout(layoutConverter.convert(entity.getLayout()));

		return dto;
	}
}
