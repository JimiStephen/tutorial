package com.jimi.javase.nio.selector;

import java.io.RandomAccessFile;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author jimi
 * @version 1.0
 * @date 2019/11/29 10:33
 */
public class SelectorTest {
    public static void main(String[] args) throws Exception {
        RandomAccessFile aFile = null;
        aFile = new RandomAccessFile(ClassLoader.getSystemResource("./data/nio-data.txt").getFile(), "rw");
        FileChannel inChannel = aFile.getChannel();

        SocketChannel channel = SocketChannel.open();

        // create selector
        Selector selector = Selector.open();
        // Registering Channels with the Selector
        channel.configureBlocking(false);

        SelectionKey key = channel.register(selector, SelectionKey.OP_READ);
        /*
        The Channel must be in non-blocking mode to be used with a Selector. This means that you cannot use FileChannel's
        with a Selector since FileChannel's cannot be switched into non-blocking mode. Socket channels will work fine though.
        
        Notice the second parameter of the register() method. This is an "interest set", meaning what events you are interested
        in listening for in the Channel, via the Selector. There are four different events you can listen for:
        
        Connect
        Accept
        Read
        Write
        A channel that "fires an event" is also said to be "ready" for that event. So, a channel that has connected successfully
        to another server is "connect ready". A server socket channel which accepts an incoming connection is "accept" ready. A
        channel that has data ready to be read is "read" ready. A channel that is ready for you to write data to it, is "write" ready.
        
        These four events are represented by the four SelectionKey constants:
        
        SelectionKey.OP_CONNECT
        SelectionKey.OP_ACCEPT
        SelectionKey.OP_READ
        SelectionKey.OP_WRITE
        */
        int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;

        // boolean isInterestedInAccept = interestSet & SelectionKey.OP_ACCEPT;
        // boolean isInterestedInConnect = interestSet & SelectionKey.OP_CONNECT;
        // boolean isInterestedInRead = interestSet & SelectionKey.OP_READ;
        // boolean isInterestedInWrite = interestSet & SelectionKey.OP_WRITE;

        int readySet = key.readyOps();
        key.isAcceptable();
        key.isConnectable();
        key.isReadable();
        key.isWritable();


        Channel selKeyChannel  = key.channel();

        Selector keySelector = key.selector();
        Object theObject = null;
        key.attach(theObject);

        Object attachedObj = key.attachment();
        Set<SelectionKey> selectedKeys = selector.selectedKeys();

        Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

        while(keyIterator.hasNext()) {

            SelectionKey itKey = keyIterator.next();

            if(itKey.isAcceptable()) {
                // a connection was accepted by a ServerSocketChannel.

            } else if (itKey.isConnectable()) {
                // a connection was established with a remote server.

            } else if (itKey.isReadable()) {
                // a channel is ready for reading

            } else if (itKey.isWritable()) {
                // a channel is ready for writing
            }

            keyIterator.remove();
        }
    }

    public static void SelectorFull() throws Exception{
        Selector selector = Selector.open();
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);

        SelectionKey selKey = channel.register(selector, SelectionKey.OP_READ);


        while(true) {

            int readyChannels = selector.selectNow();

            if(readyChannels == 0) continue;


            Set<SelectionKey> selectedKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while(keyIterator.hasNext()) {

                SelectionKey key = keyIterator.next();

                if(key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.

                } else if (key.isConnectable()) {
                    // a connection was established with a remote server.

                } else if (key.isReadable()) {
                    // a channel is ready for reading

                } else if (key.isWritable()) {
                    // a channel is ready for writing
                }

                keyIterator.remove();
            }
        }
    }
}
