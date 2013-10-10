package com.atanor.smanager.domain.converter;

import junit.framework.Assert;

import org.junit.Test;

import com.atanor.smanager.domain.entity.Display;
import com.atanor.smanager.domain.entity.PanelLayout;
import com.atanor.smanager.rpc.dto.DisplayDto;

public class DisplayConverterTest extends BaseConverterTest<DisplayConverter> {

	@Test
	public void testConvert() {
		DisplayDto result = converter.convert(new Display(PanelLayout.ONExTHREE, 1800, 600));

		Assert.assertNotNull(result);
		Assert.assertEquals(PanelLayout.ONExTHREE.getDescription(), result.getLayout().getName());
		Assert.assertEquals(PanelLayout.ONExTHREE.getPanelQuantity(), result.getLayout().getPanelQuantity());
		Assert.assertEquals(Integer.valueOf(1800), result.getWidth());
		Assert.assertEquals(Integer.valueOf(600), result.getHigh());
	}

	@Test(expected = NullPointerException.class)
	public void testConvertNull() {
		converter.convert(null);
	}

}
