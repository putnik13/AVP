package com.atanor.vserver.common.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the Hello Service.
 */
@RemoteServiceRelativePath("hello")
public interface HelloService extends RemoteService {

	void hello(String param);
}
