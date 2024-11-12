package src.template.algorithm.sorting.impl;

import src.template.algorithm.sorting.interfaces.Sort;

/**
 * Insertion sort and sort the container in ascending order
 * Time: worst case O(n^2) and best case O(n) Space: O(1)
 * 是可以不需要额外空间进行排序的排序算法，插入排序对于接近有序的数组，时间复杂度会降至 O(n)的时间复杂度
 */
public class InsertionSort extends Sort {

    private Comparable[] comparables;

    public InsertionSort() {
    }

    public InsertionSort(Comparable[] comparables) {
        this();
        this.comparables = comparables;
    }

    @Override
    public void sort(Comparable[] comparables) {
        InsertionSort.sort(comparables, 0, comparables.length - 1);
    }

    public static void sort(Comparable[] comparables, int left, int right) {
        if (comparables == null || comparables.length == 0) return;
        for (int i = left + 1; i <= right; i++) {
            Comparable element = comparables[i];
            int j = i;
            while (j > left && comparables[j - 1].compareTo(element) > 0) {
                comparables[j] = comparables[j - 1];
                j --;
            }
            comparables[j] = element;
        }
    }
}
