package com.atanor.vserver.vsadmin.client.injector;


import com.atanor.vserver.vsadmin.client.ui.ContentPane;
import com.atanor.vserver.vsadmin.client.ui.MainPane;
import com.atanor.vserver.vsadmin.client.ui.NavigatePane;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.web.bindery.event.shared.EventBus;

@GinModules(ClientModule.class)
public interface ClientInjector extends Ginjector {

	EventBus getEventBus();

	MainPane getMainPane();
	
	ContentPane getContentPane();
	
	NavigatePane getNavigatePane();
}
