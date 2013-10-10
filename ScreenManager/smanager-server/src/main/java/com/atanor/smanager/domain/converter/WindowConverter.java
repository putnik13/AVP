package com.atanor.smanager.domain.converter;

import org.apache.commons.lang3.Validate;

import com.atanor.smanager.domain.entity.Window;
import com.atanor.smanager.rpc.dto.WindowDto;

public class WindowConverter extends AbstractConverter<WindowDto, Window> {

	@Override
	public WindowDto convert(Window entity) {
		Validate.notNull(entity, "entity param can not be null");
		
		final WindowDto dto = new WindowDto(entity.getId());
		dto.setName(entity.getName());
		dto.setSource(entity.getSource());
		dto.setXTopLeft(entity.getXTopLeft());
		dto.setYTopLeft(entity.getYTopLeft());
		dto.setXBottomRigh(entity.getXBottomRight());
		dto.setYBottomRight(entity.getYBottomRight());
		dto.setZIndex(entity.getZIndex());
		
		return dto;
	}

}
