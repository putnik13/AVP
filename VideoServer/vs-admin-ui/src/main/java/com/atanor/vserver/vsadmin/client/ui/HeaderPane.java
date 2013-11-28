package com.atanor.vserver.vsadmin.client.ui;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;

public class HeaderPane extends HLayout {

	public HeaderPane() {
		setHeight(50);
		setWidth100();
		
		final HLayout spacer = new HLayout();
		spacer.setWidth(20);
        
        Label label = new Label();
        label.setContents("Welcome, John Doe");
        
        final HLayout hLayout = new HLayout();
        hLayout.setAlign(Alignment.RIGHT);
        hLayout.addMembers(label);
		
        addMembers(hLayout, spacer);
	}
}
