package com.atanor.smanager.domain.converter;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import com.atanor.smanager.domain.entity.Display;
import com.atanor.smanager.domain.entity.Hardware;
import com.atanor.smanager.domain.entity.PanelLayout;
import com.atanor.smanager.domain.entity.Preset;
import com.atanor.smanager.domain.entity.Window;
import com.atanor.smanager.rpc.dto.HardwareDto;

public class HardwareConverterTest extends BaseConverterTest<HardwareConverter> {

	@Test
	public void testConvert() {
		Hardware source = new Hardware();
		source.setModelName("SONY");
		source.setSources(Arrays.asList("input1", "input2"));
		source.setDisplay(new Display(PanelLayout.TWOxTHREE, 1200, 1800));

		Preset preset1 = new Preset();
		Window w1 = new Window("Window 1", "CAMERA", 1, 2, 3, 4, 1);
		preset1.setWindows(Arrays.asList(w1));

		Preset preset2 = new Preset();
		Window w2 = new Window("Window 2", "INPUT1", 5, 6, 7, 8, 2);
		Window w3 = new Window("Window 3", "INPUT1", 10, 11, 12, 13, 3);
		preset2.setWindows(Arrays.asList(w2, w3));

		source.setPresets(Arrays.asList(preset1, preset2));

		HardwareDto result = converter.convert(source);

		Assert.assertNotNull(result);
		Assert.assertEquals("SONY", result.getModelName());

		Assert.assertEquals(2, result.getSources().size());
		Assert.assertEquals("input1", result.getSources().get(0));
		Assert.assertEquals("input2", result.getSources().get(1));

		Assert.assertEquals(Integer.valueOf(1200), result.getDisplay().getWidth());
		Assert.assertEquals(Integer.valueOf(1800), result.getDisplay().getHigh());
		Assert.assertEquals(PanelLayout.TWOxTHREE.getDescription(), result.getDisplay().getLayout().getName());
		Assert.assertEquals(PanelLayout.TWOxTHREE.getPanelQuantity(), result.getDisplay().getLayout()
				.getPanelQuantity());

		Assert.assertEquals(2, result.getPresets().size());
		Assert.assertEquals(1, result.getPresets().get(0).getWindows().size());
		Assert.assertEquals(2, result.getPresets().get(1).getWindows().size());
	}
	
	@Test(expected = NullPointerException.class)
	public void testConvertNull() {
		converter.convert(null);
	}
}
