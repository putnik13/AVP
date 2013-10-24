package com.atanor.smanager.client.ui.style;

import com.atanor.smanager.client.ui.WindowLabel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;

public class EditWindowStyleApplier {

	public void applyStyle(WindowLabel canvas) {
		canvas.setBorder("1px inset black");
		canvas.setBackgroundColor("yellow");
		canvas.setOpacity(50);
		canvas.setCanDragResize(false);
		canvas.setCanDragReposition(false);
		canvas.setAlign(Alignment.CENTER);
		canvas.setValign(VerticalAlignment.CENTER);
		canvas.setKeepInParentRect(true);
	}

}
