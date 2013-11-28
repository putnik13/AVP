package com.atanor.vserver.vsadmin.client.events;

import com.atanor.vserver.vsadmin.client.ui.sections.Section;
import com.google.gwt.event.shared.GwtEvent;

public class SectionAnimationStartedEvent extends GwtEvent<SectionAnimationStartedHandler> {

	private static Type<SectionAnimationStartedHandler> TYPE;

	public static Type<SectionAnimationStartedHandler> getType() {
		if (TYPE == null) {
			TYPE = new Type<SectionAnimationStartedHandler>();
		}
		return TYPE;
	}

	private Section section;

	public SectionAnimationStartedEvent(final Section section) {
		this.section = section;
	}
	
	public Section getSection() {
		return section;
	}
	
	@Override
	public final Type<SectionAnimationStartedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SectionAnimationStartedHandler handler) {
		handler.onAnimationStarted(this);
	}
}