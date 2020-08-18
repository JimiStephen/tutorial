package com.jimi.commons.httpclient;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;


public class HttpClientFactory {

    private static HttpClientFactory httpFactory = null;

    private HttpClientFactory() {
    };

    public static HttpClientFactory getInstance() {
        if (httpFactory == null) {
            httpFactory = new HttpClientFactory();
            // httpClient = httpFactory.createHttpClient();
        }
        return httpFactory;
    }

    public CloseableHttpClient getHttpClient() {
        return httpFactory.createHttpClient(null,0);
        // return httpClient;
    }

    public CloseableHttpClient getHttpClient(int retryCount) {
        return httpFactory.createHttpClient(null,retryCount);
        // return httpClient;
    }

    public CloseableHttpClient getHttpClient(String SSL) {
        return httpFactory.createHttpClient(SSL,0);
        // return httpClient;
    }

    public CloseableHttpClient getHttpClient(String SSL,int retryCount) {
        return httpFactory.createHttpClient(SSL,retryCount);
        // return httpClient;
    }

    private CloseableHttpClient createHttpClient(String SSL,int retryCount) {
        CloseableHttpClient httpClient = null;
        if (httpClient == null) {
            try {
                // SSL实现免证书
                SSLContext sslContext = new SSLContextBuilder().useTLS()
                        .useSSL().loadTrustMaterial(null, new TrustStrategy() {
                            @Override
                            public boolean isTrusted(
                                    java.security.cert.X509Certificate[] chain,
                                    String authType)
                                    throws CertificateException {
                                return true;
                            }
                        }).build();
                X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
                    @Override
                    public boolean verify(String arg0, SSLSession session) {
                        return true;
                    }

                    @Override
                    public void verify(String host, SSLSocket ssl)
                            throws IOException {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void verify(String host, X509Certificate cert)
                            throws SSLException {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void verify(String host, String[] cns,
                                       String[] subjectAlts) throws SSLException {
                        // TODO Auto-generated method stub

                    }
                };
                // HTTP协议错误 （家乐福）//需要进行适配
                SSLConnectionSocketFactory sslSocketFactory = this.getSSL(
                        sslContext, hostnameVerifier, SSL);
                RegistryBuilder<ConnectionSocketFactory> registry = RegistryBuilder
                        .<ConnectionSocketFactory> create()
                        .register("http", PlainConnectionSocketFactory.INSTANCE)
                        .register("https", sslSocketFactory);
                HttpClientConnectionManager connectionManager = new HttpClientConnectionManager(registry.build());
                connectionManager.setMaxTotal(500);
                connectionManager.setDefaultMaxPerRoute(20);
                SocketConfig socketConfig = SocketConfig.custom()
                        .setSoKeepAlive(true).setTcpNoDelay(true).build();

                CustomHttpRequestRetryHandler retryHandler= null;
                if (retryCount>0) {
                    retryHandler=new CustomHttpRequestRetryHandler(retryCount,true);
                }else {
                    retryHandler=new CustomHttpRequestRetryHandler(4,true);
                }
                LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
                HttpClientBuilder httpClientBuilder = getHttpClientBuilder()
                        .setConnectionManager(connectionManager)
                        .setRedirectStrategy(redirectStrategy)
                        .setDefaultSocketConfig(socketConfig)
                        .setRetryHandler(retryHandler)
                        // 重试请求5次 new DefaultHttpRequestRetryHandler(5, true)
                        .setKeepAliveStrategy(new CustomConnectionKeepAliveStrategy());
                // 监控管理PoolingHttpClientConnectionManager链接池
                new IdleConnectionMonitorThread(connectionManager).start();
                httpClient = httpClientBuilder.build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return httpClient;
    }

    private SSLConnectionSocketFactory getSSL(SSLContext sslContext,
                                              X509HostnameVerifier hostnameVerifier, String SSL) {
        if (SSL == null) {
            return new SSLConnectionSocketFactory(sslContext, null, null,
                    hostnameVerifier);
        } else {
            return new SSLConnectionSocketFactory(sslContext,
                    new String[] { SSL }, null, hostnameVerifier);
        }
    }

    private HttpClientBuilder getHttpClientBuilder() {
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.addInterceptorFirst(new HttpRequestInterceptor() {

            public void process(final HttpRequest request,
                                final HttpContext context) throws HttpException,
                    IOException {
                if (!request.containsHeader("Accept-Encoding")) {
                    request.addHeader("Accept-Encoding", "gzip");
                }
            }

        });

        httpClientBuilder.addInterceptorLast(new HttpRequestInterceptor() {
            public void process(final HttpRequest request,
                                final HttpContext context) throws HttpException,
                    IOException {
                if (!request.containsHeader("Accept-Encoding")) {
                    request.addHeader("Accept-Encoding", "gzip");
                }
            }

        });

        httpClientBuilder.addInterceptorFirst(new HttpResponseInterceptor() {
            public void process(final HttpResponse response,
                                final HttpContext context) throws HttpException,
                    IOException {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    Header ceheader = entity.getContentEncoding();
                    if (ceheader != null) {
                        HeaderElement[] codecs = ceheader.getElements();
                        for (int i = 0; i < codecs.length; i++) {
                            if (codecs[i].getName().equalsIgnoreCase("gzip")) {
                                return;
                            }
                        }
                    }
                }
            }

        });

        httpClientBuilder.addInterceptorLast(new HttpResponseInterceptor() {
            public void process(final HttpResponse response,
                                final HttpContext context) throws HttpException,
                    IOException {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    Header ceheader = entity.getContentEncoding();
                    if (ceheader != null) {
                        HeaderElement[] codecs = ceheader.getElements();
                        for (int i = 0; i < codecs.length; i++) {
                            if (codecs[i].getName().equalsIgnoreCase("gzip")) {
                                return;
                            }
                        }
                    }
                }
            }

        });

        List<Header> headers = new ArrayList<Header>();

        headers.add(new BasicHeader(
                "User-Agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.151 Safari/535.19"));
        headers.add(new BasicHeader("Accept-Language",
                "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4"));
        headers.add(new BasicHeader("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate,sdch"));
        // headers.add(new BasicHeader("Content-Type",
        // "application/x-www-form-urlencoded")); //
        // 此设置会覆盖系统自带的Content-Type，造成提交失败，因此删除
        headers.add(new BasicHeader("Connection", "keep-alive"));
        return httpClientBuilder.setDefaultHeaders(headers);
    }

}
