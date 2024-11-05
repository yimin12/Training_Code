package src.template.algorithm.stack_queue.queue.impl;

/**
 * Use linked list to implement Queue
 */

public class Queue<T> {

    Node<T> head, tail;

    public Queue() {
        head = tail = null;
    }

    public T poll() throws QueueException{
        if (head == null) {
            throw new QueueException("Queue is empty");
        }
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
