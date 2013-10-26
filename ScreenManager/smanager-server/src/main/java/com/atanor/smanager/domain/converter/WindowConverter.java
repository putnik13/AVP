package com.atanor.smanager.domain.converter;

import org.apache.commons.lang3.Validate;

import com.atanor.smanager.domain.entity.Window;
import com.atanor.smanager.rpc.dto.WindowDto;

public class WindowConverter extends AbstractConverter<WindowDto, Window> {

	@Override
	public WindowDto toDto(final Window entity) {
		Validate.notNull(entity, "entity param can not be null");
		
		final WindowDto dto = new WindowDto(entity.getId());
		dto.setName(entity.getName());
		dto.setSource(entity.getSource());
		dto.setXTopLeft(entity.getXTopLeft());
		dto.setYTopLeft(entity.getYTopLeft());
		dto.setXBottomRight(entity.getXBottomRight());
		dto.setYBottomRight(entity.getYBottomRight());
		dto.setZIndex(entity.getZIndex());
		
		return dto;
	}

	@Override
	public Window toEntity(final WindowDto dto) {
		Validate.notNull(dto, "dto param can not be null");
		
		final Window entity = new Window(dto.getId());
		entity.setName(dto.getName());
		entity.setSource(dto.getSource());
		entity.setXTopLeft(dto.getXTopLeft());
		entity.setYTopLeft(dto.getYTopLeft());
		entity.setXBottomRight(dto.getXBottomRight());
		entity.setYBottomRight(dto.getYBottomRight());
		entity.setZIndex(dto.getZIndex());
		
		entity.setModified(dto.isModified());
		
		return entity;
	}

}
