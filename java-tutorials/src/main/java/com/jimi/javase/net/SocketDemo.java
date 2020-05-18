package com.jimi.javase.net;


import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/8 10:24
 */
public class SocketDemo {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();

            SocketAddress socketAddress = new InetSocketAddress(7070);
            serverSocket.bind(socketAddress);

            Socket socket  = serverSocket.accept();
            InputStream  is = socket.getInputStream();

            byte[] buffer = new byte[56];
            while (is.read(buffer) > 0){
                System.out.println(new String(buffer));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
