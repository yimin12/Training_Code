package src.template.algorithm.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *  Bucket represent a small range of total range, choose suitable bucket for current value.
 *  If each values distribute uniformly, it will consume linear time
 *    Time Complexity Analysis:
 *    Finding min and max: This step takes O(N) time, where N is the number of elements in the input array.
 *    Bucket Initialization: Initializing the buckets takes O(N), as it creates N empty buckets.
 *    Distributing Elements into Buckets: This step is O(N) because each element is placed into a bucket in constant time.
 *    Sorting Individual Buckets: If the input is uniformly distributed and each bucket has about k elements (k = N/M where M is the number of buckets),
 *    sorting each bucket using Collections.sort() (which uses Timsort with O(k log k)) results in O(∑(i=1 to M) k_i log k_i) ≈ O(N log k).
 *    In the best-case scenario, k is small, leading to O(N) for bucket sorting.
 *    Merging Buckets: Merging the sorted buckets back into the original array is O(N).
 *    Overall Time Complexity:
 *    Best Case: O(N + M + N) ≈ O(N) when distribution is even and each bucket has O(1) elements.
 *    Average Case: O(N + N log k) where k is the average number of elements per bucket.
 *    Worst Case: O(N^2) if all elements fall into a single bucket, making it behave like O(N log N) sorting for one bucket.
 *    Space Complexity Analysis:
 *    Buckets Storage: O(N) space is used to store elements in buckets.
 *    Auxiliary Space for Sorting: Each bucket's sorting may require extra space, but Collections.sort() uses O(1) for in-place sorting.
 *    Overall Space Complexity: O(N + M) where M is the number of buckets. Since M is typically O(N), the space complexity can be considered O(N).
 *    Summary:
 *    Time Complexity: O(N + N log k)
 *    Space Complexity: O(N)
 */
public class BucketSort extends Sort{

    Comparable[] comparables;

    public BucketSort() {}

    public BucketSort(Comparable[] comparables) {
        this();
        this.comparables = comparables;
    }

    private static void sort(Comparable[] comparables, Comparable min, Comparable max) {
        if (comparables == null || comparables.length == 0) return;
        int N = comparables.length, numBuckets = N;
        List<List<Comparable>> buckets = new ArrayList<>(numBuckets);

        for (int i = 0; i < numBuckets; i++) buckets.add(new ArrayList<>());
        for (Comparable comparable : comparables) {
            // 使用 compareTo的方法进行散列
            int bucketIndex = (int) (((double) comparable.compareTo(min) / (max.compareTo(min) + 1)) * numBuckets);
            bucketIndex = Math.min(bucketIndex, numBuckets - 1); // Ensure it fits within range
            buckets.get(bucketIndex).add(comparable);
        }

        int index = 0;
        for (List<Comparable> bucket : buckets) {
            if (!bucket.isEmpty()) {
                Collections.sort(bucket);
                for (Comparable item : bucket) {
                    comparables[index ++] = item;
                }
            }
        }
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
        BucketSort.sort(comparables, min, max);
    }
}
