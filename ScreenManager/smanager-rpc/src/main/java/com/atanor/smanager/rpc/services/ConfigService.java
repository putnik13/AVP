package com.atanor.smanager.rpc.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the Config service.
 */
@RemoteServiceRelativePath("config")
public interface ConfigService extends RemoteService {
	List<String> getAvailableConfigurations();
}
