package src.template.algorithm.data_structure.stack_queue.queue.impl;

import src.template.algorithm.data_structure.stack_queue.queue.interfaces.MyQueue;

/**
 * Use linked list to implement Queue
 */

public class Queue<T> implements MyQueue<T> {

    Node<T> head, tail;
    private int size;

    public Queue() {
        head = tail = null;
        this.size = 0;
    }

    public T poll() throws QueueException{
        if (head == null) {
            throw new QueueException("Queue is empty");
        }
        this.size --;
        Node<T> cur = head;
        head = head.next;
        if (head == null) tail = null;
        cur.next = null;
        return cur.getValue();
    }

    public T peek() throws QueueException{
        if (head == null) {
            throw new QueueException("Queue is empty");
        }
        return head.getValue();
    }

    public void offer(T value) throws QueueException{
        this.size ++;
        if (head == null) {
            head = new Node<>(value);
            tail = head;
        } else {
            tail.next = new Node<>(value);
            tail = tail.next;
        }
    }

    public boolean isEmpty(){
        return head == null;
    }

    @Override
    public int size() {
        return this.size;
    }

    static class Node<T> {

        private final T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }

}
