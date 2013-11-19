/*
 * Copyright 2013 Jeanfrancois Arcand
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.atanor.hdconnect.async;

import java.util.logging.Logger;

import javax.inject.Singleton;

import org.atmosphere.client.TrackMessageSizeInterceptor;
import org.atmosphere.config.service.Disconnect;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Post;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.gwt20.managed.AtmosphereMessageInterceptor;
import org.atmosphere.gwt20.server.GwtRpcInterceptor;
import org.atmosphere.interceptor.AtmosphereResourceLifecycleInterceptor;
import org.atmosphere.interceptor.SuspendTrackerInterceptor;

/**
 * Super simple managed echo application that use two broadcaster for pushing
 * data back to the client.
 */
@ManagedService(path = "/HdConnect/atmosphere/async", interceptors = {
/**
 * Handle lifecycle for us
 */
AtmosphereResourceLifecycleInterceptor.class,
/**
 * Send to the client the size of the message to prevent serialization error.
 */
TrackMessageSizeInterceptor.class,
/**
 * Serialize/Deserialize GWT message for us
 */
GwtRpcInterceptor.class,
/**
 * Make sure our {@link AtmosphereResourceEventListener#onSuspend} is only
 * called once for transport that reconnect on every requests.
 */
SuspendTrackerInterceptor.class,
/**
 * Deserialize the GWT message
 */
AtmosphereMessageInterceptor.class })
@Singleton
public class ImageResource {

	static final Logger logger = Logger.getLogger("AtmosphereHandler");

	@Ready
	public void onReady(final AtmosphereResource r) {
		logger.info("Received RPC GET");
		// Look up a new Broadcaster used for pushing who is connected.
		AsyncConnector.getBroadcaster().addAtmosphereResource(r).broadcast("Browser UUID: " + r.uuid() + " connected.");
	}

	@Disconnect
	public void disconnected(AtmosphereResourceEvent event) {
		if (event.isCancelled()) {
			logger.info("User:" + event.getResource().uuid() + " unexpectedly disconnected");
		} else if (event.isClosedByClient()) {
			logger.info("User:" + event.getResource().uuid() + " closed the connection");
		}
	}

	@Post
	public void post(AtmosphereResource r) {
		logger.info("POST received with transport + " + r.transport());
		// TODO put handler for client call
	}

}
