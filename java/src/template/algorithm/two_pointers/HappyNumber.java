package src.template.algorithm.two_pointers;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number n is happy.
 *
 * A happy number is a number defined by the following process:
 *
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * Example 2:
 *
 * Input: n = 2
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= n <= 231 - 1
 */

public class HappyNumber {

    /**
     *  Method 1: use two pointers, how you can know we can use two pointers like cycle-linked list -> "cycle" keyword
     *  Time: O(depth) where depth is depending on the given number and how times it calls getNext
     *  Space: O(1)
     */
    public boolean is_happy_two_pointers(int number) {
        int slow = number;
        int fast = getNext(number);
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));

        }
        return fast == 1;
    }

    /**
     * Method 2: Use set to identify the loop entrance
     * Time: O(depth) where depth is depending on the given number and how times it calls getNext
     * Time: O(depth) same as above.
     * @param number
     * @return
     */
    public boolean is_happy_two_set(int number) {
        Set<Integer> visited = new HashSet<Integer>();
        while (number != 1 && !visited.contains(number)) {
            visited.add(number);
            number = getNext(number);
        }
        return number == 1;
    }

    private int getNext(int n) {
        int total = 0;
        while (n > 0) {
            int digit = n % 10;
            n /= 10;
            total += digit * digit;
        }
        return total;
    }
}
