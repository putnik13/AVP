package com.atanor.vrecorder.domain.converter;

import com.atanor.vrecorder.domain.entity.AbstractEntity;
import com.atanor.vrecorder.rpc.dto.AbstractDto;

@SuppressWarnings("rawtypes")
public interface Converter<D extends AbstractDto, E extends AbstractEntity> {

	D toDto(E entity);

}
