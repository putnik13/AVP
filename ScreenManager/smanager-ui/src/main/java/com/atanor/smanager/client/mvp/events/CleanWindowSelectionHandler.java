package com.atanor.smanager.client.mvp.events;

import com.google.gwt.event.shared.EventHandler;

public interface CleanWindowSelectionHandler extends EventHandler {

    void onCleanWindowSelection(CleanWindowSelectionEvent event);
}
