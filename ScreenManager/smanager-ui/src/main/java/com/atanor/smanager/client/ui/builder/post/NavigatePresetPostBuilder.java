package com.atanor.smanager.client.ui.builder.post;

import com.atanor.smanager.client.ui.PresetLabel;
import com.smartgwt.client.types.Overflow;

public class NavigatePresetPostBuilder implements PresetPostBuilder {

	@Override
	public void doPostBuild(PresetLabel preset) {
		preset.setShowEdges(true);
		//preset.setBorder("1px solid black");
		preset.setOverflow(Overflow.HIDDEN);
	}

}
