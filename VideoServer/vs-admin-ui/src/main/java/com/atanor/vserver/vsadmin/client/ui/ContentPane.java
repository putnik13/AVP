package com.atanor.vserver.vsadmin.client.ui;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import com.atanor.vserver.vsadmin.client.events.SectionAnimationStartedEvent;
import com.atanor.vserver.vsadmin.client.events.SectionSelectedEvent;
import com.atanor.vserver.vsadmin.client.events.SectionSelectedHandler;
import com.atanor.vserver.vsadmin.client.ui.sections.BroadcastingSection;
import com.atanor.vserver.vsadmin.client.ui.sections.EditConfigurationSection;
import com.atanor.vserver.vsadmin.client.ui.sections.ShareConferenceSection;
import com.atanor.vserver.vsadmin.client.ui.sections.StreamControlSection;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.AnimationCallback;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.HLayout;

public class ContentPane extends HLayout implements SectionSelectedHandler {

	private static final int ANIMATION_TIME = 500;

	@Inject
	private EventBus eventBus;
	
	private final StreamControlSection streamControl;
	private final ShareConferenceSection shareConference;
	private final BroadcastingSection broadcasting;
	private final EditConfigurationSection editConfiguration;

	private final List<HLayout> sections;

	private HLayout selectedSection;
	private boolean isAnimationInProgress = false;

	@Inject
	public ContentPane(final StreamControlSection streamControl, final ShareConferenceSection shareConference,
			final BroadcastingSection broadcasting, final EditConfigurationSection editConfiguration) {
		this.streamControl = streamControl;
		this.shareConference = shareConference;
		this.broadcasting = broadcasting;
		this.editConfiguration = editConfiguration;

		this.sections = Arrays.asList(streamControl, shareConference, broadcasting, editConfiguration);

		setWidth100();
		setHeight100();

		final Canvas canvas = new Canvas();
		canvas.setWidth100();
		canvas.setHeight100();
		canvas.setOverflow(Overflow.HIDDEN);

		canvas.addChild(streamControl);
		canvas.addChild(shareConference);
		canvas.addChild(broadcasting);
		canvas.addChild(editConfiguration);

		setToFrontSection(streamControl);

		addMember(canvas);
	}

	@Override
	public void onSectionSelected(final SectionSelectedEvent event) {
		if (isAnimationInProgress) {
			return;
		}

		HLayout newSelectedSection = null;
		switch (event.getSection()) {
		case STREAM_CONTROL:
			newSelectedSection = streamControl;
			break;
		case SHARE_CONFERENCE:
			newSelectedSection = shareConference;
			break;
		case BROADCASTING:
			newSelectedSection = broadcasting;
			break;
		case EDIT_CONFIGURATION:
			newSelectedSection = editConfiguration;
			break;
		default:
			throw new IllegalStateException("Section not found " + event.getSection().name());
		}

		eventBus.fireEvent(new SectionAnimationStartedEvent(event.getSection()));
		move(newSelectedSection);
	}

	private void move(final HLayout section) {
		isAnimationInProgress = true;
		section.animateMove(0, 0, null, ANIMATION_TIME);
		selectedSection.animateMove(0, selectedSection.getHeight(), new AnimationCallback() {

			@Override
			public void execute(boolean earlyFinish) {
				setToFrontSection(section);
				isAnimationInProgress = false;
			}

		}, 500);

	}

	private void setToFrontSection(final HLayout selected) {
		for (HLayout section : sections) {
			if (section != selected) {
				resetPosition(section);
			}
		}
		selected.bringToFront();
		this.selectedSection = selected;
	}

	private void resetPosition(final HLayout section) {
		section.moveTo(0, -section.getHeight());
	}

}
