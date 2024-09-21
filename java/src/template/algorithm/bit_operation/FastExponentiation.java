package src.template.algorithm.bit_operation;

/**
 * Calculate the result of a raised to the power of b, modulo p. So call (a ^ b) % p
 *
 * Input format:
 * Three integers a, b, and p, separated by spaces on the same line.
 *
 * Output format:
 * Output a single integer, representing a^b mod p.
 *
 * Data constraints:
 * 0 ≤ a, b ≤ 10^9
 * 1 ≤ p ≤ 10^9
 *
 * Input example:
 * 3 2 7
 * Output example:
 * 2
 */

public class FastExponentiation {

    /**
     * Method 1: Use Bit to Optimize, jdk21 long is 64 bit
     * Time: O(logn) Space: O(1)
     * @param a
     * @param b
     * @param p
     * @return
     */
    public Long bitFastExponentiation(int a, int b, int p) {
        long res = 1l % p;
        while (b != 0) {
            if ((b & 1) != 0) res = res * a % p; // module p in every iteration
            a = a * a % p;
            b >>= 1;
        }
        return res;
    }

    /**
     * Method 1: Use Recursion to Optimize, jdk21 long is 64 bit
     * Time: O(logn) Space: O(logn)
     * @param a
     * @param b
     * @param p
     * @return
     */
    public Long recursiveFastExponentiation(int a, int b, int p) {
        // base case
        if (b == 0) return 1l % p;
        long half = recursiveFastExponentiation(a, b >> 1, p);
        half = (half * half) % p;
        if ((b & 1) != 0) half = half * a % p;
        return half;
    }
}
