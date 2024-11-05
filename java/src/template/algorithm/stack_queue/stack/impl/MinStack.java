package src.template.algorithm.stack_queue.stack.impl;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Description:
 *  Enhance the stack implementation to support min() operation, min() should return the
 *  current minimum value in the stack. If the stack is Empty, min() return -1;
 *  In this design, we will avoid using treeMap but keep multiple stacks to maintain the minimal, we can try
 *  implementing max_stack with treeMap
 *
 * @param <T>
 */
public class MinStack <T extends Comparable<T>>{

    static class MinStackException extends StackException {

        public MinStackException(String message) {
            super(message);
        }

    }

    private final Deque<T> stack;
    private final Deque<Integer> freqStack;
    private final Deque<T> minStack;

    public MinStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
        freqStack = new LinkedList<>(); // use for deduplication
    }

    /**
     * Time: O(1), Space: O(1)
     * @return
     * @throws MinStackException
     */
    public T min() throws MinStackException {
        if (minStack.isEmpty()) throw new MinStackException("Stack is empty");
        return minStack.peekFirst();
    }

    /**
     * Time: O(1), Space: Amortized O(1)
     * @param data
     */
    public void push(T data) {
        stack.offerFirst(data);
        if (minStack.isEmpty() || data.compareTo(minStack.peekFirst()) < 0) {
            minStack.offerFirst(data);
            freqStack.offerFirst(1);
        } else if (freqStack.peekFirst() != null && data.compareTo(minStack.peekFirst()) == 0) {
            freqStack.offerFirst(freqStack.pollFirst() + 1);
        }
    }

    /**
     * Time: O(1) Space: O(1)
     * @return
     * @throws MinStackException
     */
    public T pop() throws MinStackException {
        if (stack.isEmpty()) throw new MinStackException("Stack is empty");
        T res = stack.pollFirst();
        if (minStack.peekFirst().equals(res) && !freqStack.isEmpty()) {
            if (freqStack.peekFirst() == 1) {
                minStack.pollFirst(); // what happen if we have duplicate?
                freqStack.pollFirst();
            } else {
                freqStack.offerFirst(freqStack.pollFirst() - 1);
            }
        }
        return res;
    }

    /**
     * Time: O(1)
     * @return
     * @throws MinStackException
     */
    public T top() throws MinStackException {
        if (stack.isEmpty()) throw new MinStackException("Stack is empty");
        return stack.peekFirst();
    }
}
