package src.template.algorithm.data_structure.stack_queue.stack.impl;

import src.template.algorithm.data_structure.stack_queue.stack.interfaces.MyStack;

/**
 * Use linked list to implement a stack, LIFO
 */

public class LinkedListStack<T> implements MyStack<T> {

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

    @Override
    public T peek() throws LinkedListStackException {
        if (head == null) throw new LinkedListStackException("Stack is empty");
        return head.getData();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void push(T data) {
        if (head == null) {
            head = new Node<>(data);
        }
        Node<T> last = new Node<>(data);
        last.next = head;
        head = last;
        this.size ++;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
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
