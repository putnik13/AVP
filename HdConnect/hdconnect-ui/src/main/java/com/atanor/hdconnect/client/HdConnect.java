package com.atanor.hdconnect.client;

import java.util.List;

import org.atmosphere.gwt20.client.Atmosphere;
import org.atmosphere.gwt20.client.AtmosphereCloseHandler;
import org.atmosphere.gwt20.client.AtmosphereMessageHandler;
import org.atmosphere.gwt20.client.AtmosphereOpenHandler;
import org.atmosphere.gwt20.client.AtmosphereReopenHandler;
import org.atmosphere.gwt20.client.AtmosphereRequest;
import org.atmosphere.gwt20.client.AtmosphereRequestConfig;
import org.atmosphere.gwt20.client.AtmosphereResponse;
import org.atmosphere.gwt20.client.managed.RPCEvent;
import org.atmosphere.gwt20.client.managed.RPCSerializer;

import com.atanor.hdconnect.client.json.JsonConverters;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HdConnect implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button button = new Button("Hello!!");
		
		RootPanel.get().add(button);

		RPCSerializer rpcSerializer = GWT.create(RPCSerializer.class);

		AtmosphereRequestConfig rpcRequestConfig = AtmosphereRequestConfig.create(rpcSerializer);

		rpcRequestConfig.setUrl(GWT.getModuleBaseURL() + "atmosphere/async");
		rpcRequestConfig.setTransport(AtmosphereRequestConfig.Transport.WEBSOCKET);
		rpcRequestConfig.setFallbackTransport(AtmosphereRequestConfig.Transport.STREAMING);
		rpcRequestConfig.setOpenHandler(new AtmosphereOpenHandler() {
			@Override
			public void onOpen(AtmosphereResponse response) {
				System.out.println("RPC Connection opened");
			}
		});
		rpcRequestConfig.setReopenHandler(new AtmosphereReopenHandler() {
			@Override
			public void onReopen(AtmosphereResponse response) {
				System.out.println("RPC Connection reopened");
			}
		});
		rpcRequestConfig.setCloseHandler(new AtmosphereCloseHandler() {
			@Override
			public void onClose(AtmosphereResponse response) {
				System.out.println("RPC Connection closed");
			}
		});
		rpcRequestConfig.setMessageHandler(new AtmosphereMessageHandler() {
			@Override
			public void onMessage(AtmosphereResponse response) {
				List<RPCEvent> messages = response.getMessages();
				for (RPCEvent event : messages) {
					System.out.println("received message through RPC: " + event.getMessage());
					final Snapshot snapshot = JsonConverters.SNAPSHOT_READER.read(event.getMessage());
					System.out.println("snapshot: " + snapshot.toString());
				}
			}
		});
		rpcRequestConfig.setFlags(AtmosphereRequestConfig.Flags.enableProtocol);
		rpcRequestConfig.setFlags(AtmosphereRequestConfig.Flags.trackMessageLength);

		Atmosphere atmosphere = Atmosphere.create();
		final AtmosphereRequest rpcRequest = atmosphere.subscribe(rpcRequestConfig);

		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				try {
					// AtmosphereMessage myevent = new RPCEvent();
					// myevent.setMessage("Hello from Client!");
					rpcRequest.push(new RPCEvent("Hello from Client!"));
				} catch (SerializationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
