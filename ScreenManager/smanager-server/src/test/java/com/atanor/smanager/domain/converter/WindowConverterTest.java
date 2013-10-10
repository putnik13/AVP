package com.atanor.smanager.domain.converter;

import junit.framework.Assert;

import org.junit.Test;

import com.atanor.smanager.domain.entity.Window;
import com.atanor.smanager.rpc.dto.WindowDto;

public class WindowConverterTest extends BaseConverterTest<WindowConverter> {

	@Test
	public void testConvert() {
		Window source = new Window("Window 0", "CAMERA", 1, 2, 1, 3, 0);

		WindowDto result = converter.convert(source);
		Assert.assertNotNull(result);
		Assert.assertEquals("Window 0", result.getName());
		Assert.assertEquals("CAMERA", result.getSource());
		Assert.assertEquals(Integer.valueOf(1), result.getXTopLeft());
		Assert.assertEquals(Integer.valueOf(2), result.getYTopLeft());
		Assert.assertEquals(Integer.valueOf(1), result.getXBottomRigh());
		Assert.assertEquals(Integer.valueOf(3), result.getYBottomRight());
		Assert.assertEquals(Integer.valueOf(0), result.getZIndex());
	}

	@Test(expected = NullPointerException.class)
	public void testConvertNull() {
		converter.convert(null);
	}
	
}
