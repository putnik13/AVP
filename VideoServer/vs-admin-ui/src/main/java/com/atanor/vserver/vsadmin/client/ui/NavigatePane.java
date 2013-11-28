package com.atanor.vserver.vsadmin.client.ui;

import javax.inject.Inject;

import com.atanor.vserver.vsadmin.client.events.SectionAnimationStartedEvent;
import com.atanor.vserver.vsadmin.client.events.SectionAnimationStartedHandler;
import com.atanor.vserver.vsadmin.client.events.SectionSelectedEvent;
import com.atanor.vserver.vsadmin.client.ui.sections.Section;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class NavigatePane extends HLayout implements SectionAnimationStartedHandler {

	@Inject
	private EventBus eventBus;

	final IButton streamControl;
	final IButton shareConference;
	final IButton broadcasting;
	final IButton editConfiguration;

	public NavigatePane() {
		setWidth(200);
		setHeight100();
		setBackgroundColor("yellow");

		streamControl = new IButton("Stream Control");
		streamControl.setWidth100();
		streamControl.addClickHandler(buildClickHandler(Section.STREAM_CONTROL));

		shareConference = new IButton("Share Conference");
		shareConference.setWidth100();
		shareConference.addClickHandler(buildClickHandler(Section.SHARE_CONFERENCE));

		broadcasting = new IButton("Broadcasting");
		broadcasting.setWidth100();
		broadcasting.addClickHandler(buildClickHandler(Section.BROADCASTING));

		editConfiguration = new IButton("Edit Configuration");
		editConfiguration.setWidth100();
		editConfiguration.addClickHandler(buildClickHandler(Section.EDIT_CONFIGURATION));

		final VLayout controlsLayout = new VLayout();
		controlsLayout.setWidth100();
		controlsLayout.setHeight100();

		controlsLayout.addMembers(streamControl, shareConference, broadcasting, editConfiguration);
		addMember(controlsLayout);
	}

	private ClickHandler buildClickHandler(final Section section) {
		return new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new SectionSelectedEvent(section));
			}
		};
	}

	private void enableAllControls() {
		streamControl.enable();
		shareConference.enable();
		broadcasting.enable();
		editConfiguration.enable();
	}

	@Override
	public void onAnimationStarted(final SectionAnimationStartedEvent event) {
		enableAllControls();
		switch (event.getSection()) {
		case STREAM_CONTROL:
			streamControl.disable();
			break;
		case SHARE_CONFERENCE:
			shareConference.disable();
			break;
		case BROADCASTING:
			broadcasting.disable();
			break;
		case EDIT_CONFIGURATION:
			editConfiguration.disable();
			break;
		default:
			throw new IllegalStateException("Section not found " + event.getSection().name());
		}
	}
}
