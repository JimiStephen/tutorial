Lesson: Implementations
Implementations are the data objects used to store collections, which implement the interfaces described in the Interfaces section. This lesson describes the following kinds of implementations:

General-purpose implementations are the most commonly used implementations, designed for everyday use. They are summarized in the table titled General-purpose-implementations.
Special-purpose implementations are designed for use in special situations and display nonstandard performance characteristics, usage restrictions, or behavior.
Concurrent implementations are designed to support high concurrency, typically at the expense of single-threaded performance. These implementations are part of the java.util.concurrent package.
Wrapper implementations are used in combination with other types of implementations, often the general-purpose ones, to provide added or restricted functionality.
Convenience implementations are mini-implementations, typically made available via static factory methods, that provide convenient, efficient alternatives to general-purpose implementations for special collections (for example, singleton sets).
Abstract implementations are skeletal implementations that facilitate the construction of custom implementations — described later in the Custom Collection Implementations section. An advanced topic, it's not particularly difficult, but relatively few people will need to do it.
The general-purpose implementations are summarized in the following table.

General-purpose Implementationst
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Interfaces  -|-Hash table Implementations	-|-Resizable array Implementations-|-Tree Implementations	-|-Linked list Implementations -|-Hash table + Linked list Implementations-|
Set	        -|-HashSet	 	                -|-                               -|-TreeSet	 	        -|-                            -|- LinkedHashSet                          -|
List	    -|-                             -|-ArrayList	 	              -|-                       -|-    LinkedList              -|-                                        -|
Queue	    -|-                             -|-         	 	              -|-                       -|-                            -|-                                        -|
Deque	    -|-                             -|-ArrayDeque	 	              -|-                       -|-    LinkedList              -|-                                        -|
Map	    	-|-HashMap                      -|-ArrayList	 	              -|-TreeMap                -|-    LinkedList              -|-LinkedHashMap                           -|
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------