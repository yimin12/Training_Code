package src.template.algorithm.stack_queue.queue.impl;

import java.util.NoSuchElementException;

/**
 * Design your implementation of a circular queue. The circular queue is a linear data structure
 * in which operations are performed based on the FIFO (First In First Out) principle,
 * and the last position is connected back to the first position to form a circle.
 * It is also called a "Ring Buffer".
 *
 * One of the benefits of the circular queue is that it utilizes the spaces at the front of the queue.
 * In a normal queue, once the queue becomes full, we cannot insert the next element
 * even if there is space in front of the queue. Using a circular queue, we can reuse this space.
 *
 * Your implementation should support the following operations:
 *
 * - `CircularQueue(k)`: Constructor to set the size of the queue to be `k`.
 * - `Front()`: Get the front item from the queue. If the queue is empty, throw exception.
 * - `Rear()`: Get the last item from the queue. If the queue is empty, throw exception.
 * - `enQueue(value)`: Insert an element into the circular queue. Return `true` if the operation is successful.
 * - `deQueue()`: Delete an element from the circular queue. Return `true` if the operation is successful.
 * - `isEmpty()`: Check whether the circular queue is empty.
 * - `isFull()`: Check whether the circular queue is full.
 */


public class CircularQueue<T> {

    private T[] data;
    private int head, tail, size, capacity;

    public CircularQueue(int capacity) {
        this.data = (T[])new Object[capacity];
        this.head = this.tail = 1;
        this.capacity = capacity;
        this.size = 0;
    }

    public boolean isFull() {
        // return size == capacity
        return ((tail + 1) % capacity) == head;
    }

    public boolean isEmpty() {
        return head == -1;
    }

    public boolean enQueue(T value) {
        if(isFull()) return false;
        if(isEmpty()) head = 0;
        tail = (tail + 1) % capacity;
        data[tail] = value;
        size++;
        return true;
    }

    public boolean deQueue() {
        if(isEmpty()) return false;
        if (head == tail) {
           // the queue is empty, the head should be reset to -1
           head = tail = -1;
           return true;
        }
        // pop from head
        head = (head + 1) % capacity;
        size--;
        return true;
    }

    public T Front() throws QueueException {
        if(isEmpty()) throw new QueueException("Queue is empty");
        return data[head];
    }

    public T Rear() throws QueueException {
        if (isEmpty()) throw new QueueException("Queue is empty");
        return data[tail];
    }
}
