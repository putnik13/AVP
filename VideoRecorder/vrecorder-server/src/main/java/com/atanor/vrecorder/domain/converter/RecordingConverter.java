package com.atanor.vrecorder.domain.converter;

import java.util.List;

import org.apache.commons.lang3.Validate;

import com.atanor.vrecorder.domain.entity.Recording;
import com.atanor.vrecorder.rpc.dto.RecordingDto;

public class RecordingConverter extends AbstractConverter<RecordingDto, Recording> {

	@Override
	public RecordingDto toDto(final Recording entity) {
		Validate.notNull(entity, "entity param can not be null");

		final RecordingDto dto = new RecordingDto();
		dto.setName(entity.getName());
		dto.setStartTime(entity.getStartTime());
		dto.setEndTime(entity.getEndTime());
		dto.setDuration(entity.getDuration());

		return dto;
	}

	public List<RecordingDto> toListDto(final List<Recording> entities) {
		return convertEntityList(this, entities);
	}
}
