package com.atanor.smanager.client.ui.builder.post;

import com.atanor.smanager.client.ui.WindowLabel;
import com.atanor.smanager.client.ui.style.EditWindowStyleApplier;

public class EditWindowPostBuilder implements WindowPostBuilder {

	@Override
	public void doPostBuild(WindowLabel window) {
		
		new EditWindowStyleApplier().applyStyle(window);

		window.setContents(window.getDto().getSource());
	}

}
