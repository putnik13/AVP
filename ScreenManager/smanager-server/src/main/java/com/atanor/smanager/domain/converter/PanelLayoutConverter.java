package com.atanor.smanager.domain.converter;

import org.apache.commons.lang3.Validate;

import com.atanor.smanager.domain.entity.PanelLayout;
import com.atanor.smanager.rpc.dto.PanelLayoutDto;

public class PanelLayoutConverter implements Converter<PanelLayoutDto, PanelLayout> {

	@Override
	public PanelLayoutDto convert(PanelLayout entity) {
		Validate.notNull(entity, "entity param can not be null");		
		
		final PanelLayoutDto dto = new PanelLayoutDto();
		dto.setName(entity.getDescription());
		dto.setPanelQuantity(entity.getPanelQuantity());
		
		return dto;
	}

}
