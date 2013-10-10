package com.atanor.smanager.services;

import java.util.Arrays;

import com.atanor.smanager.domain.entity.Display;
import com.atanor.smanager.domain.entity.Hardware;
import com.atanor.smanager.domain.entity.PanelLayout;
import com.atanor.smanager.domain.entity.Preset;
import com.atanor.smanager.domain.entity.Window;

public class EquipmentConfigServiceMockImpl implements EquipmentConfigService {

	@Override
	public Hardware getActiveHardware() {
		Hardware source = new Hardware();

		source.setModelName("SONY");
		source.setSources(Arrays.asList("Input 1", "Input 2", "Input 3", "Input 4", "testvnc", "video", "web"));
		source.setDisplay(new Display(PanelLayout.THREExFIVE, 1600, 1200));

		Preset preset1 = new Preset();
		Window w0 = new Window("Window 0", "video", 800, 0, 1600, 600, 0);
		Window w1 = new Window("Window 1", "testvnc", 800, 600, 1600, 1200, 4);
		Window w2 = new Window("Window 2", "video", 0, 600, 800, 1200, 1);
		Window w3 = new Window("Window 3", "Input 4", 400, 300, 800, 600, 3);
		Window w4 = new Window("Window 4", "web", 400, 0, 800, 300, 2);
		preset1.setWindows(Arrays.asList(w0, w1, w2, w3, w4));

		Preset preset2 = new Preset();
		Window w5 = new Window("Window 0", "Input 1", 0, 0, 1200, 1200, 0);
		Window w6 = new Window("Window 1", "Input 2", 1200, 0, 1600, 600, 0);
		Window w7 = new Window("Window 1", "Input 3", 1200, 600, 1600, 1200, 0);
		preset2.setWindows(Arrays.asList(w5, w6, w7));

		source.setPresets(Arrays.asList(preset1, preset2));

		return source;
	}

}
