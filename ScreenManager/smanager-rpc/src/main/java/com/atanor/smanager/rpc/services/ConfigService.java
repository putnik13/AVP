package com.atanor.smanager.rpc.services;

import java.util.List;

import com.atanor.smanager.shared.AppConstants;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the Config service.
 */
@RemoteServiceRelativePath(AppConstants.CONFIG_PATH)
public interface ConfigService extends RemoteService {
	List<String> getAvailableConfigurations();
}
