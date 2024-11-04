package src.template.algorithm.two_pointers;

/**
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 *
 * Example 1:
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 * Example 2:
 *
 * Input: height = [1,1]
 * Output: 1
 *
 */
public class ContainerWithMostWater {

    /**
     * Method 1: Brute force, iterate all boundary
     * Time: O(n^2) Space: O(1)
     * @param height
     * @return
     */
    public int max_area_brute_force(int[] height) {
        if(height == null || height.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i ++) {
            for (int j = i + 1; j < height.length; j ++ ) {
                int h = Math.min(height[i], height[j]);
                max = Math.max(max, (j - i) * h);
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }


    /**
     * Method 2: Two pointers to optimize
     * Time: O(n) Space: O(1)
     * @param height
     * @return
     */
    public int max_area_two_pointer(int[] height) {
        if(height == null || height.length == 0) return 0;
        int max = Integer.MIN_VALUE, left = 0, right = height.length - 1;
        while (left < right) {
            int h = height[left] < height[right] ? height[left++] : height[right--];
            max = Math.max(max, (right - left + 1) * h);
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }

    /**
     * Why DP is not a good solution here?
     *  Because DP is typically used when we can break down a problem into overlapping sub-problems that build up a solution
     * 中心开花的思想，由一个点往往两旁发散是不够的，需要记录边长，增加了逻辑复杂度
     */
}
