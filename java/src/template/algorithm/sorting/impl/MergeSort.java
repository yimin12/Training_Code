package src.template.algorithm.sorting.impl;

import src.template.algorithm.sorting.interfaces.Sort;

/**
 * Need extra space as shuffle buffer and if it is stable depends on your implementation. In this version, it should be
 * Stable
 * Time: O(n*logn) Space: O(n + logn) where n is extra aux array, and logn is the depth of call stack
 *
 * Insight:
 *  已知在近乎有序的数组中，插入排序的时间复杂度接近与 O(n)，而归并排序稳定在 O(nlogn) 所以我们可以在一般归并排序的基础之上实施优化
 *  1。对于已知有序数组，无需继续 进入递归
 *  2. 数据量小，或者接近有序时时使用 插入排序
 *
 * Implementation:
 *  1. Top down -> Recursively merge
 *  2. Bottom up -> Iterative merge (Avoiding stack
 */

public class MergeSort extends Sort {

    Comparable[] comparables;
    // 本质上是 n^2 和 n 的常数项比较，当归并元素小于10个时，使用插入排序，具体优化程度更实际用例有关
    public static Integer EXPERIMENT_VALUE = 10;

    public MergeSort() {
    }

    public MergeSort(Comparable[] comparables) {
        this();
        this.comparables = comparables;
    }

   public static void sort(Comparable[] comparables, int left, int right) {
        if (comparables == null || left < 0 || right < 0 || left >= comparables.length || right >= comparables.length)
            return;
        Comparable[] aux = new Comparable[comparables.length];
//        merge_sort_top_down(comparables, aux, left, right);
//        merge_sort_optimized(comparables, aux, left, right);
//        merge_sort_bottom_up(comparables, aux, left, right);
       merge_sort_bottom_up_optimized(comparables, aux, left, right);
    }

   private static void merge_sort_top_down(Comparable[] comparables, Comparable[] aux, int left, int right) {
        if (left >= right) return;
        int mid = left + ((right - left) >> 1);
        merge_sort_top_down(comparables, aux, left, mid);
        merge_sort_top_down(comparables, aux, mid + 1, right);
        merge(comparables, aux, left, mid, right);
   }

   private static void merge_sort_optimized(Comparable[] comparables, Comparable[] aux, int left, int right) {
        // Optimized 1
        if (right - left <= EXPERIMENT_VALUE) {
            InsertionSort.sort(comparables, left, right);
            return;
        }
        if (left >= right) return;
        int mid = left + ((right - left) >> 1);
        merge_sort_optimized(comparables, aux, left, mid);
        merge_sort_optimized(comparables, aux, mid + 1, right);
        // Optimized 2
        if (comparables[mid].compareTo(comparables[mid + 1]) > 0) merge(comparables, aux, left, mid, right);
   }

   private static void merge_sort_bottom_up(Comparable[] comparables, Comparable[] aux, int left, int right) {
        int n = right - left + 1;
        for (int size = 1 ; size < n; size *= 2) {
            for (int i = left; i <= right - size ; i += (size * 2)) {
               // 对 arr[i...i+sz-1] 和 arr[i+sz...i+2*sz-1] 进行归并
               int mid = i + size - 1;
               int end = Math.min(i + size * 2 - 1, right);
               merge(comparables, aux, i, mid, end);
            }
        }
   }

   private static void merge_sort_bottom_up_optimized(Comparable[] comparables, Comparable[] aux, int left, int right) {
        int n = right - left + 1;
        for (int i = left; i < n; i += EXPERIMENT_VALUE) {
            InsertionSort.sort(comparables, i, Math.min(i + EXPERIMENT_VALUE, right - 1));
        }
        for (int size = 1 ; size < n; size *= 2) {
            for (int i = left; i <= right - size ; i += (size * 2)) {
                int mid = i + size - 1;
                int end = Math.min(i + size * 2 - 1, right);
                if (comparables[mid].compareTo(comparables[mid + 1]) > 0) {
                    merge(comparables, aux, i, mid, end);
                }
            }
        }
   }

   private static void merge(Comparable[] comparables, Comparable[] aux, int left, int mid, int right) {
        for (int i = left; i <= right; i ++) {
            aux[i] = comparables[i];
        }
        int left_index = left, right_index = mid + 1;
        while (left_index <= mid && right_index <= right) {
            if (aux[left_index].compareTo(aux[right_index]) <= 0) {
                comparables[left ++] = aux[left_index ++];
            } else {
                comparables[left ++] = aux[right_index ++];
            }
        }
        while (left_index <= mid) {
            comparables[left ++] = aux[left_index ++];
        }
   }

    @Override
    public void sort(Comparable[] comparables) {
        MergeSort.sort(comparables, 0, comparables.length - 1);
    }

}
