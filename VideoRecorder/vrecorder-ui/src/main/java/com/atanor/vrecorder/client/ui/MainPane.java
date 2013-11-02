package com.atanor.vrecorder.client.ui;

import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class MainPane extends HLayout {

	public MainPane() {
		setWidth100();
		setHeight100();
		
		final IButton startRecord = new IButton("Start Recording");
		startRecord.setWidth(90);
		final IButton stopRecord = new IButton("Stop Recording");
		stopRecord.setWidth(90);

		final HLayout headerPane = new HLayout();
		headerPane.addMembers(startRecord, stopRecord);
		headerPane.setMembersMargin(10);

		final ListGrid listGrid = new ListGrid();
		ListGridField fileName = new ListGridField("fileName");
		ListGridField duration = new ListGridField("Duration");
		listGrid.setFields(fileName, duration);

		final Label freeSpace = new Label();
		freeSpace.setContents("Free Space on disk: ... Mb is available");

		final VLayout vLayout = new VLayout();
		vLayout.setWidth("80%");
		vLayout.setHeight100();
		vLayout.setMembersMargin(50);
		vLayout.addMembers(headerPane, listGrid, freeSpace);

		addMembers(new LayoutSpacer(), vLayout, new LayoutSpacer());
	}
}
