package com.atanor.vserver.vsadmin.client.ui.sections;

import com.smartgwt.client.widgets.Label;

public class EditConfigurationSection extends BaseSection {

	public EditConfigurationSection() {

		Label label = new Label();
		label.setContents("EDIT CONFIGURATION");
		// label.setTop(0);
		addChild(label);
	}
}
