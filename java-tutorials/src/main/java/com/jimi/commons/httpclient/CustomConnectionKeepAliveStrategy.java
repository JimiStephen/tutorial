package com.jimi.commons.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;

/**
 * 重写DefaultConnectionKeepAliveStrategy 
 * 如果KeepAlive = -1 设置为60秒
 *	2014/12/9
 */
public class CustomConnectionKeepAliveStrategy extends DefaultConnectionKeepAliveStrategy{

	@Override
	public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
			long keepAlive = super.getKeepAliveDuration(response, context);
	        if (keepAlive == -1) {
	            keepAlive = 90*1000;
	        }
	        return keepAlive;
    }
}
