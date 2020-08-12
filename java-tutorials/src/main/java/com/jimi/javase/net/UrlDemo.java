package com.jimi.javase.net;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/8/12 14:19
 */
public class UrlDemo {
    public static void main(String[] args) {


//          writeUrlForStream();

//        readUrlForStream();
//        getUrlContent();
    }

    private static void writeUrlForStream() {
        try {
            String witeUrl = "http://www.baidu.com";
//            String witeUrl ="http://example.com/servlet/ReverseServlet";

            String stringToReverse = "hello java url";
            URL url = new URL(witeUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            //write string to url connection ouputstream
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream());
            out.write("string=" + stringToReverse);
            out.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            String decodedString;
            while ((decodedString = in.readLine()) != null) {
                System.out.println(decodedString);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readUrlForStream() {
        try {
            URL baidu = new URL("http://www.baidu.com/");
            URLConnection yc = baidu.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getUrlContent() {
        try {
            URL indexUrl = new URL("http://www.zhipin.com");
            URLConnection indexUrlConnect = indexUrl.openConnection();
            InputStream content = (InputStream) indexUrlConnect.getContent();
            System.out.println(content);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
