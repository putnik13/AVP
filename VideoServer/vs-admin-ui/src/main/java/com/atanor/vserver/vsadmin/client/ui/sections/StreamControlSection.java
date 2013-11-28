package com.atanor.vserver.vsadmin.client.ui.sections;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;

public class StreamControlSection extends HLayout {

	public StreamControlSection(){
		setWidth100();
		setHeight100();
		setBackgroundColor("red");
		
		Label label = new Label();
		label.setContents("STREAM CONTROL");
		//label.setTop(0);
		addChild(label);
	}
}
