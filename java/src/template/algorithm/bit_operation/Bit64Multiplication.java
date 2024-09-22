package src.template.algorithm.bit_operation;

/**
 * Calculate the value of (a * b) mod p.
 *
 * Input format:
 * The first line contains the integer a.
 * The second line contains the integer b.
 * The third line contains the integer p.
 *
 * Output format:
 * Output a single integer, representing (a * b) mod p.
 *
 * Data constraints:
 * 1 ≤ a, b, p ≤ 10^18
 *
 * Example input:
 * 3
 * 4
 * 5
 *
 * Example output:
 * 2
 */

public class Bit64Multiplication {

    /* Follow up of FastExponentiation */
    /**
     * Convert the multiplication to add using the same idea as fast exponential
     * a * b = a + a + a ... + a
     *           ^
     *           |
     *          b times
     * memory the path
     * a * 1 = a, a * 2 = 2a, a * 4 = 4a .... -> a * (2^k) = 2^k * a
     * Method 1: Fast exponential
     * Time: O(logn) Space: O(1)
     */
    public Long bitMultiply(Long a, Long b, Long p) {
        Long res = 0L; // careful, add operation starts with 0 rather than 1
        while (b != 0) {
            if ((b & 1) != 0) res = (res + a) % p;
            b >>= 1;
            a = a * 2 % p;
        }
        return res;
    }

    /**
     * Method 2: Recursion
     * Time: O(logn) Space: O(logn)
     */
    public Long recursiveMultiply(Long a, Long b, Long p) {
        if (b == 0) return 0L;
        Long half = recursiveMultiply(a, b >> 1, p);
        half = (half % p) + (half % p);
        if ((b & 1) ==1) half = (half % p) + (a % p);
        return half;
    }
}
