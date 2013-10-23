package com.atanor.smanager.injector;

import com.atanor.smanager.domain.converter.Converter;
import com.atanor.smanager.domain.converter.DisplayConverter;
import com.atanor.smanager.domain.converter.HardwareConverter;
import com.atanor.smanager.domain.converter.PresetConverter;
import com.atanor.smanager.domain.converter.WindowConverter;
import com.atanor.smanager.domain.entity.Display;
import com.atanor.smanager.domain.entity.Hardware;
import com.atanor.smanager.domain.entity.Preset;
import com.atanor.smanager.domain.entity.Window;
import com.atanor.smanager.rpc.dto.DisplayDto;
import com.atanor.smanager.rpc.dto.HardwareDto;
import com.atanor.smanager.rpc.dto.PresetDto;
import com.atanor.smanager.rpc.dto.WindowDto;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class AppConverterModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<Converter<PresetDto, Preset>>() {}).to(new TypeLiteral<PresetConverter>() {});
		bind(new TypeLiteral<Converter<WindowDto, Window>>() {}).to(new TypeLiteral<WindowConverter>() {});
		bind(new TypeLiteral<Converter<DisplayDto, Display>>() {}).to(new TypeLiteral<DisplayConverter>() {});
		bind(new TypeLiteral<Converter<HardwareDto, Hardware>>() {}).to(new TypeLiteral<HardwareConverter>() {});
	}

}
