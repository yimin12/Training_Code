package src.template.algorithm.sorting.impl;

import src.template.algorithm.sorting.interfaces.Sort;

/**
 * Time Complexity Analysis:
 *
 * 1. Finding min and max: This step takes O(N) time, where N is the number of elements in the input array.
 * 2. Bucket Initialization: Initializing the buckets takes O(N), as it creates N empty buckets.
 * 3. Distributing Elements into Buckets: This step is O(N) because each element is placed into a bucket in constant time.
 * 4. Sorting Individual Buckets: If the input is uniformly distributed and each bucket has about k elements (k = N/M where M is the number of buckets),
 *    sorting each bucket using Collections.sort() (which uses Timsort with O(k log k)) results in O(∑(i=1 to M) k_i log k_i) ≈ O(N log k).
 *    In the best-case scenario, k is small, leading to O(N) for bucket sorting.
 * 5. Merging Buckets: Merging the sorted buckets back into the original array is O(N).
 *
 * Overall Time Complexity:
 *
 * - Best Case: O(N + M + N) ≈ O(N) when distribution is even and each bucket has O(1) elements.
 * - Average Case: O(N + N log k) where k is the average number of elements per bucket.
 * - Worst Case: O(N^2) if all elements fall into a single bucket, making it behave like O(N log N) sorting for one bucket.
 *
 * Space Complexity Analysis:
 *
 * 1. Buckets Storage: O(N) space is used to store elements in buckets.
 * 2. Auxiliary Space for Sorting: Each bucket's sorting may require extra space, but Collections.sort() uses O(1) for in-place sorting.
 * 3. Overall Space Complexity: O(N + M) where M is the number of buckets. Since M is typically O(N), the space complexity can be considered O(N).
 *
 * Summary:
 * - Time Complexity: O(N + N log k)
 * - Space Complexity: O(N)
 */

public class CountingSort extends Sort {

    Comparable[] comparables;

    public CountingSort() {}

    CountingSort(Comparable[] comparables) {
        this();
        this.comparables = comparables;
    }

    public static void sort(Comparable[] comparables, Comparable min, Comparable max) {
        if (comparables == null || comparables.length == 0 || min.compareTo(max) == 0) return;
        int minValue = ((Number) min).intValue();
        int maxValue = ((Number) max).intValue();
        int range = maxValue - minValue + 1;

        int[] count = new int[range];
        Comparable[] output = new Comparable[comparables.length];
        for (Comparable comparable : comparables) {
            int value = ((Number) comparable).intValue();
            count[value - minValue]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1]; // use as index in the following traverse
        }
        // Build the output array by placing elements in the correct position
        for (int i = comparables.length - 1; i >= 0; i--) {
            int value = ((Number) comparables[i]).intValue();
            output[--count[value - minValue]] = comparables[i];
        }

        // Copy the sorted elements back to the original array
        System.arraycopy(output, 0, comparables, 0, comparables.length);
    }

    @Override
    void sort(Comparable[] comparables) {
        if (comparables == null || comparables.length == 0) return;
        Comparable min = comparables[0];
        Comparable max = comparables[0];
        for (Comparable comparable : comparables) {
            if (comparable.compareTo(min) < 0) min = comparable;
            if (comparable.compareTo(max) > 0) max = comparable;
        }
        CountingSort.sort(comparables, min, max);
    }

    public static void main(String[] args) {

        Integer[] array = {10, 4, 6, 8, 13, 2, 3};
        Sort sorter = new CountingSort(array);
        sorter.sort(array);
        // Prints:
        // [2, 3, 4, 6, 8, 10, 13]
        System.out.println(java.util.Arrays.toString(array));

        array = new Integer[] {10, 10, 10, 10, 10};
        sorter.sort(array);
        // Prints:
        // [10, 10, 10, 10, 10]
        System.out.println(java.util.Arrays.toString(array));
    }
}
