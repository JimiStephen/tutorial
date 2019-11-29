package com.jimi.javase.nio.channel;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 *
 *In Java NIO you can transfer data directly from one channel to another, if one of the channels is a FileChannel.
 * The FileChannel class has a transferTo() and a transferFrom() method which does this for you.
 * @author xianyao.ye@ucarinc.com
 * @version 1.0
 * @date 2019/11/29 10:04
 */
public class ChannelTransfersTest {

    public static void main(String[] args) throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
        FileChannel      fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count    = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);

        fromChannel.transferTo(position, count, toChannel);

    }
}
