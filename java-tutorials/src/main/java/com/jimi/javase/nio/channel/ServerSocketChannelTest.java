package com.jimi.javase.nio.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author xianyao.ye@ucarinc.com
 * @version 1.0
 * @date 2019/11/29 15:27
 */
public class ServerSocketChannelTest {
    public static void main(String[] args) throws Exception {
        //Opening a ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        while(true){
            SocketChannel socketChannel =
                    serverSocketChannel.accept();
            if(socketChannel != null){
                //do something with socketChannel...
                ByteBuffer dst = ByteBuffer.allocate(48);
                socketChannel.read(dst);
                break;
            }
            //do something with socketChannel...
        }

        serverSocketChannel.close();
    }
}
