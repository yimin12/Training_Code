package src.template.algorithm.bit_operation;

/**
 * Bit Basic Operation
 * Time: O(1), Space(1), thanks for LaiOffer
 */

public class BitBasicOperation {

    /**
     * Given a number x, test whether x's k-th bit is one -> bit tester
     * Assume the input is valid, no need to consider conner case
     */
    public int bitGetter(int a, int k) {
        return (a >> k) & 1;
    }

    /**
     *  Given a number x, set x's k-th bit to 1 -> bit setter
     */
    public int bitSetter(int a, int k) {
        return a | (1 << k);
    }

    /**
     * Given a number x, set x's k-th bit to 0 -> bit re-setter
     */
    public int bitResetter(int a, int k) {
        return a & (~(1 << k));
    }

    /**
     * Reverse all bits of a number
     */
    public int reverseBit(int a) {
        int i = 0, j = 31; // 32 bit int
        while (i < j) {
            a = reverse(a, i++, j--);
        }
        return a;
    }

    private int reverse(int a, int i, int j) {
        int left = ((a >> j) & 1), right = ((a >> i) & 1);
        if (left != right) {
            a ^= ((1 << i) | (1 << j));
        }
        return a;
    }

    /**
     * Set the lowest bit to 0
     */
    public int clearLowestBit(int x) {
        return x & (x - 1);
    }

    /**
     * Determine whether a number x is a power of 2
     * Method 1
     */
    public boolean isPowerOfTwo(int a) {
        return a > 0 && countOnes(a) == 1;
    }

    private int countOnes(int a) {
        int res = 0;
        while (a > 0) {
            res += (a & 1);
            a >>= 1;
        }
        return res;
    }


    /**
     * Determine whether a number x is a power of 2
     * Method 2
     */
    public boolean isPowerOfTwo1(int a) {
        return ((a & (a - 1)) == 0 && a > 0);
    }

    /**
     * Determine the number of bits that are different two positive
     * Method 1
     * Time:O(number of bit) Space: O(1)
     */
    public int countDifferentBits(int a, int b) {
        int res = 0;
        for (int c = a ^ b; c != 0; c >>= 1) {
            res += (c & 1);
        }
        return res;
    }

    /**
     * Determine the number of bits that are different two positive
     * Method 2
     * Time:O(log(number of bit)) Space: O(1), the bit mask inside divideConquer function is used for 32 bit int.
     * How to get the bit mask?
     *  0x55555555：这是一个固定的掩码，用于每两位对齐。它的二进制表示是 01010101010101010101010101010101。在这个掩码中，
     *  所有的奇数位是 1，偶数位是 0。这确保了我们在与 x >> 1 进行按位与操作时，只保留每两位中的低位，忽略高位。
     * 过程：
     *  假设 x = 10110110，即十进制的 182。
     *  x >> 1 操作：
     *      将 10110110 向右移动 1 位，得到 01011011。移动后，每一对相邻的位会发生改变，变成：
     *      x      = 10110110
 *          x >> 1 = 01011011
     *  (x >> 1) & 0x55555555 操作：
     *      我们使用掩码 0x55555555，它的低 8 位部分是 01010101。将 x >> 1 = 01011011 与 01010101 进行按位与操作：
     *        01011011
     *      & 01010101
     *        ----------
     *        01010001
     *      结果是 01010001，这个结果保留了每对相邻位中的低位（比如 01 这一组中保留的是 1）
     *  现在进行减法操作，将 x 减去 ((x >> 1) & 0x55555555)：
     *      x      = 10110110
     *      result = 01010001
     *      x - result = 10110110 - 01010001 = 01100101 得到的结果是 01100101。这一操作的效果是对每两位相邻的位进行部分相加。
     *  在这个例子中，通过 x - ((x >> 1) & 0x55555555)，我们将二进制数 x = 10110110 转化为 01100101。这一步的操作相当于对每一对相邻的位进行了归约（合并为一组，并累加）。
     *  这一步骤之后，接下来的步骤将进一步归约，分别处理 4 位、8 位和 16 位的分组，直到所有位数都被累加完毕。这就是分治法逐步减少规模的过程。
     */
    public int countDifferentBits2(int a, int b) {
        int diff = a ^ b;
        return divideConquer(diff);
    }

    private int divideConquer(int x) {
        // We need bit mask for using divide and conquer
        x = x - ((x >> 1) & 0x55555555);  // 每2位一组，进行相加
        x = (x & 0x33333333) + ((x >> 2) & 0x33333333);  // 每4位一组，进行相加
        x = (x + (x >> 4)) & 0x0F0F0F0F;  // 每8位一组，进行相加
        x = x + (x >> 8);  // 每16位一组，进行相加
        x = x + (x >> 16); // 每32位一组，进行相加
        return x & 0x3F;   // 取低6位
    }

}
