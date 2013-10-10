package com.atanor.smanager.domain.dao;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.atanor.smanager.domain.entity.Preset;
import com.atanor.smanager.domain.entity.Window;
import com.google.common.collect.Lists;

@Ignore
public class PresetDaoTest extends BaseDaoTest<Preset> {

	@Test
	public void testInsertRecord() throws Exception {
		Preset preset = new Preset();

		Window w1 = new Window("Window 1", "VIDEO", 1, 2, 1, 2, 1);
		Window w2 = new Window("Window 2", "CAMERA1", 3, 1, 1, 1, 2);
		Window w3 = new Window("Window 2", "CAMERA2", 3, 1, 2, 1, 3);
		populatePreset(preset, Lists.newArrayList(w1, w2, w3));
		preset.setWindows(Lists.newArrayList(w1, w2, w3));
		
		Assert.assertNotNull(dao.insert(preset));
	}

	@Test
	public void testDeleteRecord() throws Exception {
		Preset preset = new Preset();

		Long id = dao.insert(preset);
		preset = dao.find(id);
		dao.delete(preset);
	}

	@Test
	public void testSelect() throws Exception {
		Preset preset = new Preset();

		Window w1 = new Window("Window 1", "VIDEO", 1, 2, 1, 2, 1);
		Window w2 = new Window("Window 2", "CAMERA1", 3, 1, 1, 1, 2);
		Window w3 = new Window("Window 2", "CAMERA2", 3, 1, 2, 1, 3);
		populatePreset(preset, Lists.newArrayList(w1, w2, w3));
		preset.setWindows(Lists.newArrayList(w1, w2, w3));

		Long id = dao.insert(preset);

		Preset presetFromDB = dao.find(id);
		Assert.assertNotNull(presetFromDB);

//		Assert.assertEquals(PanelLayout.TWOxTHREE, presetFromDB.getLayout());
//		Assert.assertEquals(Integer.valueOf(6), presetFromDB.getLayout().getPanelQuantity());

		Assert.assertEquals(3, presetFromDB.getWindows().size());

		Assert.assertEquals("Window 1", presetFromDB.getWindows().get(0).getName());
		Assert.assertEquals("VIDEO", presetFromDB.getWindows().get(0).getSource());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getWindows().get(0).getXTopLeft());
		Assert.assertEquals(Integer.valueOf(2), presetFromDB.getWindows().get(0).getYTopLeft());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getWindows().get(0).getXBottomRight());
		Assert.assertEquals(Integer.valueOf(2), presetFromDB.getWindows().get(0).getYBottomRight());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getWindows().get(0).getZIndex());
		
		Assert.assertEquals("Window 2", presetFromDB.getWindows().get(1).getName());
		Assert.assertEquals("CAMERA1", presetFromDB.getWindows().get(1).getSource());
		Assert.assertEquals(Integer.valueOf(3), presetFromDB.getWindows().get(1).getXTopLeft());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getWindows().get(1).getYTopLeft());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getWindows().get(1).getXBottomRight());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getWindows().get(1).getYBottomRight());
		Assert.assertEquals(Integer.valueOf(2), presetFromDB.getWindows().get(1).getZIndex());
		
		Assert.assertEquals("Window 3", presetFromDB.getWindows().get(2).getName());
		Assert.assertEquals("CAMERA2", presetFromDB.getWindows().get(2).getSource());
		Assert.assertEquals(Integer.valueOf(3), presetFromDB.getWindows().get(2).getXTopLeft());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getWindows().get(2).getYTopLeft());
		Assert.assertEquals(Integer.valueOf(2), presetFromDB.getWindows().get(2).getXBottomRight());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getWindows().get(2).getYBottomRight());
		Assert.assertEquals(Integer.valueOf(3), presetFromDB.getWindows().get(2).getZIndex());
	}

	@Test
	public void testUpdate() throws Exception {
		Preset preset = new Preset();
		preset.setWindows(Arrays.asList(new Window()));
		
		Long id = dao.insert(preset);
		Preset presetFromDB = dao.find(id);
		Assert.assertNotNull(presetFromDB);
		Assert.assertEquals(1, presetFromDB.getWindows().size());
		
		presetFromDB.getWindows().add(new Window());
		dao.update(presetFromDB);

		presetFromDB = dao.find(id);
		Assert.assertNotNull(presetFromDB);
		Assert.assertEquals(2, presetFromDB.getWindows().size());
	}

	public void testGetAll() {
		Preset preset1 = new Preset();
		Preset preset2 = new Preset();
		Preset preset3 = new Preset();

		dao.insert(preset1);
		dao.insert(preset2);
		dao.insert(preset3);

		List<Preset> allDisplay = dao.findAll();
		Assert.assertEquals(3, allDisplay.size());

//		Assert.assertEquals(PanelLayout.ONExONE, allDisplay.get(0).getLayout());
//		Assert.assertEquals(Integer.valueOf(1), allDisplay.get(0).getLayout().getPanelQuantity());
//
//		Assert.assertEquals(PanelLayout.ONExTWO, allDisplay.get(1).getLayout());
//		Assert.assertEquals(Integer.valueOf(2), allDisplay.get(1).getLayout().getPanelQuantity());
//
//		Assert.assertEquals(PanelLayout.ONExTHREE, allDisplay.get(2).getLayout());
//		Assert.assertEquals(Integer.valueOf(2), allDisplay.get(2).getLayout().getPanelQuantity());
	}
	
	private void populatePreset(Preset preset, List<Window> screens){
		for (Window screen : screens) {
			screen.setPreset(preset);
		}
	}
}
