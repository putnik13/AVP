package com.atanor.smanager.domain.converter;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import com.atanor.smanager.domain.entity.Display;
import com.atanor.smanager.domain.entity.Hardware;
import com.atanor.smanager.domain.entity.PanelLayout;
import com.atanor.smanager.domain.entity.Preset;
import com.atanor.smanager.domain.entity.Window;
import com.atanor.smanager.rpc.dto.DisplayDto;
import com.atanor.smanager.rpc.dto.HardwareDto;
import com.atanor.smanager.rpc.dto.PanelLayoutDto;
import com.atanor.smanager.rpc.dto.PresetDto;
import com.atanor.smanager.rpc.dto.WindowDto;

public class HardwareConverterTest extends BaseConverterTest<HardwareConverter> {

	@Test
	public void testToDto() {
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

		HardwareDto result = converter.toDto(source);

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
	
	@Test
	public void testToEntity() {
		HardwareDto source = new HardwareDto();
		source.setModelName("SONY");
		source.setSources(Arrays.asList("input1", "input2"));
		DisplayDto display = new DisplayDto();
		display.setWidth(1200);
		display.setHigh(1800);
		PanelLayoutDto layout = new PanelLayoutDto();
		layout.setName(PanelLayout.TWOxTHREE.getDescription());
		layout.setPanelQuantity(PanelLayout.TWOxTHREE.getPanelQuantity());
		display.setLayout(layout);
		
		source.setDisplay(display);

		PresetDto preset1 = new PresetDto();
		WindowDto w1 = new WindowDto();
		w1.setName("Window 1");
		w1.setSource("CAMERA");
		w1.setXTopLeft(1);
		w1.setYTopLeft(2);
		w1.setXBottomRight(3);
		w1.setYBottomRight(4);
		w1.setZIndex(1);
		
		preset1.setWindows(Arrays.asList(w1));

		PresetDto preset2 = new PresetDto();
		WindowDto w2 = new WindowDto();
		w2.setName("Window 2");
		w2.setSource("INPUT1");
		w2.setXTopLeft(5);
		w2.setYTopLeft(6);
		w2.setXBottomRight(7);
		w2.setYBottomRight(8);
		w2.setZIndex(2);
		
		WindowDto w3 = new WindowDto();
		w2.setName("Window 3");
		w2.setSource("INPUT1");
		w2.setXTopLeft(10);
		w2.setYTopLeft(11);
		w2.setXBottomRight(12);
		w2.setYBottomRight(13);
		w2.setZIndex(3);
		
		preset2.setWindows(Arrays.asList(w2, w3));

		source.setPresets(Arrays.asList(preset1, preset2));

		Hardware result = converter.toEntity(source);

		Assert.assertNotNull(result);
		Assert.assertEquals("SONY", result.getModelName());

		Assert.assertEquals(2, result.getSources().size());
		Assert.assertEquals("input1", result.getSources().get(0));
		Assert.assertEquals("input2", result.getSources().get(1));

		Assert.assertEquals(Integer.valueOf(1200), result.getDisplay().getWidth());
		Assert.assertEquals(Integer.valueOf(1800), result.getDisplay().getHigh());
		Assert.assertEquals(PanelLayout.TWOxTHREE.getDescription(), result.getDisplay().getLayout().getDescription());
		Assert.assertEquals(PanelLayout.TWOxTHREE.getPanelQuantity(), result.getDisplay().getLayout()
				.getPanelQuantity());

		Assert.assertEquals(2, result.getPresets().size());
		Assert.assertEquals(1, result.getPresets().get(0).getWindows().size());
		Assert.assertEquals(2, result.getPresets().get(1).getWindows().size());
	}
	
	@Test(expected = NullPointerException.class)
	public void testToDtoNull() {
		converter.toDto(null);
	}
}
