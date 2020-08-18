package com.jimi.commons.httpclient;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.net.ssl.SSLException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.protocol.HttpContext;


/**
 * 请求重试HANDLER 设置java.io.IOException为可修复
 * 
 * @author qiusw 2014/12/9
 */
public class CustomHttpRequestRetryHandler extends
		DefaultHttpRequestRetryHandler {

	private Logger logger =Logger.getLogger(getClass().getSimpleName());
	
	public CustomHttpRequestRetryHandler() {
		super();
	}
	
	public CustomHttpRequestRetryHandler(final int retryCount, final boolean requestSentRetryEnabled) {
		super(retryCount, requestSentRetryEnabled);
	}

	/**
	 * return false: 停止重试
	 * return true: 继续重试
	 */
	@Override
	public boolean retryRequest(IOException exception, int executionCount,
			HttpContext context) {
		if(context.getAttribute("jobLog") != null){

			logger.warning("HttpRequest第{" + executionCount + "}次请求重试,共{"+super.getRetryCount()+"}次!" + exception.getMessage());
		}
		logger.warning("HttpRequest第{" + executionCount + "}次请求重试!");
		if(null != exception){
			logger.info("重试原因：" + exception.getLocalizedMessage());
		}
		// 超过最大次数就不重试了
		if (executionCount >= super.getRetryCount()) {
			return false;
		}
		if (exception instanceof SocketTimeoutException) {
			logger.warning("重试原因：连接超时异常");
			return true;
		}
		if (exception instanceof InterruptedIOException) {
			logger.warning("重试原因：中断IO异常");
			return true;
		}
		if (exception instanceof UnknownHostException) {
			// 目标服务器不可达
			logger.warning("重试原因：未知的HOST异常");
			return true;
		}
		if (exception instanceof ConnectTimeoutException) {
			logger.warning("重试原因：连接超时异常");
			return true;
		}
		if (exception instanceof SSLException) {
			logger.warning("重试原因：SSL协议异常");
			// ssl握手异常
			return false;
		}
		HttpClientContext clientContext = HttpClientContext.adapt(context);
		HttpRequest request = clientContext.getRequest();
		boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
		if (idempotent) {
			// 如果请求是幂等的，就再次尝试
			return true;
		}
		return false;
	}
}
