package com.jimi.javase.nio.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author jimi
 * @version 1.0
 * @date 2019/11/29 15:17
 */
public class SocketChannelTest {
    public static void main(String[] args) throws Exception{
        //open channel
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));

        //read from channel;
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = socketChannel.read(buf);

        //write channel
        String newData = "New String to write to file..." + System.currentTimeMillis();

        ByteBuffer writeBuf = ByteBuffer.allocate(48);
        writeBuf.clear();
        writeBuf.put(newData.getBytes());

        writeBuf.flip();

        while(writeBuf.hasRemaining()) {
            socketChannel.write(buf);
        }

        //close channel
        socketChannel.close();
    }
}
