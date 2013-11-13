package com.atanor.vrecorder.facades.palantir;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class PalantirClient {

	private static final int CONNECTION_TIMEOUT = 5000;

	private final IoConnector connector;
	private IoSession session;

	@Inject
	public PalantirClient(@Named("palantir.url") String url, @Named("palantir.port") String port) {
		connector = new NioSocketConnector();

		connector.setConnectTimeoutMillis(CONNECTION_TIMEOUT);
		connector.getSessionConfig().setReadBufferSize(2048);
		connector.setDefaultRemoteAddress(new InetSocketAddress(url, Integer.valueOf(port)));

		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

		connector.setHandler(new IoHandlerAdapter() {

			@Override
			public void sessionClosed(IoSession iosession) throws Exception {
				TimeUnit.SECONDS.sleep(2);
				connect();
			}

			@Override
			public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
				session.close(false);
				cause.printStackTrace();
			}

		});
		
		connect();
	}

	private void connect() {
		final ConnectFuture future = connector.connect();
		future.awaitUninterruptibly();
		if (!future.isConnected()) {
			return;
		}
		session = future.getSession();
		session.getConfig().setUseReadOperation(true);
	}

	public void send(final String message) {
		if (session == null || !session.isConnected()) {
			throw new IllegalStateException("Application disconnected from Palantir server");
		}
		
		session.write(message);
		session.close(false);
	}
}
