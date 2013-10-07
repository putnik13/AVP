package com.atanor.smanager.domain.converter;

import com.atanor.smanager.domain.entity.Preset;
import com.atanor.smanager.rpc.dto.PresetDto;

public class PresetConverter extends AbstractConverter<PresetDto, Preset>{
	
	private DisplayLayoutConverter lConverter;
	private ActiveScreenConverter sConverter;
	
	@Override
	public PresetDto convert(Preset entity){
		final PresetDto dto = new PresetDto(entity.getId());
		dto.setLayout(lConverter.convert(entity.getLayout()));
		dto.setActiveScreens(convertList(entity.getActiveScreens(), sConverter));
		
		return dto;
	}
}
