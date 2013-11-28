package com.atanor.vserver.vsadmin.client.ui.sections;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;

public class BroadcastingSection extends HLayout {

	public BroadcastingSection(){
		setWidth100();
		setHeight100();
		setBackgroundColor("grey");
		
		Label label = new Label();
		label.setContents("BROADCASTING");
		//label.setTop(0);
		addChild(label);
	}
}
