package com.jimi.commons.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/8/13 9:02
 */
public class HttpClientDemo {
    public static void main(String[] args) {
        try {


            HttpUriRequest request = new HttpGet("http://www.zhipin.com");
            CloseableHttpResponse response = HttpClientFactory.getInstance().getHttpClient().execute(request);
            HttpEntity httpEntity = response.getEntity();
            String responseString = EntityUtils.toString(httpEntity);


            System.out.println(responseString);
            response.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
