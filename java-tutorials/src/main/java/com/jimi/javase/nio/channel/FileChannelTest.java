package com.jimi.javase.nio.channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author jimi
 * @version 1.0
 * @date 2019/11/29 15:01
 */
public class FileChannelTest {
    public static void main(String[] args) throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = inChannel.read(buf);

        String newData = "New String to write to file..." + System.currentTimeMillis();

        ByteBuffer bufWrite = ByteBuffer.allocate(48);
        bufWrite.clear();
        bufWrite.put(newData.getBytes());

        buf.flip();

        while (buf.hasRemaining()) {
            inChannel.write(buf);
        }
        long pos = inChannel.position();

        inChannel.position(pos +123);
//        the file size of the file the channel
        long fileSize = inChannel.size();
                // When you truncate a file, you cut it off at a given length.;
        inChannel.truncate(1024);
        //The FileChannel.force() method flushes all unwritten data from the channel to the disk.
        inChannel.force(true);
        inChannel.close();
    }
}
