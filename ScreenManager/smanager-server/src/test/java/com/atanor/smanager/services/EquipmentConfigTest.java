package com.atanor.smanager.services;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.atanor.smanager.domain.dao.HardwareDao;
import com.atanor.smanager.domain.dao.TestAppPersistenceModule;
import com.atanor.smanager.domain.entity.Display;
import com.atanor.smanager.domain.entity.Hardware;
import com.atanor.smanager.domain.entity.PanelLayout;
import com.atanor.smanager.domain.entity.Preset;
import com.atanor.smanager.domain.entity.Window;
import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class EquipmentConfigTest {

	@Inject
	private EntityManager em;

	@Inject
	private HardwareDao hardwareDao;

	@Inject
	private EquipmentConfigService equipService;

	@Before
	public void setUp() throws Exception {
		Injector injector = Guice.createInjector(new TestAppPersistenceModule(), new TestAppCoreModule());
		injector.injectMembers(this);
		em.getTransaction().begin();
	}

	@After
	public void tearDown() throws Exception {
		em.getTransaction().rollback();
	}

	@Test
	public void testModifyHardwareConfiguration() {
		Hardware hardware = new Hardware("PANASONIC");
		hardware.setActive(Boolean.TRUE);
		hardware.setDisplay(new Display(PanelLayout.ONExTWO, 1200, 600));
		hardware.setSources(Arrays.asList("input1", "input2", "input3"));

		Preset preset = new Preset();
		preset.setHardware(hardware);
		Window w1 = new Window("Window 1", "VIDEO", 1, 2, 1, 2, 1);
		Window w2 = new Window("Window 2", "CAMERA1", 3, 1, 1, 1, 2);
		Window w3 = new Window("Window 3", "CAMERA2", 3, 1, 2, 1, 3);
		populatePreset(preset, Lists.newArrayList(w1, w2, w3));
		preset.setWindows(Lists.newArrayList(w1, w2, w3));

		hardware.setPresets(Arrays.asList(preset));
		hardwareDao.insert(hardware);

		hardware = new Hardware("eLG");
		hardware.setActive(Boolean.FALSE);
		hardwareDao.insert(hardware);

		hardware = equipService.getActiveHardware();
		Assert.assertNotNull(hardware);
		Assert.assertEquals("PANASONIC", hardware.getModelName());
		Assert.assertNotNull(hardware.getDisplay());
		Assert.assertEquals(PanelLayout.ONExTWO, hardware.getDisplay().getLayout());
		Assert.assertEquals(Integer.valueOf(1200), hardware.getDisplay().getWidth());
		Assert.assertEquals(Integer.valueOf(600), hardware.getDisplay().getHigh());

		Assert.assertEquals(1, hardware.getPresets().size());
		preset = hardware.getPresets().get(0);

		Assert.assertEquals(3, preset.getWindows().size());
		Assert.assertEquals("Window 1", preset.getWindows().get(0).getName());
		Assert.assertEquals("VIDEO", preset.getWindows().get(0).getSource());
		Assert.assertEquals(Integer.valueOf(1), preset.getWindows().get(0).getXTopLeft());
		Assert.assertEquals(Integer.valueOf(2), preset.getWindows().get(0).getYTopLeft());
		Assert.assertEquals(Integer.valueOf(1), preset.getWindows().get(0).getXBottomRight());
		Assert.assertEquals(Integer.valueOf(2), preset.getWindows().get(0).getYBottomRight());
		Assert.assertEquals(Integer.valueOf(1), preset.getWindows().get(0).getZIndex());

		Assert.assertEquals("Window 2", preset.getWindows().get(1).getName());
		Assert.assertEquals("CAMERA1", preset.getWindows().get(1).getSource());
		Assert.assertEquals(Integer.valueOf(3), preset.getWindows().get(1).getXTopLeft());
		Assert.assertEquals(Integer.valueOf(1), preset.getWindows().get(1).getYTopLeft());
		Assert.assertEquals(Integer.valueOf(1), preset.getWindows().get(1).getXBottomRight());
		Assert.assertEquals(Integer.valueOf(1), preset.getWindows().get(1).getYBottomRight());
		Assert.assertEquals(Integer.valueOf(2), preset.getWindows().get(1).getZIndex());

		Assert.assertEquals("Window 3", preset.getWindows().get(2).getName());
		Assert.assertEquals("CAMERA2", preset.getWindows().get(2).getSource());
		Assert.assertEquals(Integer.valueOf(3), preset.getWindows().get(2).getXTopLeft());
		Assert.assertEquals(Integer.valueOf(1), preset.getWindows().get(2).getYTopLeft());
		Assert.assertEquals(Integer.valueOf(2), preset.getWindows().get(2).getXBottomRight());
		Assert.assertEquals(Integer.valueOf(1), preset.getWindows().get(2).getYBottomRight());
		Assert.assertEquals(Integer.valueOf(3), preset.getWindows().get(2).getZIndex());

		Long id = hardware.getPresets().get(0).getId();
		// Create new preset with updated windows parameters
		preset = new Preset(id);
		w1 = new Window("Window 1", "VIDEO", 11, 22, 33, 44, 0);
		w1.setPreset(preset);
		preset.setWindows(Arrays.asList(w1));
		
		equipService.savePreset(preset);
		
		hardware = equipService.getActiveHardware();
		Assert.assertNotNull(hardware);
		Assert.assertEquals("PANASONIC", hardware.getModelName());
		Assert.assertNotNull(hardware.getDisplay());
		Assert.assertEquals(PanelLayout.ONExTWO, hardware.getDisplay().getLayout());
		Assert.assertEquals(Integer.valueOf(1200), hardware.getDisplay().getWidth());
		Assert.assertEquals(Integer.valueOf(600), hardware.getDisplay().getHigh());

		Assert.assertEquals(1, hardware.getPresets().size());
		preset = hardware.getPresets().get(0);

		Assert.assertEquals(1, preset.getWindows().size());
		Assert.assertEquals("Window 1", preset.getWindows().get(0).getName());
		Assert.assertEquals("VIDEO", preset.getWindows().get(0).getSource());
		Assert.assertEquals(Integer.valueOf(11), preset.getWindows().get(0).getXTopLeft());
		Assert.assertEquals(Integer.valueOf(22), preset.getWindows().get(0).getYTopLeft());
		Assert.assertEquals(Integer.valueOf(33), preset.getWindows().get(0).getXBottomRight());
		Assert.assertEquals(Integer.valueOf(44), preset.getWindows().get(0).getYBottomRight());
		Assert.assertEquals(Integer.valueOf(0), preset.getWindows().get(0).getZIndex());

	}

	private void populatePreset(Preset preset, List<Window> screens) {
		for (Window screen : screens) {
			screen.setPreset(preset);
		}
	}
}
