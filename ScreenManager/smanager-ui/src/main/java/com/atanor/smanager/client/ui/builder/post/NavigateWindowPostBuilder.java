package com.atanor.smanager.client.ui.builder.post;

import com.atanor.smanager.client.ui.WindowLabel;

public class NavigateWindowPostBuilder implements WindowPostBuilder {

	@Override
	public void doPostBuild(WindowLabel preset) {
		preset.setBorder("1px inset black");
		preset.setBackgroundColor("darkgrey");
	}

}
