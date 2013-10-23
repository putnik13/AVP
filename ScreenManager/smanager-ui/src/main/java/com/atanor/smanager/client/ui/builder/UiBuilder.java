package com.atanor.smanager.client.ui.builder;

import com.atanor.smanager.client.ui.PresetLabel;
import com.atanor.smanager.client.ui.WindowLabel;
import com.atanor.smanager.client.ui.builder.post.PresetPostBuilder;
import com.atanor.smanager.client.ui.builder.post.WindowPostBuilder;
import com.atanor.smanager.rpc.dto.PresetDto;
import com.atanor.smanager.rpc.dto.WindowDto;
import com.google.common.primitives.Ints;

public class UiBuilder {

	public static PresetLabel buildPresetLayout(final PresetDto preset, final Long presetWidth,
			final Long presetHeight, final Double scaleFactor, final PresetPostBuilder presetPostBuilder,
			final WindowPostBuilder windowPostBuilder) {

		PresetLabel plabel = new PresetLabel(preset);
		plabel.setWidth(Ints.checkedCast(presetWidth));
		plabel.setHeight(Ints.checkedCast(presetHeight));
		presetPostBuilder.doPostBuild(plabel);

		for (WindowDto window : preset.getWindows()) {

			final WindowLabel wlabel = new WindowLabel(window);

			Long wTop = Math.round(scaleFactor * window.getYTopLeft().doubleValue());
			wlabel.setTop(Ints.checkedCast(wTop));

			Long wLeft = Math.round(scaleFactor * window.getXTopLeft().doubleValue());
			wlabel.setLeft(Ints.checkedCast(wLeft));

			Long wWidth = Math.round(scaleFactor
					* (window.getXBottomRight().doubleValue() - window.getXTopLeft().doubleValue()));
			wlabel.setWidth(Ints.checkedCast(wWidth));

			Long wHeight = Math.round(scaleFactor
					* (window.getYBottomRight().doubleValue() - window.getYTopLeft().doubleValue()));
			wlabel.setHeight(Ints.checkedCast(wHeight));

			windowPostBuilder.doPostBuild(wlabel);

			plabel.addChild(wlabel);
		}

		return plabel;
	}
}
