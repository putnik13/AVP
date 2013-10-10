package com.atanor.smanager.domain.converter;

import org.junit.Before;

import com.atanor.smanager.injector.AppConverterModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class BaseConverterTest<C extends Converter<?, ?>> {

	@Inject
	C converter;

	@Before
	public void setUp() throws Exception {
		Injector injector = Guice.createInjector(new AppConverterModule());
		injector.injectMembers(this);
	}

}
