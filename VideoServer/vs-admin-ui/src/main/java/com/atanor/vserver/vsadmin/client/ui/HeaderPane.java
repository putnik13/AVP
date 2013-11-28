package com.atanor.vserver.vsadmin.client.ui;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class HeaderPane extends ToolStrip {

	public HeaderPane() {
		setWidth100();

		// push all buttons to the right
		addFill();
        
        Label label = new Label();
        label.setContents("John Doe");
        addMember(label);
		
        addSeparator();
        
		ToolStripButton logoutButton = new ToolStripButton();  
        logoutButton.setTitle("Logout");  
        addButton(logoutButton);
        
	}
}
