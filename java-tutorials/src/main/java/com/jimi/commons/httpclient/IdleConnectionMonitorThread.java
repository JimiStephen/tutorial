package com.jimi.commons.httpclient;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


/**
 * 管理PoolingHttpClientConnectionManager 关闭失效链接 关闭5分钟未使用的空闲链接
 * 
 *
 */
public class IdleConnectionMonitorThread extends Thread {

	private Logger logger = Logger.getLogger(getClass().getSimpleName());

	private final HttpClientConnectionManager connMgr;
	private volatile boolean shutdown;

	public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
		super();
		this.connMgr = connMgr;
	}

	@Override
	public void run() {
		try {
			while (!shutdown) {
				synchronized (this) {
					wait(10000);
					if (connMgr.isShutdown()) {
						this.shutdown = true;
					}
					//logger.info(connMgr.getTotalStats().toString());
					// 关闭失效的连接
					connMgr.closeExpiredConnections();
					// 可选的, 关闭5分钟内不活动的连接
					connMgr.closeIdleConnections(5, TimeUnit.MINUTES);
				}
			}
		} catch (InterruptedException ex) {
			// terminate
		}
	}

	public void shutdown() {
		shutdown = true;
		synchronized (this) {
			notifyAll();
		}
	}

}