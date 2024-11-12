package src.template.algorithm.data_structure.stack_queue.stack.impl;

import src.template.algorithm.data_structure.stack_queue.stack.interfaces.MyStack;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 *  Description:
 *  	Design a max stack that supports push, pop, top, peekMax and popMax.
 * 		push(x) -- Push element x onto stack.
 * 		pop() -- Remove the element on top of the stack and return it.
 * 		top() -- Get the element on the top.
 * 		peekMax() -- Retrieve the maximum element in the stack.
 * 		popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
 *  In this implementation, try to use tree map rather than maintaining multiple stacks
 */

public class MaxStack<T extends Comparable<T>> implements MyStack<T> {

    TreeMap<T, List<Node<T>>> map;
    DoubleLinkedList<T> dll;
    private int size;

    public MaxStack() {
        this.map = new TreeMap<>();
        this.dll = new DoubleLinkedList<>();
        this.size = 0;
    }

    @Override
    public void push(T value) {
        this.size ++;
        Node<T> node = dll.add(value);
        if (!map.containsKey(value)) {
            map.put(value, new ArrayList<>());
        }
        map.get(value).add(node); // add to the linked list if duplicate
    }

    @Override
    public T peek() {
        return dll.peek();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public T pop() {
        this.size --;
        T value = dll.pop();
        List<Node<T>> list = map.get(value);
        list. removeLast();
        if (list.isEmpty()) {
            map.remove(value);
        }
        return value;
    }

    public T peekMax() {
        return map.lastKey();
    }

    public T popMax() {
        this.size --;
        T max = peekMax();
        List<Node<T>> list = map.get(max);
        Node<T> node = list.removeLast();
        dll.unlink(node);
        if (list.isEmpty()) map.remove(max);
        return max;
    }

    static class MaxStackException extends StackException {

        public MaxStackException(String message) {
            super(message);
        }

    }

    static class Node<T extends Comparable<T>> {

        private final T data;
        Node<T> prev, next;

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }
    }

    static class DoubleLinkedList<T extends Comparable<T>> {

        Node<T> head, tail;

        public DoubleLinkedList() {
            head = new Node<T>(null);
            tail = new Node<T>(null);
            head.next = tail;
            tail.prev = head;
        }

        /**
         * Appending at the end
         * @param data
         * @return
         */
        public Node<T> add(T data) {
            Node<T> cur = new Node<T>(data);
            cur.next = tail;
            cur.prev = tail.prev;
            tail.prev.next = cur;
            tail.prev = cur;
            return cur;
        }

        public T pop() {
            return unlink(tail.prev).getData();
        }

        public T peek() {
            return tail.prev.getData();
        }

        public Node<T> unlink(Node<T> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            return node;
        }
    }
}
