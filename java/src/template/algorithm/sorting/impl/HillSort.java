package src.template.algorithm.sorting.impl;

import src.template.algorithm.sorting.interfaces.Sort;

public class HillSort extends Sort {

    Comparable[] comparables;

    public HillSort() {}

    public HillSort(Comparable[] comparables) {
        this();
        this.comparables = comparables;
    }

    /**
     * Method to perform Shell sort (Hill sort) on an array of Comparable elements.
     */
    public static void hillSort(Comparable[] comparables) {
        if (comparables == null || comparables.length == 0) return;

        int n = comparables.length;

        // Start with a large gap, then reduce the gap
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Perform a gapped insertion sort for this gap size.
            for (int i = gap; i < n; i++) {
                Comparable temp = comparables[i];
                int j;
                for (j = i; j >= gap && comparables[j - gap].compareTo(temp) > 0; j -= gap) {
                    comparables[j] = comparables[j - gap];
                }
                comparables[j] = temp;
            }
        }
    }

    @Override
    void sort(Comparable[] comparables) {
        if (comparables == null || comparables.length == 0) return;
        HillSort.hillSort(comparables);
    }

    public static void main(String[] args) {
        Integer[] numbers = {387, 468, 134, 123, 68, 221, 769, 37, 7, 890, 1, 587};
        HillSort sorter = new HillSort(numbers);
        sorter.sort(numbers);
        // Prints:
        // [1, 7, 37, 68, 123, 134, 221, 387, 468, 587, 769, 890]
        System.out.println(java.util.Arrays.toString(numbers));
    }
}

