package com.atanor.vrecorder.domain.converter;

import java.util.List;

import org.apache.commons.lang3.Validate;

import com.atanor.vrecorder.domain.entity.Recording;
import com.atanor.vrecorder.rpc.dto.RecordingDto;

public class RecordingConverter extends AbstractConverter<RecordingDto, Recording> {

	@Override
	public RecordingDto toDto(final Recording entity) {
		Validate.notNull(entity, "entity param can not be null");

		final RecordingDto dto = new RecordingDto(entity.getId());
		dto.setName(entity.getName());
		dto.setStartTime(entity.getStartTime());
		dto.setEndTime(entity.getEndTime());
		dto.setDuration(entity.getDuration());
		dto.setEncodedImage(entity.getImageBlob());
		dto.setOutdated(entity.isOutdated());

		return dto;
	}

	public List<RecordingDto> toListDto(final List<Recording> entities) {
		return convertEntityList(this, entities);
	}

	@Override
	public Recording toEntity(final RecordingDto dto) {
		Validate.notNull(dto, "dto param can not be null");

		final Recording entity = new Recording(dto.getId());
		entity.setName(dto.getName());
		entity.setStartTime(dto.getStartTime());
		entity.setEndTime(dto.getEndTime());
		entity.setDuration(dto.getDuration());
		entity.setOutdated(dto.isOutdated());

		return entity;
	}

	public List<Recording> toListEntities(final List<RecordingDto> dtos) {
		return convertDtoList(this, dtos);
	}
}
