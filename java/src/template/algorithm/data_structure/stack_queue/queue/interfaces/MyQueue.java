package src.template.algorithm.data_structure.stack_queue.queue.interfaces;

public interface MyQueue <T> {

    public T peek();
    public T poll();
    public boolean isEmpty();
    public void offer(T element);
    public int size();

}
