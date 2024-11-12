package src.template.algorithm.sorting.impl;

import src.template.algorithm.sorting.interfaces.Sort;

/**
 * This implementation is in-place sorting and it is not stable because we swap all elements
 * Explain:
 *     Step 1: Heapify the array: This step constructs a max-heap where the largest element
 *          is at the first position of the array.
 *     Step 2: Sort by extracting the root:
 *          The root of the max-heap (the largest element) is swapped with the last element in the
 *              current subarray (swap(comparables, left, i);).
 *          The array size is reduced by one (i--), effectively removing the largest element from the heap.
 *          The percolateDown method is called on the reduced heap to reheapify it, ensuring that the next
 *              largest element moves to the root.
 *  Time: O(nlogn + n), Space: O(1) -> all in-place sorting is O(1) for space
 *
 */
public class HeapSort extends Sort {

    Comparable[] comparables;

    public HeapSort() {}

    public HeapSort(Comparable[] comparables) {
        this();
        this.comparables = comparables;
    }

    public static void sort(Comparable[] comparables, int left, int right) {
        if (comparables == null || comparables.length <= 0 || left >= right) return;
        // heapify O(n)
        for (int i = Math.max(left, ((right - left) >> 1)); i >= left; i--) {
            percolateDown(comparables, (right - left), i);
        }

        System.out.println(java.util.Arrays.toString(comparables));
        // Sort with heapified array O(nlogn)
        for (int i = right; i > left; i --) {
            swap(comparables, left, i);
            percolateDown(comparables, i, left);
        }
    }

    private static void percolateDown(Comparable[] comparables, int size, int index) {
        while (true) {
            int left_son = (index << 1) + 1;
            int right_son = (index << 1) + 2;
            int son = index;
            if (right_son < size && comparables[right_son].compareTo(comparables[son]) > 0) son = right_son;
            if (left_son < size && comparables[left_son].compareTo(comparables[son]) > 0) son = left_son;
            if (son != index) {
                swap(comparables, index, son);
                index = son;
            } else break;
        }
    }

    @Override
    void sort(Comparable[] comparables) {
        HeapSort.sort(comparables, 0, comparables.length - 1);
    }
}
