package src.template.algorithm.stack_queue.queue.impl;

import java.util.Stack;

/**
 * Description:
 * Implement a queue using only two stacks. The queue should support the following operations:
 *
 * - `push(element)`: Push an element to the back of the queue.
 * - `pop()`: Remove and return the front (first) element in the queue.
 * - `top()`: Return the front (first) element in the queue without removing it.
 *
 * Both `pop` and `top` methods should return the value of the first element.
 *
 * @param <T> The type of elements held in this queue.
 */

public class StackQueue <T> {

    // Shuffle Stack to maintain Queue -> LIFO to FIFO
    private Stack<T> in, out;

    public StackQueue() {
        in = new Stack<>();
        out = new Stack<>();
    }

    public T poll() {
        shuffle();
        return out.isEmpty() ? null : out.pop();
    }

    public void offer(T value) {
        in.push(value);
    }

    private void shuffle() {
        if(out.isEmpty()) {
            while(!in.isEmpty()) {
                out.push(in.pop());
            }
        }
    }

    public int size() {
        return in.size() + out.size();
    }

    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }

}
