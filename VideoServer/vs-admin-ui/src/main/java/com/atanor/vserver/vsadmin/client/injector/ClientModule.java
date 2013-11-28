package com.atanor.vserver.vsadmin.client.injector;

import com.atanor.vserver.vsadmin.client.ui.ContentPane;
import com.atanor.vserver.vsadmin.client.ui.HeaderPane;
import com.atanor.vserver.vsadmin.client.ui.SectionsPane;
import com.atanor.vserver.vsadmin.client.ui.MainPane;
import com.atanor.vserver.vsadmin.client.ui.NavigatePane;
import com.atanor.vserver.vsadmin.client.ui.StatusPane;
import com.atanor.vserver.vsadmin.client.ui.sections.BroadcastingSection;
import com.atanor.vserver.vsadmin.client.ui.sections.EditConfigurationSection;
import com.atanor.vserver.vsadmin.client.ui.sections.ShareConferenceSection;
import com.atanor.vserver.vsadmin.client.ui.sections.StreamControlSection;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class ClientModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(MainPane.class).in(Singleton.class);
		bind(ContentPane.class).in(Singleton.class);
		bind(NavigatePane.class).in(Singleton.class);
		bind(SectionsPane.class).in(Singleton.class);
		bind(HeaderPane.class).in(Singleton.class);
		bind(StatusPane.class).in(Singleton.class);
		bind(StreamControlSection.class).in(Singleton.class);
		bind(ShareConferenceSection.class).in(Singleton.class);
		bind(BroadcastingSection.class).in(Singleton.class);
		bind(EditConfigurationSection.class).in(Singleton.class);
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
	}

}
