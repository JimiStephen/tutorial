Java NIO (New IO) is an alternative IO API for Java (from Java 1.4), meaning alternative to the standard Java IO 
and Java Networking API's. Java NIO offers a different way of working with IO than the standard IO API's.

### Java NIO: Channels and Buffers
In the standard IO API you work with byte streams and character streams. In NIO you work with channels and buffers.
 Data is always read from a channel into a buffer, or written from a buffer to a channel.

### Java NIO: Non-blocking IO
Java NIO enables you to do non-blocking IO. For instance, a thread can ask a channel to read data into a buffer.
 While the channel reads data into the buffer, the thread can do something else. Once data is read into the buffer, 
 the thread can then continue processing it. The same is true for writing data to channels.

### Java NIO: Selectors
Java NIO contains the concept of "selectors". A selector is an object that can monitor multiple channels for events 
(like: connection opened, data arrived etc.). Thus, a single thread can monitor multiple channels for data.


Java NIO consist of the following core components:

Channels
Buffers
Selectors

Java NIO has more classes and components than these, but the Channel, 
Buffer and Selector forms the core of the API, in my opinion. The rest of the components, 
like Pipe and FileLock are merely utility classes to be used in conjunction with the three core components. 
Therefore, I'll focus on these three components in this NIO overview. The other components are explained in their own texts
 elsewhere in this tutorial.
 See the menu at the top corner of this page.