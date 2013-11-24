package com.atanor.vserver.servlet;

import javax.inject.Singleton;

import com.atanor.vserver.common.services.HelloService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Singleton
@SuppressWarnings("serial")
public class HelloServlet extends RemoteServiceServlet implements HelloService {

	@Override
	public void hello(String param) {
		System.out.println(String.format("Hello from %s!!!", param));
	}

}
