package src.template.algorithm.two_pointers;

/**
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a
 * subarray
 *  whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 *
 *
 *
 * Example 1:
 *
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * Example 2:
 *
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * Example 3:
 *
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 */

public class MinimumSizeSubarraySum {


    /**
     * Method 1
     * Brute force, enumerate all possibility
     * Time: O(n^3) Space: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int minimum_size_brute_force(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length, minLen = Integer.MAX_VALUE;
        for (int left = 0; left < n; left++) {
            for (int right = left; right < n; right++) {
                int arraySum = 0;
                for (int i = left; i <= right; i++) {
                    arraySum += nums[i];
                }
                if (arraySum >= target) {
                    minLen = Math.min(minLen, right - left + 1);
                }
            }
        }
        if (minLen == Integer.MAX_VALUE) return -1;
        return minLen;
    }

    /**
     * Method 2
     * Create extra prefix sum to trade off the space for time
     * Time: O(n^2) Space: O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public int minimum_size_prefix_sum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int len = nums.length, minLen = Integer.MAX_VALUE;
        int[] prefix = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        for (int left = 0; left < len; left++) {
            for (int right = left; right < len; right++) {
                if (prefix[right + 1] - prefix[left] >= target) {
                    minLen = Math.min(minLen, right - left + 1);
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }

    /**
     * Method 3:
     * Keep optimizing the Method 2 by using binary search, you do not have to iterate the entire array
     * Time: O(n*logn) Space: O(n)
     * Restriction:
     *   if the array contains negative value, the prefix is not always in ascending order, we can not use binary search for it
     * @param nums
     * @param target
     * @return
     */
    public int minimum_size_prefix_sum_binary_search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int len = nums.length, minLen = Integer.MAX_VALUE;
        int[] prefix = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        for (int left = 0; left < len; left++) {
            int end = binaryGetClosestEnd(prefix, left, target);
            if (prefix[end + 1] - prefix[left] >= target) {
                minLen = Math.min(minLen, end - left + 1);
            }
        }
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }

    private int binaryGetClosestEnd(int[] prefix, int start, int target) {
        int left = start, right = prefix.length - 2; // make sure we are using -2 here because we use mid + 1 below
        while (left + 1 < right) {
            int mid = left + (right - left) >> 1;
            if (prefix[mid + 1] - prefix[start] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (prefix[left + 1] - prefix[start] >= target) {
            return left;
        }
        return right;
    }

    /**
     * Method 4: Two pointers with same direction traverse
     * Time: O(n) Space: O(1)
     * @param nums
     * @param target
     * @return
     */
    public int minimum_size_two_pointers(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        int len = nums.length, minLen = Integer.MAX_VALUE, sumSubArray = 0, end = 0;
        for (int left = 0; left < len; left ++) {
            while (end < len && sumSubArray < target) {
                sumSubArray += nums[end];
                end ++; // here
            }
            if(sumSubArray >= target) {
                minLen = Math.min(minLen, end - left); // why do not need + 1? Because the end is not include, end ++ afterward check line 136
            }
            sumSubArray -= nums[left];
        }
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }
}