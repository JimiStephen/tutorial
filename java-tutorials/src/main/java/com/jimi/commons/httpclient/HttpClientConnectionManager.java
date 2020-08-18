package com.jimi.commons.httpclient;

import java.util.concurrent.TimeUnit;

import org.apache.http.config.Registry;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.SchemePortResolver;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * 重写PoolingHttpClientConnectionManager增加关闭标识
 * 
 *
 */
public class HttpClientConnectionManager extends
		PoolingHttpClientConnectionManager {

	private boolean isShutdown = false;

	public HttpClientConnectionManager() {
		super();
	}

	public HttpClientConnectionManager(
			HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory) {
		super(connFactory);
	}

	public HttpClientConnectionManager(long timeToLive, TimeUnit tunit) {
		super(timeToLive, tunit);
	}

	public HttpClientConnectionManager(
			Registry<ConnectionSocketFactory> socketFactoryRegistry,
			DnsResolver dnsResolver) {
		super(socketFactoryRegistry, dnsResolver);
	}

	public HttpClientConnectionManager(
			Registry<ConnectionSocketFactory> socketFactoryRegistry,
			HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory,
			DnsResolver dnsResolver) {
		super(socketFactoryRegistry, connFactory, dnsResolver);
	}

	public HttpClientConnectionManager(
			Registry<ConnectionSocketFactory> socketFactoryRegistry,
			HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory,
			SchemePortResolver schemePortResolver, DnsResolver dnsResolver,
			long timeToLive, TimeUnit tunit) {
		super(socketFactoryRegistry, connFactory, schemePortResolver,
				dnsResolver, timeToLive, tunit);
	}

	public HttpClientConnectionManager(
			Registry<ConnectionSocketFactory> socketFactoryRegistry,
			HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory) {
		super(socketFactoryRegistry, connFactory);
	}

	public HttpClientConnectionManager(
			Registry<ConnectionSocketFactory> socketFactoryRegistry) {
		super(socketFactoryRegistry);
	}

	@Override
	public void shutdown() {
		this.isShutdown = true;
		super.shutdown();
	}

	public boolean isShutdown() {
		return isShutdown;
	}

}
