package com.atanor.smanager.domain.converter;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.atanor.smanager.domain.entity.Preset;
import com.atanor.smanager.domain.entity.Window;
import com.atanor.smanager.rpc.dto.PresetDto;
import com.atanor.smanager.rpc.dto.WindowDto;

public class PresetConverterTest extends BaseConverterTest<PresetConverter> {

	@Test
	public void testToDto() {
		Preset source = new Preset();

		Window w1 = new Window("Window 1", "CAMERA", 1, 2, 3, 4, 1);
		Window w2 = new Window("Window 2", "INPUT1", 5, 6, 7, 8, 2);
		Window w3 = new Window("Window 3", "INPUT1", 10, 11, 12, 13, 3);
		source.setWindows(Arrays.asList(w1, w2, w3));

		PresetDto result = converter.toDto(source);

		Assert.assertNotNull(result);
		Assert.assertEquals(3, result.getWindows().size());

		Assert.assertEquals(Integer.valueOf(1), result.getWindows().get(0).getXTopLeft());
		Assert.assertEquals(Integer.valueOf(2), result.getWindows().get(0).getYTopLeft());
		Assert.assertEquals(Integer.valueOf(3), result.getWindows().get(0).getXBottomRight());
		Assert.assertEquals(Integer.valueOf(4), result.getWindows().get(0).getYBottomRight());
		Assert.assertEquals(Integer.valueOf(1), result.getWindows().get(0).getZIndex());

		Assert.assertEquals(Integer.valueOf(5), result.getWindows().get(1).getXTopLeft());
		Assert.assertEquals(Integer.valueOf(6), result.getWindows().get(1).getYTopLeft());
		Assert.assertEquals(Integer.valueOf(7), result.getWindows().get(1).getXBottomRight());
		Assert.assertEquals(Integer.valueOf(8), result.getWindows().get(1).getYBottomRight());
		Assert.assertEquals(Integer.valueOf(2), result.getWindows().get(1).getZIndex());

		Assert.assertEquals(Integer.valueOf(10), result.getWindows().get(2).getXTopLeft());
		Assert.assertEquals(Integer.valueOf(11), result.getWindows().get(2).getYTopLeft());
		Assert.assertEquals(Integer.valueOf(12), result.getWindows().get(2).getXBottomRight());
		Assert.assertEquals(Integer.valueOf(13), result.getWindows().get(2).getYBottomRight());
		Assert.assertEquals(Integer.valueOf(3), result.getWindows().get(2).getZIndex());
	}
	
	@Test
	public void testToEntity() {
		PresetDto source = new PresetDto();

		WindowDto w1 = new WindowDto();
		w1.setName("Window 1");
		w1.setSource("CAMERA");
		w1.setXTopLeft(1);
		w1.setYTopLeft(2);
		w1.setXBottomRight(3);
		w1.setYBottomRight(4);
		w1.setZIndex(1);
		
		WindowDto w2 = new WindowDto();
		w2.setName("Window 2");
		w2.setSource("INPUT1");
		w2.setXTopLeft(5);
		w2.setYTopLeft(6);
		w2.setXBottomRight(7);
		w2.setYBottomRight(8);
		w2.setZIndex(2);
		
		WindowDto w3 = new WindowDto();
		w3.setName("Window 3");
		w3.setSource("INPUT1");
		w3.setXTopLeft(10);
		w3.setYTopLeft(11);
		w3.setXBottomRight(12);
		w3.setYBottomRight(13);
		w3.setZIndex(3);
		
		source.setWindows(Arrays.asList(w1, w2, w3));

		Preset result = converter.toEntity(source);

		Assert.assertNotNull(result);
		Assert.assertEquals(3, result.getWindows().size());

		Assert.assertEquals(Integer.valueOf(1), result.getWindows().get(0).getXTopLeft());
		Assert.assertEquals(Integer.valueOf(2), result.getWindows().get(0).getYTopLeft());
		Assert.assertEquals(Integer.valueOf(3), result.getWindows().get(0).getXBottomRight());
		Assert.assertEquals(Integer.valueOf(4), result.getWindows().get(0).getYBottomRight());
		Assert.assertEquals(Integer.valueOf(1), result.getWindows().get(0).getZIndex());

		Assert.assertEquals(Integer.valueOf(5), result.getWindows().get(1).getXTopLeft());
		Assert.assertEquals(Integer.valueOf(6), result.getWindows().get(1).getYTopLeft());
		Assert.assertEquals(Integer.valueOf(7), result.getWindows().get(1).getXBottomRight());
		Assert.assertEquals(Integer.valueOf(8), result.getWindows().get(1).getYBottomRight());
		Assert.assertEquals(Integer.valueOf(2), result.getWindows().get(1).getZIndex());

		Assert.assertEquals(Integer.valueOf(10), result.getWindows().get(2).getXTopLeft());
		Assert.assertEquals(Integer.valueOf(11), result.getWindows().get(2).getYTopLeft());
		Assert.assertEquals(Integer.valueOf(12), result.getWindows().get(2).getXBottomRight());
		Assert.assertEquals(Integer.valueOf(13), result.getWindows().get(2).getYBottomRight());
		Assert.assertEquals(Integer.valueOf(3), result.getWindows().get(2).getZIndex());
	}

	@Test(expected = NullPointerException.class)
	public void testToDtoNull() {
		converter.toDto(null);
	}
}
