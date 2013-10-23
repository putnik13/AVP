package com.atanor.smanager.client.ui.builder.post;

import com.atanor.smanager.client.ui.PresetLabel;
import com.smartgwt.client.types.DragAppearance;

public class EditPresetPostBuilder implements PresetPostBuilder {

	@Override
	public void doPostBuild(PresetLabel preset) {
		preset.setDragAppearance(DragAppearance.TARGET);
		preset.setCanDragResize(true);
		preset.setBorder("1px solid black");
	}

}
