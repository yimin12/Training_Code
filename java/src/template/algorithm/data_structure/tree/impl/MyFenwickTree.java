package src.template.algorithm.data_structure.tree.impl;

import java.util.Arrays;

/**
 * Binary Indexed Tree, also known as Fenwick Tree.
 * This data structure efficiently updates elements and calculates prefix sums in a table of numbers.
 * 为什么要使用索引树：
 *  相比于前缀和与差分数组，索引树在查询与更新操作之中取得平衡
 *  前缀和：
 *      Read : O(1) Write: O(n)
 *  差分数组
 *      Read : O(n) Write: O(1)
 *  索引树
 *      Read : O(logN) Write : O(logN)
 * 对于创建数组的时候:
 * 可以发现，在这棵抽象的树种向上移动的过程其实就是不断将当前数字的最后一个1翻转为0的过程。
 * 基于这一事实，实现在Binary Indexed Tree中向上（在数组中向前）寻找母结点的代码就非常容易了。
 *
 * 例如给定一个int x = 13，这个过程可以用如下运算实现：
 *
 *     x = 13 = 0b00001101
 *    -x = -13 = 0b11110011
 *   x & (-x) = 0b00000001
 * x - (x & (-x)) = 0b00001100
 *
 * 对于更新数组元素的时候:
 * 从5开始，应当被更新的位置的坐标为原坐标加上原坐标二进制表示中最后一个1所代表的数字。
 * 这一过程和上面求和的过程刚好相反。
 *
 * 以int x = 5为例，我们可以用如下运算实现：
 *
 *     x = 5 = 0b00000101
 *    -x = -5 = 0b11111011
 *   x & (-x) = 0b00000001
 * x + (x & (-x)) = 0b00000110
 */

public class MyFenwickTree {

    final int capacity;
    private long[] fenwickTree;

    public MyFenwickTree(final int capacity) {
        this.capacity = capacity;
        this.fenwickTree = new long[capacity + 1];
    }

    public MyFenwickTree(final int capacity, long[] values) {
        this.capacity = capacity;
        this.fenwickTree = this.convert(values);
    }

    public long sum(int left, int right) {
        if(right < left) throw new IllegalArgumentException("Make sure right >= left");
        return prefixSum(right) - prefixSum(left - 1);
    }

    public void add(int index, long value) {
        while (index < this.capacity) {
            this.fenwickTree[index] += value;
            index += lsb(index);
        }
    }

    public void set(int index, long value) {
        add(index, value - sum(index, index));
    }

    private long[] convert(final long[] values) {
        long[] temp = new long[values.length];
        System.arraycopy(values, 0, temp, 0, values.length);
        for (int i = 1; i < values.length; i++) {
            int parent = i + lsb(i);
            if (parent < values.length) {
                temp[parent] += temp[i];
            }
        }
        return temp;
    }

    // Returns the value of the least significant bit (LSB)
    // lsb(108) = lsb(0b1101100) =     0b100 = 4
    // lsb(104) = lsb(0b1101000) =    0b1000 = 8
    // lsb(96)  = lsb(0b1100000) =  0b100000 = 32
    // lsb(64)  = lsb(0b1000000) = 0b1000000 = 64
    private int lsb(int i) {
        return i & (-i);
    }

    private long prefixSum(int index) {
        long sum = 0L;
        while (index != 0) {
            sum += fenwickTree[index];
            index -= lsb(index);
        }
        return sum;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.fenwickTree);
    }

    public void prettyPrint() {
        System.out.println("Fenwick Tree (Index : Value):");
        for (int i = 0; i <= capacity; i++) {
            System.out.printf("Index %2d : %d%n", i, fenwickTree[i]);
        }
    }

}
