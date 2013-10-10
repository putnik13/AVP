package com.atanor.smanager.domain.converter;

import junit.framework.Assert;

import org.junit.Test;

import com.atanor.smanager.domain.entity.PanelLayout;
import com.atanor.smanager.rpc.dto.PanelLayoutDto;

public class PanelLayoutConverterTest extends BaseConverterTest<PanelLayoutConverter> {

	@Test
	public void testConvert() {
		PanelLayoutDto result = converter.convert(PanelLayout.ONExTHREE);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(PanelLayout.ONExTHREE.getDescription(), result.getName());
		Assert.assertEquals(PanelLayout.ONExTHREE.getPanelQuantity(), result.getPanelQuantity());
	}

	@Test(expected = NullPointerException.class)
	public void testConvertNull() {
		converter.convert(null);
	}
	
}
