package src.template.algorithm.sort;

/**
 *  In-place sort -> not require extra space
 *  Stable while sorting -> comparables[i].compareTo(comparables[i - 1]) < 0
 *  Time: O(n^2) Time: O(1)
 *  是可以不需要额外空间且稳定进行排序的排序算法
 */
public class BubbleSort extends Sort {

    Comparable[] comparables;

    public BubbleSort() {}

    public BubbleSort(Comparable[] comparables) {
        this();
        this.comparables = comparables;
    }

    public static void sort(Comparable[] comparables, int left, int right) {
        if (comparables == null || comparables.length <= 0 || left >= right) return;
        boolean sorted;
        do {
            sorted = true;
            for (int i = left + 1; i <= right; i ++) {
                if (comparables[i].compareTo(comparables[i - 1]) < 0) {
                    Sort.swap(comparables, i, i - 1);
                    sorted = false;
                }
            }
        } while (!sorted);
    }

    @Override
    public void sort(Comparable[] comparables) {
        BubbleSort.sort(comparables, 0, comparables.length - 1);
    }

}
