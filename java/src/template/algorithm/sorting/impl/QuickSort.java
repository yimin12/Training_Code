package src.template.algorithm.sorting.impl;

import src.template.algorithm.sorting.interfaces.Sort;
import src.test.template.algorithm.test_helper.SortTestHelper;

import java.util.Random;

/**
 * 这里需要注意双路快排为何在特定情况下慢于三路快排许多，因为双路快排将相等元素也并入递归，而三路快排将相等元素排除出快排，不进入递归。实现方式
 * 主要区别在 partition 上，同时返回值略有不同
 */
public class QuickSort extends Sort {

    Comparable[] comparables;
    private static Random random = new Random(); // random select pivot
    // 本质上是 n^2 和 n 的常数项比较，当归并元素小于10个时，使用插入排序，具体优化程度更实际用例有关
    public static Integer EXPERIMENT_VALUE = 10;

    // No-argument constructor for reflective instantiation
    public QuickSort() {}

    public QuickSort(Comparable[] comparables) {
        this.comparables = comparables;
    }

    public static void quickSort(Comparable[] comparables, int left, int right) {
        if (left >= right) return;
        if (right - left <= EXPERIMENT_VALUE) {
            InsertionSort.sort(comparables, left, right);
        }
        int pivotIndex[] = partition_two_ways(comparables, left, right);
//        int pivotIndex[] = partition_three_ways(comparables, left, right);
        quickSort(comparables, left, pivotIndex[0] - 1);
        quickSort(comparables, pivotIndex[1], right);
    }

    private static int[] partition_two_ways(Comparable[] comparables, int left, int right) {
        int pivotIndex = pivotIndex(left, right);
        Comparable pivot = comparables[pivotIndex];
        swap(comparables, left, pivotIndex);
        int leftBound = left + 1, rightBound = right;
        while (leftBound <= rightBound) {
            if (comparables[leftBound].compareTo(pivot) < 0) {
                leftBound ++;
            } else if (comparables[rightBound].compareTo(pivot) >= 0) {
                rightBound --;
            } else {
                swap(comparables, leftBound ++, rightBound --);
            }
        }
        swap(comparables, left, rightBound);
        return new int[]{rightBound, rightBound + 1}; // 这里只需要返回同一个值，因为相同元素还是会进入递归
    }

    private static int[] partition_three_ways(Comparable[] comparables, int left, int right) {
        int pivotIndex = pivotIndex(left, right);
        Comparable pivot = comparables[pivotIndex];
        swap(comparables, left, pivotIndex);
        int leftBound = left, rightBound = right + 1, cursor = left + 1;
        while (cursor < rightBound) {
            if (comparables[cursor].compareTo(pivot) < 0) {
                swap(comparables, cursor ++, leftBound + 1);
                leftBound ++;
            } else if (comparables[cursor].compareTo(pivot) > 0) {
                swap(comparables, cursor, rightBound - 1);
                rightBound --;
            } else {
                cursor ++;
            }
        }
        swap(comparables, left, leftBound);
        return new int[]{leftBound, rightBound};
    }

    private static int pivotIndex(int left, int right) {
        return left + (int)(Math.random() * (right - left + 1));
    }

    @Override
    public void sort(Comparable[] comparables) {
        if (comparables == null || comparables.length == 0) return;
        QuickSort.quickSort(comparables, 0, comparables.length - 1);
    }

    public static void main(String[] args) {
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        Sort sorter = new QuickSort(arr);
        sorter.sort(arr);
        // Prints:
        // [-13, 2, 3, 4, 4, 6, 8, 10]
        SortTestHelper.testSort("src.template.algorithm.sort.QuickSort", arr);
//        System.out.println(java.util.Arrays.toString(arr));
    }
}
