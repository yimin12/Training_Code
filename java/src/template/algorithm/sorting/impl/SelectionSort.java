package src.template.algorithm.sorting.impl;

import src.template.algorithm.sorting.interfaces.Sort;

public class SelectionSort extends Sort {

    Comparable[] comparables;

    public SelectionSort() {
    }

    public SelectionSort(Comparable[] comparables) {
        this();
        this.comparables = comparables;
    }

    public static void sort(Comparable[] comparables, int left, int right) {
        if (comparables == null || comparables.length == 0 || left >= right) return;
        for (int i = left; i < right; i ++) {
            int swapIndex = i;
            for (int j = i + 1; j <= right; j ++) {
                if (comparables[j].compareTo(comparables[swapIndex]) < 0) {
                    swapIndex = j;
                }
            }
            swap(comparables, i, swapIndex);
        }
    }

    @Override
    void sort(Comparable[] comparables) {
        SelectionSort.sort(comparables, 0, comparables.length - 1);
    }
}
