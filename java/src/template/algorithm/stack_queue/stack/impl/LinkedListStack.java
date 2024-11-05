package src.template.algorithm.stack_queue.stack.impl;

import java.util.EmptyStackException;

/**
 * Use linked list to implement a stack, LIFO
 */

public class LinkedListStack<T> {

    static class LinkedListStackException extends StackException {

        public LinkedListStackException() {
            super("LinkedListStack is empty");
        }

        public LinkedListStackException(String message) {
            super(message);
        }
    }

    static class Node<T> {
        private final T data;
        Node<T> next;

        private Node (T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }

    }

    private Node<T> head;
    private int size;

    public LinkedListStack() {
        head = null;
        this.size = 0;
    }

    public T pop () throws LinkedListStackException{
        if (head == null) {
            throw new LinkedListStackException("Stack is empty");
        }
        Node<T> res = head;
        head = head.next;
        res.next = null;
        this.size --;
        return res.getData();
    }

    public T peek() throws LinkedListStackException {
        if (head == null) throw new LinkedListStackException("Stack is empty");
        return head.getData();
    }

    public void push(T data) {
        if (head == null) {
            head = new Node<>(data);
        }
        Node<T> last = new Node<>(data);
        last.next = head;
        head = last;
        this.size ++;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return this.size;
    }

    public void print() {
        Node<T> cur = head;
        while (cur != null) {
            System.out.print(cur.getData() + " -> ");
            cur = cur.next;
        }
        System.out.println("null");
    }
}
