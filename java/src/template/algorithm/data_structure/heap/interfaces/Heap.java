package src.template.algorithm.data_structure.heap.interfaces;

public interface Heap<T extends Comparable<T>> {

    public int size();
    public boolean isEmpty();
    public void offer(T element);
    public T peek();
    public T poll();
    public void clear();

}
