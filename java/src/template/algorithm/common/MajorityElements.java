package src.template.algorithm.common;

import java.util.*;

/**
* Description:
* 	Given an array of integers, the majority number is the number that occurs more than half of the size of the array. Find it.
* Example:
* 	Given [1, 1, 1, 1, 2, 2, 2], return 1
* 	Solve it O(n) time and O(1) extra space
*/

public class MajorityElements<T> {


    /**
     * Native idea using hash map
     * Time: O(n) Space: O(n)
     * @param data
     * @return
     */
    public T majority_elements_map(List<T> data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        Map<T, Integer> map = new HashMap<>();
        int listSize = data.size(), halfSize = listSize >> 1;
        T res = null;
        for (T val : data) {
            map.put(val, map.getOrDefault(val, 0) + 1);
            if (map.get(val) > halfSize) {
                res = val;
            }
        }
        return res;
    }

    /**
     *  Method 2:
     *      The Boyer-Moore Vote Algorithm solves the majority vote problem
     *      in linear time O(n) and logarithmic space O(log n)
     *  Time: O(n) Space: O(1)
     */
    public T majority_elements_boyer_moore(T[] data) {
        if (data == null || data.length == 0) return null;
        int len = data.length;
        T candidate = data[0];
        int counter = 0;
        for (T val : data) {
            if (counter == 0) {
                candidate = val;
                counter = 1;
            } else if (candidate.equals(val)) {
                counter ++;
            } else {
                counter --;
            }
        }
        counter = 0;
        for (T val : data) {
            if (val.equals(candidate)) counter ++;
        }
        if (counter < ((len + 1) >> 1)) return null;
        return candidate;
    }

    /**
     * Follow Up : Given an array of integers, the majority number is the number that occurs more than 1/3 of the size of the array.
     * Example : Given [1, 2, 1, 2, 1, 3, 3], return 1.
     * Time: O(n) Space: O(1)
     * @param data
     * @return
     */
    public T majority_elements_boyer_moore_follow_up(List<T> data) {
        if (data == null || data.isEmpty()) return null;
        int counter_1 = 0, counter_2 = 0;
        T candidate_1 = null, candidate_2 = null;
        for (T val : data) {
            if (val.equals(candidate_1)) {
                counter_1 ++;
            } else if (val.equals(candidate_2)) {
                counter_2 ++;
            } else if (counter_1 == 0) {
                candidate_1 = val;
                counter_1 = 1;
            } else if (counter_2 == 0) {
                candidate_2 = val;
                counter_2 = 1;
            } else {
                counter_1 --;
                counter_2 --;
            }
        }
        counter_1 = counter_2 = 0;
        for(T val : data) {
            if (val.equals(candidate_1)) {
                counter_1 ++;
            } else if (val.equals(candidate_2)) {
                counter_2 ++;
            }
        }
        if (counter_1 > counter_2) return counter_1 > data.size() / 3 ? candidate_1 : null;
        else return counter_2 > data.size() / 3 ? candidate_2 : null;
    }

    /**
     * Follow up 2 : Given an array of integers and a number k, the majority number is the number that occurs more than 1/k of the size of the array.
     * Given [3,1,2,3,2,3,3,4,4,4] and k=3, return 3.
     * Time: O(n) Space: O(k)
     * @param data
     * @param k
     * @return
     */
    public T majority_elements_boyer_moore_general(List<T> data, int k) {
        if (data == null || data.isEmpty() || k < 2) return null;
        Map<T, Integer> counters = new HashMap<>();
        // Step 1: Candidate selection phase
        for (T val : data) {
            counters.put(val, counters.getOrDefault(val, 0) + 1);
            // If we reach k distinct elements, decrease each count by 1
            if (counters.size() >= k) {
                // Reduce each counter by 1, and remove if it becomes 0
                counters.entrySet().removeIf(entry -> entry.setValue(entry.getValue() - 1) == 0);
            }
        }
        if (counters.isEmpty()) return null;
        counters.replaceAll((_, _) -> 0);
        // Step 3: Count occurrences of remaining k candidates
        for (T val : data) {
            if (counters.containsKey(val)) {
                counters.put(val, counters.get(val) + 1);
            }
        }
        // Step 4: Find the element with the maximum count
        int maxCount = 0;
        T maxKey = null;
        for (Map.Entry<T, Integer> entry : counters.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxKey = entry.getKey();
            }
        }
        return maxKey;
    }

}
