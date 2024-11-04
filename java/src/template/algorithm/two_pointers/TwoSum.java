package src.template.algorithm.two_pointers;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Example 2:
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * Example 3:
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 */
public class TwoSum {

    /**
     * Method 1: Brute force
     * Enumerate all possibilities
     * Time: O(n^2) Space: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] two_sum_brute(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }

    /**
     * Method 2: Optimized by hashtable
     * Use hashmap to trade off the space to time
     * Time(
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] two_sum_hash_table(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    /**
     * Method 3: Two pointer if the data is sorted
     * Time: O(n), Spaec: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] two_sum_two_pointer(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        // Arrays.sort(nums); -> if data is un-sorted, time O(n*logn)
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                return new int[]{left, right};
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }
}