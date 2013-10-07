package com.atanor.smanager.domain.converter;

import com.atanor.smanager.domain.entity.ActiveScreen;
import com.atanor.smanager.rpc.dto.ActiveScreenDto;

public class ActiveScreenConverter extends AbstractConverter<ActiveScreenDto, ActiveScreen>{

	@Override
	public ActiveScreenDto convert(ActiveScreen entity) {
		final ActiveScreenDto dto = new ActiveScreenDto();
		return dto;
	}

}
