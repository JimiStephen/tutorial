/**
 * @author jimi
 * @date 2019/11/28 15:13
 * @version 1.0
 */
package com.jimi.javase.nio.channel;
/*

Java NIO Channels are similar to streams with a few differences:

        You can both read and write to a Channels. Streams are typically one-way (read or write).
        Channels can be read and written asynchronously.
        Channels always read to, or write from, a Buffer.

Channel Implementations
Here are the most important Channel implementations in Java NIO:

FileChannel
DatagramChannel
SocketChannel
ServerSocketChannel

The FileChannel reads data from and to files.
The DatagramChannel can read and write data over the network via UDP.
The SocketChannel can read and write data over the network via TCP.
The ServerSocketChannel allows you to listen for incoming TCP connections, like a web server does. For each incoming connection a SocketChannel is created.
*/
