package src.template.algorithm.data_structure.heap.interfaces;

public interface MyHeap <T extends Comparable<T>> {

    public int size();
    public boolean isEmpty();
    public void offer(T element);
    public T peek();
    public T poll();

}
