package src.template.algorithm.two_pointers;

import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */

public class SlidingWindowMaximum {

    /**
     * Method brute force
     * Time: O(nk) Space: O(1) excluding the output
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] max_sliding_window_brute_force(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length * k == 0) return new int[]{};
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            int curMax = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                curMax = Math.max(curMax, nums[j]);
            }
            res[i] = curMax;
        }
        return res;
    }

    /**
     * Method 2: 双端队列，维护一个滑动窗口
     * Sliding Window
     * Time: O(n) Space: O(k) worst case
     * @param nums
     * @param k
     * @return
     */
    public int[] max_sliding_window_two_pointers(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length * k == 0) return new int[0];
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            // insight 1: store the index of the nums rather than the value itself
            // insight 2: maintain Monotonic queue in the sliding window
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            deque.offerLast(i);
            if (i >= k - 1) {
                res[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return res;
    }

    /**
     * Method 3, greedy solution
     * Time: O(n), Space: O(1)
     * @param nums
     * @param k
     * @return
     */
    public int[] max_sliding_window_greedy(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length * k == 0) return new int[0];
        int n = nums.length;
        if (n == 1 || k == 1) return nums;
        int[] window = new int[n - k + 1];
        int max_index = -1, index = 0;
        for (int i = 0; i < n - k + 1; i++) {
            // case 1: max_index -> the index of the max value within the sliding window
            if (i > max_index) {
                max_index = i;
                for (int m = i; m < i + k; m ++) {
                    // traverse the sliding window and find the max value
                    if (nums[m] >= nums[max_index]) {
                        max_index = m;
                    }
                }
            } else {
                if (nums[i + k - 1] > nums[max_index]) {
                    // if the new joinee is larger than the old generation max, set the max index to the index of new joinee
                    max_index = i + k - 1;
                }
            }
            window[index ++] = nums[max_index];
        }
        return window;

    }
}