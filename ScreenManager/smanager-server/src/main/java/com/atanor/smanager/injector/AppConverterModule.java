package com.atanor.smanager.injector;

import com.atanor.smanager.domain.converter.DisplayConverter;
import com.atanor.smanager.domain.converter.WindowConverter;
import com.atanor.smanager.domain.converter.Converter;
import com.atanor.smanager.domain.converter.PanelLayoutConverter;
import com.atanor.smanager.domain.converter.PresetConverter;
import com.atanor.smanager.domain.entity.Display;
import com.atanor.smanager.domain.entity.Window;
import com.atanor.smanager.domain.entity.PanelLayout;
import com.atanor.smanager.domain.entity.Preset;
import com.atanor.smanager.rpc.dto.DisplayDto;
import com.atanor.smanager.rpc.dto.WindowDto;
import com.atanor.smanager.rpc.dto.PanelLayoutDto;
import com.atanor.smanager.rpc.dto.PresetDto;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;

public class AppConverterModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<Converter<PresetDto, Preset>>() {}).to(new TypeLiteral<PresetConverter>() {}).in(Singleton.class);
		bind(new TypeLiteral<Converter<PanelLayoutDto, PanelLayout>>() {}).to(new TypeLiteral<PanelLayoutConverter>() {}).in(Singleton.class);
		bind(new TypeLiteral<Converter<WindowDto, Window>>() {}).to(new TypeLiteral<WindowConverter>() {}).in(Singleton.class);
		bind(new TypeLiteral<Converter<DisplayDto, Display>>() {}).to(new TypeLiteral<DisplayConverter>() {}).in(Singleton.class);
	}

}