package com.jimi.javase.net;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/8 10:24
 */
public class SocketClientDemo {

    public static void main(String[] args) {
        try {
            SocketAddress socketAddress = new InetSocketAddress(7070);
            Socket socket = new Socket();
            socket.connect(socketAddress);
            OutputStream os = socket.getOutputStream();
            String str = "hello,socket";
            os.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
