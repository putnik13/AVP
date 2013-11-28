package com.atanor.vserver.vsadmin.client.ui.sections;

import com.smartgwt.client.widgets.Label;

public class BroadcastingSection extends BaseSection {

	public BroadcastingSection() {

		Label label = new Label();
		label.setContents("BROADCASTING");
		// label.setTop(0);
		addChild(label);
	}
}
