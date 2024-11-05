package src.template.algorithm.stack_queue.stack.impl;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Description:
 * Implement a stack using queues with the following operations:
 *
 * - `push(x)`: Push element `x` onto the stack.
 * - `pop()`: Remove the element on top of the stack.
 * - `top()`: Get the top element of the stack.
 * - `empty()`: Return `true` if the stack is empty, `false` otherwise.
 *
 * @param <T> The type of elements held in this stack.
 */

public class QueueStack<T> {

    private Queue<T> queue;
    public QueueStack(Queue<T> queue) {
        queue = new LinkedList<>();
    }

    public void push(T data) {
        queue.offer(data);
        // shuffle
        for (int i = 1; i < queue.size(); i++) {
            queue.add(queue.poll()); // every offer will cost n^2 time
        }
    }

    public void pushMultiple(T... x) {
        for (T data : x) {
            push(data);
        }
    }

    public T pop() {
        return queue.poll();
    }

    public T top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }

}
