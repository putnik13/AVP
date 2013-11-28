package com.atanor.vserver.vsadmin.client.ui.sections;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;

public class EditConfigurationSection extends HLayout {

	public EditConfigurationSection(){
		setWidth100();
		setHeight100();
		setBackgroundColor("blue");
		
		Label label = new Label();
		label.setContents("EDIT CONFIGURATION");
		//label.setTop(0);
		addChild(label);
	}
}
