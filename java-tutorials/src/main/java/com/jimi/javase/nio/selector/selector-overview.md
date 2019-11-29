The Java NIO Selector is a component which can examine one or more Java NIO Channel instances,
 and determine which channels are ready for e.g. reading or writing. This way a single thread 
 can manage multiple channels, and thus multiple network connections.
 
 ### Why Use a Selector?
 The advantage of using just a single thread to handle multiple channels is that you need less 
 threads to handle the channels. Actually, you can use just one thread to handle all of your 
 channels. Switching between threads is expensive for an operating system, and each thread takes 
 up some resources (memory) in the operating system too. Therefore, the less threads you use, 
 the better.
 
 Keep in mind though, that modern operating systems and CPU's become better and better at 
 multitasking, so the overheads of multithreading becomes smaller over time. In fact, if a 
 CPU has multiple cores, you might be wasting CPU power by not multitasking. Anyways, that 
 design discussion belongs in a different text. It suffices to say here, 
 that you can handle multiple channels with a single thread, using a Selector.