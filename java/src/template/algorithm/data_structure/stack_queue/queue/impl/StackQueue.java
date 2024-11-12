package src.template.algorithm.data_structure.stack_queue.queue.impl;

import src.template.algorithm.data_structure.stack_queue.queue.interfaces.MyQueue;

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

public class StackQueue <T> implements MyQueue<T> {

    // Shuffle Stack to maintain Queue -> LIFO to FIFO
    private Stack<T> in, out;

    public StackQueue() {
        in = new Stack<>();
        out = new Stack<>();
    }

    @Override
    public T peek() {
        shuffle();
        return out.peek();
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
