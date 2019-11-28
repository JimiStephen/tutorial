package com.jimi.javase.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xianyao.ye@ucarinc.com
 * @version 1.0
 * @date 2019/11/28 15:18
 */
public class BasicChannelTest {
    public static void main(String[] args) {

        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("/data/nio-data.txt", "rw");
            FileChannel inChannel = aFile.getChannel();


            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = inChannel.read(buf);
            while (bytesRead != -1) {

                System.out.println("Read " + bytesRead);
                buf.flip();

                while(buf.hasRemaining()){
                    System.out.print((char) buf.get());
                }

                buf.clear();
                bytesRead = inChannel.read(buf);
            }
            aFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
