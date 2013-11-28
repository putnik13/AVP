package com.atanor.vserver.vsadmin.client.ui.sections;

import com.smartgwt.client.widgets.Label;

public class StreamControlSection extends BaseSection {

	public StreamControlSection() {

		Label label = new Label();
		label.setContents("STREAM CONTROL");
		// label.setTop(0);
		addChild(label);
	}
}
