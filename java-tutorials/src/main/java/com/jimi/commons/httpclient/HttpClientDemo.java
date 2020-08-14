package com.jimi.commons.httpclient;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/8/13 9:02
 */
public class HttpClientDemo {
    public static void main(String[] args) {
        try {


            URL url = new URL("http://www.zhipin.com");
            HttpClient httpClient = HttpClient.New(url);

            InputStream is = httpClient.getInputStream();
            byte[] buff = new byte[516];
           while ( is.read(buff) > -1){
               System.out.println(String.valueOf(buff));
           };
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
