package edu.cmu.cs.cs214.rec12.queue;

import java.util.ArrayDeque;
import java.util.Deque;

import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;

/**
 * Modify this class to be thread-safe and be an UnboundedBlockingQueue.
 */
@ThreadSafe
public class UnboundedBlockingQueue<E> implements SimpleQueue<E>  {
    @GuardedBy("this")
    private Deque<E> queue = new ArrayDeque<>();

    public UnboundedBlockingQueue() { }

    public synchronized boolean isEmpty() { return queue.isEmpty(); }

    public synchronized int size() { return queue.size(); }

    public synchronized E peek() { return queue.peek(); }

    public synchronized void enqueue(E element) {
        queue.add(element);
        notifyAll(); // notifyAll() for multiple producer/consumer threads
    }

    /**
     * TODO:  Change this method to block (waiting for an enqueue) rather
     * than throw an exception.  Completing this task may require
     * modifying other methods.
     */
    public synchronized E dequeue () throws InterruptedException{
        while(queue.isEmpty()) {
            wait();
        }
        E item = queue.remove();
        return item;
    }

    public String toString() { return queue.toString(); }
}
