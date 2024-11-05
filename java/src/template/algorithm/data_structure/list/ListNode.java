package src.template.algorithm.data_structure.list;

/**
 * Common Used Data structure
 * @param <T>
 */
public class ListNode<T extends  Comparable<T>> {

    public T value;
    public ListNode<T> next;
    public ListNode(T value) {
        this.value = value;
    }
}
