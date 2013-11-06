package com.atanor.vrecorder.servlet;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.atanor.vrecorder.domain.facades.PlayerFacade;
import com.atanor.vrecorder.rpc.services.SnapshotService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Singleton
@SuppressWarnings("serial")
public class SnapshotServlet extends RemoteServiceServlet implements SnapshotService {

	@Inject
	private PlayerFacade player;

	@Override
	public String getSnapshot() {		
		return player.getSnapshot();
	}

}
