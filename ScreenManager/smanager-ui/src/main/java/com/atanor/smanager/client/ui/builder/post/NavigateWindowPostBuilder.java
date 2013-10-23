package com.atanor.smanager.client.ui.builder.post;

import com.atanor.smanager.client.ui.WindowLabel;

public class NavigateWindowPostBuilder implements WindowPostBuilder {

	@Override
	public void doPostBuild(WindowLabel preset) {
		preset.setBorder("1px solid black");
		preset.setBackgroundColor("darkgrey");
	}

}
