package com.atanor.smanager.domain.converter;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.atanor.smanager.domain.entity.PanelLayout;
import com.atanor.smanager.domain.entity.Preset;
import com.atanor.smanager.domain.entity.Window;
import com.atanor.smanager.rpc.dto.PresetDto;

public class PresetConverterTest extends BaseConverterTest<PresetConverter> {

	@Test
	public void testConvert() {
		Preset source = new Preset();
		source.setLayout(PanelLayout.TWOxTHREE);

		Window w1 = new Window("Window 1", "CAMERA", 1, 2, 3, 4, 1);
		Window w2 = new Window("Window 2", "INPUT1", 5, 6, 7, 8, 2);
		Window w3 = new Window("Window 3", "INPUT1", 10, 11, 12, 13, 3);
		source.setWindows(Arrays.asList(w1, w2, w3));

		PresetDto result = converter.convert(source);

		Assert.assertNotNull(result);
		Assert.assertEquals(PanelLayout.TWOxTHREE.getDescription(), result.getLayout().getName());
		Assert.assertEquals(PanelLayout.TWOxTHREE.getPanelQuantity(), result.getLayout().getPanelQuantity());
		Assert.assertEquals(3, result.getWindows().size());

		Assert.assertEquals(Integer.valueOf(1), result.getWindows().get(0).getXTopLeft());
		Assert.assertEquals(Integer.valueOf(2), result.getWindows().get(0).getYTopLeft());
		Assert.assertEquals(Integer.valueOf(3), result.getWindows().get(0).getXBottomRigh());
		Assert.assertEquals(Integer.valueOf(4), result.getWindows().get(0).getYBottomRight());
		Assert.assertEquals(Integer.valueOf(1), result.getWindows().get(0).getZIndex());

		Assert.assertEquals(Integer.valueOf(5), result.getWindows().get(1).getXTopLeft());
		Assert.assertEquals(Integer.valueOf(6), result.getWindows().get(1).getYTopLeft());
		Assert.assertEquals(Integer.valueOf(7), result.getWindows().get(1).getXBottomRigh());
		Assert.assertEquals(Integer.valueOf(8), result.getWindows().get(1).getYBottomRight());
		Assert.assertEquals(Integer.valueOf(2), result.getWindows().get(1).getZIndex());

		Assert.assertEquals(Integer.valueOf(10), result.getWindows().get(2).getXTopLeft());
		Assert.assertEquals(Integer.valueOf(11), result.getWindows().get(2).getYTopLeft());
		Assert.assertEquals(Integer.valueOf(12), result.getWindows().get(2).getXBottomRigh());
		Assert.assertEquals(Integer.valueOf(13), result.getWindows().get(2).getYBottomRight());
		Assert.assertEquals(Integer.valueOf(3), result.getWindows().get(2).getZIndex());
	}

	@Test(expected = NullPointerException.class)
	public void testConvertNull() {
		converter.convert(null);
	}
}
