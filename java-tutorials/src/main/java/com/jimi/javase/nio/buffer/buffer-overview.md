### Basic Buffer Usage
Using a Buffer to read and write data typically follows this little 4-step process:

Write data into the Buffer
Call buffer.flip()
Read data out of the Buffer
Call buffer.clear() or buffer.compact()
When you write data into a buffer, the buffer keeps track of how much data you have written. 
Once you need to read the data, you need to switch the buffer from writing mode into reading mode 
using the flip() method call. In reading mode the buffer lets you read all the data written into 
the buffer.

Once you have read all the data, you need to clear the buffer, to make it ready for writing again. 
You can do this in two ways: By calling clear() or by calling compact(). The clear() method clears 
the whole buffer. The compact() method only clears the data which you have already read.
 Any unread data is moved to the beginning of the buffer, 
and data will now be written into the buffer after the unread data.

### Buffer Capacity, Position and Limit
A buffer is essentially a block of memory into which you can write data, which you can then later read again. 
This memory block is wrapped in a NIO Buffer object, which provides a set of methods that makes it easier to work with the memory block.

A Buffer has three properties you need to be familiar with, in order to understand how a Buffer works. These are:

* capacity
* position
* limit

The meaning of position and limit depends on whether the Buffer is in read or write mode. Capacity always means the same, no matter the buffer mode.

Capacity
Being a memory block, a Buffer has a certain fixed size, also called its "capacity". You can only write capacity bytes, longs, 
chars etc. into the Buffer. Once the Buffer is full, you need to empty it (read the data, or clear it) before you can write more data into it.

Position
When you write data into the Buffer, you do so at a certain position. Initially the position is 0. When a byte, long etc. 
has been written into the Buffer the position is advanced to point to the next cell in the buffer to insert data into. 
Position can maximally become capacity - 1.

When you read data from a Buffer you also do so from a given position. When you flip a Buffer from writing mode to reading mode,
 the position is reset back to 0. As you read data from the Buffer you do so from position, and position is advanced to next position to read.

Limit
In write mode the limit of a Buffer is the limit of how much data you can write into the buffer. 
In write mode the limit is equal to the capacity of the Buffer.

When flipping the Buffer into read mode, limit means the limit of how much data you can read from the data. 
Therefore, when flipping a Buffer into read mode, limit is set to write position of the write mode. I
n other words, you can read as many bytes as were written (limit is set to the number of bytes written, which is marked by position).

**Buffer Types**

Java NIO comes with the following Buffer types:

* ByteBuffer
* MappedByteBuffer
* CharBuffer
* DoubleBuffer
* FloatBuffer
* IntBuffer
* LongBuffer
* ShortBuffer

flip()
The flip() method switches a Buffer from writing mode to reading mode. Calling flip() sets the position back to 0, and sets the limit to where position just was.