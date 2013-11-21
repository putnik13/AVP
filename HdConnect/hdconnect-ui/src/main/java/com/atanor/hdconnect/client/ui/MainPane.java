package com.atanor.hdconnect.client.ui;

import com.atanor.hdconnect.client.entity.Snapshot;
import com.atanor.hdconnect.client.presenter.MainPanePresenter;
import com.google.gwt.user.client.ui.IsWidget;

public interface MainPane extends IsWidget {

	void setPresenter(MainPanePresenter presenter);
	
	void addSnapshot(Snapshot snapshot);
}
