package com.atanor.vrecorder.rpc.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the Recording Service.
 */
@RemoteServiceRelativePath("snapshot")
public interface SnapshotService extends RemoteService {

	String getSnapshot();
}
