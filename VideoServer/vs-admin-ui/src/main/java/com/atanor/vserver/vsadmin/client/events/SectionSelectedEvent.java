package com.atanor.vserver.vsadmin.client.events;

import com.atanor.vserver.vsadmin.client.ui.sections.Section;
import com.google.gwt.event.shared.GwtEvent;

public class SectionSelectedEvent extends GwtEvent<SectionSelectedHandler> {

	private static Type<SectionSelectedHandler> TYPE;

	public static Type<SectionSelectedHandler> getType() {
		if (TYPE == null) {
			TYPE = new Type<SectionSelectedHandler>();
		}
		return TYPE;
	}

	private Section section;

	public SectionSelectedEvent(final Section section) {
		this.section = section;
	}
	
	public Section getSection() {
		return section;
	}

	@Override
	public final Type<SectionSelectedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SectionSelectedHandler handler) {
		handler.onSectionSelected(this);
	}
}