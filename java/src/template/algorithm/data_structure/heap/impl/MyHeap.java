package src.template.algorithm.data_structure.heap.impl;

import src.template.algorithm.data_structure.heap.interfaces.Heap;

import java.util.Arrays;

/**
 * Implement D_ary Heap and you can fine-tune the D with your experiment
 * Optimized with indexes array
 * Not workable, it contains error
 * @param <T>
 */
public class MyHeap <T extends Comparable<T>> implements Heap<T> {

    public static final int DEFAULT_CAPACITY = 16;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;
    public static final int DEFAULT_DEGREE = 2;

    private T[] heap;
    private int degree, n, size, capacity; // # of branches (# of children)
    private int[] indexes, reverses;
    private String mode;
    private final float loadFactor; // determine if need to rehash

    public MyHeap(String mode) {
        this(mode, DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR, DEFAULT_DEGREE);
    }

    public MyHeap(String mode, int capacity) {
        this(mode, capacity, DEFAULT_LOAD_FACTOR, DEFAULT_DEGREE);
    }

    public MyHeap(String mode, int capacity, float loadFactor) {
        this(mode, capacity, loadFactor, DEFAULT_DEGREE);
    }

    public MyHeap(String mode, int capacity, float loadFactor, int degree) {
        this.mode = mode;
        this.loadFactor = loadFactor;
        this.size = 0;
        this.capacity = capacity;
        this.degree = degree;
        heap = (T[]) new Comparable[capacity + 1]; // start from index 1
        indexes = new int[capacity + 1];
        reverses = new int[capacity + 1];
        for (int i = 0; i < capacity + 1; i++) {
            reverses[i] = 0;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void offer(T element) {
        offer(size + 1, element);
    }

    public void offer(int index, T element) {
        assert size + 1 <= capacity;
        assert index + 1 >= 1 && index + 1 <= capacity;
        assert !(contains(index));

        index += 1;
        heap[index] = element;
        indexes[size + 1] = index;
        reverses[index] = size + 1;
        size ++;
        if (needExpanding()) {
            expanding();
        }
        percolateUp(size);
    }

    public boolean contains(int index) {
        assert index + 1 >= 1 && index + 1 <= capacity;
        return reverses[index + 1] != 0;
    }


    @Override
    public T peek() {
        return getMax();
    }

    @Override
    public T poll() {
        assert size > 0;
        T element = heap[indexes[1]];
        swapIndexes(1, size);
        reverses[indexes[size]] = 0;
        this.size --;
        percolateDown(1);
        return element;
    }

    public int getMaxIndex() {
        assert size > 0;
        return indexes[1] - 1;
    }

    public T getMax() {
        assert size > 0;
        return heap[indexes[1]];
    }

    public void update(int index, T newData) {
        assert contains(index);
        index += 1;
        heap[index] = newData;
        percolateUp(reverses[index]);
        percolateDown(reverses[index]);
    }

    private void swapIndexes(int index1, int index2) {
        int temp = indexes[index1];
        indexes[index1] = indexes[index2];
        indexes[index2] = temp;
        reverses[index1] = index2;
        reverses[index2] = index1;
    }

    private boolean compare(T a, T b) {
        if (a.compareTo(b) < 0) {
            return this.mode.equals("min");
        } else {
            return this.mode.equals("max");
        }
    }

    private void percolateUp(int index) {
        while (index > 1 && compare(heap[indexes[index]], heap[indexes[(index + this.degree - 2) / this.degree]])) {
            swapIndexes(index, (index + this.degree - 2) / this.degree);
            index += (index + this.degree - 2) / this.degree;
        }
    }

    private void percolateDown(int index) {
        while (true) {
            int minChild = this.degree * (index - 1) + 2;
            int maxChild = Math.min(size, index * this.degree + 1);
            if (minChild > size) break;
            int selected = minChild;
            for (int i = minChild + 1; i <= maxChild; i++) {
                if (compare(heap[indexes[i]], heap[indexes[selected]])) {
                    selected = i;
                }
            }
            if (!compare(heap[indexes[selected]], heap[indexes[index]])) {
                break;
            }
            swapIndexes(index, selected);
            index = selected;
        }
    }

    private void expanding() {
        int newCapacity = capacity * 2;
        T[] newData = (T[]) new Comparable[newCapacity + 1];
        int[] newIndexes = new int[newCapacity + 1];
        int[] newReverse = new int[newCapacity + 1];
        System.arraycopy(heap, 0, newData, 0, capacity + 1);
        System.arraycopy(indexes, 0, newIndexes, 0, capacity + 1);
        System.arraycopy(reverses, 0, newReverse, 0, capacity + 1);
        heap = newData;
        indexes = newIndexes;
        reverses = newReverse;
        capacity = newCapacity;
    }

    private boolean needExpanding() {
        return (size + 0.0f) / heap.length >= loadFactor;
    }

    @Override
    public void clear() {
        Arrays.fill(heap, null);
        size = 0;
        Arrays.fill(reverses, 0);
        Arrays.fill(indexes, 0);
    }


}
