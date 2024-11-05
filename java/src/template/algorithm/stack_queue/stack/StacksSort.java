package src.template.algorithm.stack_queue.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Description:
 *  The numbers are initially in stack `s1`. After sorting, the numbers should remain in `s1` such that
 *  they are sorted in ascending order from top to bottom.
 * Key insight:
 *  Hanoi Problem, you can solve it in iterative way or recursive way
 */

public class StacksSort<T extends Comparable<T>> {

    /**
     * Stack_Sort AKA Hanoi_Sort
     * @param s1
     */
    public void hanoi_sort(Deque<T> s1) {
        // use extra 2 stacks to simulate the hanoi problem
        Deque<T> s2 = new LinkedList<T>();;
        Deque<T> s3 = new LinkedList<T>();
        sort(s1, s2, s3, s1.size());
    }

    // Image that s1, s2, s3 is three hanoi towers
    private void sort(Deque<T> s1, Deque<T> s2, Deque<T> s3, int size) {

        if(s1.size() <= 1) return;
        int mid1 = size / 2;
        int mid2 = size - mid1;

        for (int i = 0; i < mid1; i++) {
            s2.offerFirst(s1.pollFirst());
        }

        sort(s2, s3, s1, mid1);
        sort(s1, s3, s2, mid2);

        int i = 0, j = 0;
        while (i < mid1 && j < mid2) {
            if (s2.peekFirst().compareTo(s1.peekFirst()) < 0) {
                s3.offerFirst(s2.pollFirst());
                i ++;
            } else {
                s3.offerFirst(s1.pollFirst());
                j ++;
            }
        }
        while (i < mid1) {
            s3.offerFirst(s1.pollFirst());
            i ++;
        }
        while(j < mid2) {
            s3.offerFirst(s2.pollFirst());
            j ++;
        }
        for (i = 0; i < size; i ++) {
            s1.offerFirst(s3.pollFirst());
        }
    }
}
