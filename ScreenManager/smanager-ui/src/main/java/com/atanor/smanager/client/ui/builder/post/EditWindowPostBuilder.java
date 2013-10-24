package com.atanor.smanager.client.ui.builder.post;

import com.atanor.smanager.client.ui.WindowLabel;
import com.atanor.smanager.client.ui.style.EditWindowStyleApplier;

public class EditWindowPostBuilder implements WindowPostBuilder {

	private final Long leftOffset;
	private final Long topOffset;

	public EditWindowPostBuilder(final Long leftOffset, final Long topOffset) {
		this.leftOffset = leftOffset;
		this.topOffset = topOffset;
	}

	@Override
	public void doPostBuild(WindowLabel window) {

		new EditWindowStyleApplier().applyStyle(window);

		window.setContents(window.getDto().getSource());

		// adjust left and top position in according to display panel
		final int newLeft = window.getLeft() + leftOffset.intValue();
		window.setLeft(newLeft);
		
		final int newTop = window.getTop() + topOffset.intValue();
		window.setTop(newTop);
		
		// set offsets for back sizes scaling
		window.setLeftOffset(leftOffset);
		window.setTopOffset(topOffset);
	}

}
