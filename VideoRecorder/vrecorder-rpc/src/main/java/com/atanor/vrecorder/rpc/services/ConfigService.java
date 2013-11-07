package com.atanor.vrecorder.rpc.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the Config Service.
 */
@RemoteServiceRelativePath("config")
public interface ConfigService extends RemoteService {

	Long getAvailableSpaceSize();
}
