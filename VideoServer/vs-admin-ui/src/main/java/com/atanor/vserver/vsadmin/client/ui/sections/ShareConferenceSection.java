package com.atanor.vserver.vsadmin.client.ui.sections;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;

public class ShareConferenceSection extends HLayout {

	public ShareConferenceSection(){
		setWidth100();
		setHeight100();
		setBackgroundColor("green");
		
		Label label = new Label();
		label.setContents("SHARE CONFERENCE");
		//label.setTop(0);
		addChild(label);
	}
}
