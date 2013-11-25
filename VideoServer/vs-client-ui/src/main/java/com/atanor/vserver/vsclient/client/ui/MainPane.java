package com.atanor.vserver.vsclient.client.ui;

import com.atanor.vserver.common.entity.Snapshot;
import com.atanor.vserver.vsclient.client.presenter.MainPanePresenter;
import com.google.gwt.user.client.ui.IsWidget;

public interface MainPane extends IsWidget {

	void setPresenter(MainPanePresenter presenter);
	
	void addSnapshot(Snapshot snapshot);
}
