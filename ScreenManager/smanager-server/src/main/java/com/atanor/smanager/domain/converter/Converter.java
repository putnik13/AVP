package com.atanor.smanager.domain.converter;

public interface Converter<D, E> {

	D convert(E entity);
}
