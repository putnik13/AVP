package com.atanor.vserver.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atanor.vserver.events.OpenSessionEvent;
import com.google.common.eventbus.EventBus;

@Singleton
@SuppressWarnings("serial")
public class SessionOpenServlet extends HttpServlet {

	@Inject
	private EventBus eventBus;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		eventBus.post(new OpenSessionEvent());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
