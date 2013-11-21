package com.atanor.hdconnect.client.async;

import java.util.List;

import org.atmosphere.gwt20.client.Atmosphere;
import org.atmosphere.gwt20.client.AtmosphereMessageHandler;
import org.atmosphere.gwt20.client.AtmosphereRequestConfig;
import org.atmosphere.gwt20.client.AtmosphereResponse;
import org.atmosphere.gwt20.client.managed.RPCEvent;
import org.atmosphere.gwt20.client.managed.RPCSerializer;

import com.atanor.hdconnect.client.Client;
import com.atanor.hdconnect.client.entity.Snapshot;
import com.atanor.hdconnect.client.events.SnapshotReceivedEvent;
import com.atanor.hdconnect.client.json.JsonConverters;
import com.google.gwt.core.client.GWT;

public class AsyncConnector {
	
	public static void connect(){
		final RPCSerializer rpcSerializer = GWT.create(RPCSerializer.class);

		final AtmosphereRequestConfig rpcReqConfig = AtmosphereRequestConfig.create(rpcSerializer);

		rpcReqConfig.setUrl(GWT.getModuleBaseURL() + "atmosphere/async");
		rpcReqConfig.setTransport(AtmosphereRequestConfig.Transport.WEBSOCKET);
		rpcReqConfig.setFallbackTransport(AtmosphereRequestConfig.Transport.STREAMING);
		
		rpcReqConfig.setMessageHandler(new AtmosphereMessageHandler() {
			@Override
			public void onMessage(AtmosphereResponse response) {
				List<RPCEvent> messages = response.getMessages();
				for (RPCEvent event : messages) {
					//System.out.println("received message through RPC: " + event.getMessage());
					final Snapshot snapshot = JsonConverters.SNAPSHOT_READER.read(event.getMessage());
					Client.getEventBus().fireEvent(new SnapshotReceivedEvent(snapshot));
				}
			}
		});
		rpcReqConfig.setFlags(AtmosphereRequestConfig.Flags.enableProtocol);
		rpcReqConfig.setFlags(AtmosphereRequestConfig.Flags.trackMessageLength);

		final Atmosphere asyncClient = Atmosphere.create();
		asyncClient.subscribe(rpcReqConfig);
	}
}
