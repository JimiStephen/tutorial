package com.jimi.javase.nio.buffer;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xianyao.ye@ucarinc.com
 * @version 1.0
 * @date 2019/11/29 10:00
 */
public class ScatterGatherTest {
    public static void main(String[] args) throws Exception {
        RandomAccessFile aFile = null;

        aFile = new RandomAccessFile(ClassLoader.getSystemResource("./data/nio-data.txt").getFile(), "rw");
        FileChannel inChannel = aFile.getChannel();

       //Scatter read
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);

        ByteBuffer[] bufferArray = {header, body};

        inChannel.read(bufferArray);

        ByteBuffer headerw = ByteBuffer.allocate(128);
        ByteBuffer bodyw   = ByteBuffer.allocate(1024);

        //gather write data into buffers

        ByteBuffer[] bufferArrayw = { headerw, bodyw };

        inChannel.write(bufferArrayw);
    }
}
