package com.atanor.smanager.domain.converter;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.atanor.smanager.domain.entity.PanelLayout;
import com.atanor.smanager.injector.AppConverterModule;
import com.atanor.smanager.rpc.dto.PanelLayoutDto;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class PanelLayoutConverterTest {

	@Inject
	PanelLayoutConverter converter;

	@Before
	public void setUp() throws Exception {
		Injector injector = Guice.createInjector(new AppConverterModule());
		injector.injectMembers(this);
	}

	@Test
	public void tesToDto() {
		PanelLayoutDto result = converter.toDto(PanelLayout.ONExTHREE);

		Assert.assertNotNull(result);
		Assert.assertEquals(PanelLayout.ONExTHREE.getDescription(), result.getName());
		Assert.assertEquals(PanelLayout.ONExTHREE.getPanelQuantity(), result.getPanelQuantity());
	}

	@Test
	public void testToEntity() {
		PanelLayoutDto source = new PanelLayoutDto();
		source.setName(PanelLayout.ONExTHREE.getDescription());
		source.setPanelQuantity(PanelLayout.ONExTHREE.getPanelQuantity());
		
		PanelLayout result = converter.toEntity(source);

		Assert.assertNotNull(result);
		Assert.assertEquals(PanelLayout.ONExTHREE.getDescription(), result.getDescription());
		Assert.assertEquals(PanelLayout.ONExTHREE.getPanelQuantity(), result.getPanelQuantity());
	}
	
	@Test(expected = NullPointerException.class)
	public void testToEntityNull() {
		converter.toDto(null);
	}

}
