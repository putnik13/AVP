package com.atanor.smanager.domain.converter;

import com.atanor.smanager.domain.entity.AbstractEntity;
import com.atanor.smanager.rpc.dto.AbstractDto;

@SuppressWarnings("rawtypes")
public interface Converter<D extends AbstractDto, E extends AbstractEntity> {

	D toDto(E entity);

	E toEntity(D dto);

}
