package com.jimi.javase.lang;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <方法说明>
 * <p>
 * <p>
 * the class below generates unique identifiers local to each
 * thread.
 * A thread's id is assigned the first time it invokes {@code ThreadId.get()}
 * and remains unchanged on subsequent calls.
 *
 * @author xianyao.ye@ucarinc.com
 * @version 1.0
 * @date 2020/3/17 13:39
 */
public class ThreadId {

    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>() {
        /**
         * Returns the current thread's "initial value" for this
         * thread-local variable.  This method will be invoked the first
         * time a thread accesses the variable with the {@link #get}
         * method, unless the thread previously invoked the {@link #set}
         * method, in which case the {@code initialValue} method will not
         * be invoked for the thread.  Normally, this method is invoked at
         * most once per thread, but it may be invoked again in case of
         * subsequent invocations of {@link #remove} followed by {@link #get}.
         *
         * <p>This implementation simply returns {@code null}; if the
         * programmer desires thread-local variables to have an initial
         * value other than {@code null}, {@code ThreadLocal} must be
         * subclassed, and this method overridden.  Typically, an
         * anonymous inner class will be used.
         *
         * @return the initial value for this thread-local
         */
        @Override
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };

    //returns the current thread's unique id ,assign it if nessary;
    public static int get() {
        return threadId.get();
    }


}
