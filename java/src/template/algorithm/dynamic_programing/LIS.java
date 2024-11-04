package src.template.algorithm.dynamic_programing;

import java.util.Arrays;

/**
 * Longest Increasing Subsequence
 *
 * Example 1:
 * Input: nums = [10, 9, 2, 5, 3, 7, 101, 18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
 *
 * Example 2:
 * Input: nums = [0, 1, 0, 3, 2, 3]
 * Output: 4
 *
 * Example 3:
 * Input: nums = [7, 7, 7, 7, 7, 7, 7]
 * Output: 1
 *
 * Constraints:
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 */

public class LIS {

    public int longest_increasing_subsequence(int[] A) {
        if (A == null || A.length == 0) return 0;
        int n = A.length;
        int[] res = new int[n], index = new int[n];
        // Initialize the memoization array with -1 (uncomputed state)
        Arrays.fill(index, 0);
        return dfs(A, index, 0);
    }

    private int dfs_1(int[] A, int[] index, int cur) {
        if(cur > index[cur]) {
            index[cur] = cur;
            return cur;
        }
        int temp = Integer.MIN_VALUE;
        for (int i = index[cur] + 1; i < A.length; i ++) {
            if (A[i] > A[index[cur]]) {
                index[cur + 1] = i;
                temp = Math.max(dfs_1(A, index, cur + 1) + 1, temp);
            }
        }
        return temp;
    }


    int dfs(int[] a, int[] res, int cur) {
        // If the result is already computed, return it
        if (res[cur] != -1) return res[cur];

        int maxLength = 1; // The length of LIS starting at `cur` is at least 1 (itself)

        // Explore the next elements to see if we can extend the LIS
        for (int next = cur + 1; next < a.length; next++) {
            if (a[next] > a[cur]) {
                maxLength = Math.max(maxLength, 1 + dfs(a, res, next));
            }
        }

        // Store the computed result in the memoization array
        res[cur] = maxLength;
        return maxLength;
    }

}
