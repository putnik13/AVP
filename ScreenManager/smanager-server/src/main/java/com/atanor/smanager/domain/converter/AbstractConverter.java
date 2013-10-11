package com.atanor.smanager.domain.converter;

import java.util.List;

import com.atanor.smanager.domain.entity.AbstractEntity;
import com.atanor.smanager.rpc.dto.AbstractDto;
import com.google.common.collect.Lists;

@SuppressWarnings("rawtypes")
public abstract class AbstractConverter<D extends AbstractDto, E extends AbstractEntity> implements Converter<D, E> {

	protected static <D extends AbstractDto, E extends AbstractEntity> List<D> convertEntityList(
			final Converter<D, E> converter, final List<E> entities) {
		final List<D> converted = Lists.newArrayList();
		if (entities != null) {
			for (E entity : entities) {
				converted.add(converter.toDto(entity));
			}
		}

		return converted;
	}

	protected static <D extends AbstractDto, E extends AbstractEntity> List<E> convertDtoList(
			final Converter<D, E> converter, final List<D> dtos) {
		final List<E> converted = Lists.newArrayList();
		if (dtos != null) {
			for (D dto : dtos) {
				converted.add(converter.toEntity(dto));
			}
		}

		return converted;
	}

}
