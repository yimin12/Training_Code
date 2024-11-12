package src.template.algorithm.sorting.impl;

import src.template.algorithm.sorting.interfaces.Sort;

/**
 * Time Complexity Analysis:
 *
 * 1. Finding the Maximum Element:
 *    - This step runs in O(N), where N is the number of elements in the input array.
 *
 * 2. Counting Sort for Each Digit:
 *    - Counting sort runs in O(N + K) for each digit, where K is the range of possible values (10 for base 10).
 *    - The number of digits in the maximum number is D. For example, if the maximum number is 1234, D is 4 (as there are 4 digits).
 *    - The overall time complexity for counting sort on each digit is O(D * (N + K)).
 *
 * 3. Overall Time Complexity:
 *    - D is approximately log_10(max), where max is the largest number in the array.
 *    - Therefore, the overall time complexity is O(D * (N + K)).
 *    - Since K (the range of each digit, which is 10 for base 10) is constant, this simplifies to O(N * D).
 *    - For numbers with a reasonable number of digits, D can be treated as O(log_10(max)), leading to O(N * log_10(max)).
 *
 * Space Complexity Analysis:
 *
 * 1. Auxiliary Space:
 *    - The output array requires O(N) space to store the sorted result for each digit iteration.
 *    - The count array uses O(K) space, where K = 10 for base 10.
 *
 * 2. Overall Space Complexity:
 *    - The space complexity is O(N + K), but since K is constant, this simplifies to O(N).
 *
 * Summary:
 * - Time Complexity: O(N * D) where D ≈ log_10(max)
 * - Space Complexity: O(N)
 * - Best Suited For: Large arrays with a limited range of digit counts.
 */

public class RadixSort extends Sort {

    Comparable[] comparables;

    public RadixSort() {}

    public RadixSort(Comparable[] comparables) {
        this();
        this.comparables = comparables;
    }

    /**
     * Method to perform radix sort on an array of Comparable elements.
     */
    public static void radixSort(Comparable[] comparables) {
        if (comparables == null || comparables.length == 0) return;

        // Ensure all elements are of type Number to proceed with integer-based sorting
        for (Comparable comparable : comparables) {
            if (!(comparable instanceof Number)) {
                throw new IllegalArgumentException("Radix sort can only be applied to numeric data.");
            }
        }

        // Find the maximum number to determine the number of digits
        int max = ((Number) comparables[0]).intValue();
        for (Comparable comparable : comparables) {
            int value = ((Number) comparable).intValue();
            if (value > max) max = value;
        }

        // Perform counting sort for each digit
        int exp = 1; // Exponential factor (1, 10, 100, ...)
        while (max / exp > 0) {
            countingSortByDigit(comparables, exp);
            exp *= 10;
        }
    }

    /**
     * Helper method to perform counting sort on the array based on a specific digit.
     */
    private static void countingSortByDigit(Comparable[] comparables, int exp) {
        int n = comparables.length;
        Comparable[] output = new Comparable[n];
        int[] count = new int[10]; // Base 10 for decimal numbers

        // Count the occurrences of each digit
        for (Comparable comparable : comparables) {
            int digit = (((Number) comparable).intValue() / exp) % 10;
            count[digit]++;
        }

        // Update count array to hold the cumulative count
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            int digit = (((Number) comparables[i]).intValue() / exp) % 10;
            output[--count[digit]] = comparables[i];
        }

        // Copy the sorted elements back to the original array
        System.arraycopy(output, 0, comparables, 0, n);
    }

    @Override
    void sort(Comparable[] comparables) {
        if (comparables == null || comparables.length == 0) return;
        RadixSort.radixSort(comparables);
    }

    public static void main(String[] args) {
        Integer[] numbers = {387, 468, 134, 123, 68, 221, 769, 37, 7, 890, 1, 587};
        RadixSort sorter = new RadixSort(numbers);
        sorter.sort(numbers);
        // Prints:
        // [1, 7, 37, 68, 123, 134, 221, 387, 468, 587, 769, 890]
        System.out.println(java.util.Arrays.toString(numbers));
    }
}
