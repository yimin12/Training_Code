package src.test.template.algorithm.data_structure.tree.impl;

import org.junit.Before;
import org.junit.Test;
import src.template.algorithm.data_structure.tree.impl.MyFenwickTree;

import static org.junit.Assert.*;

public class MyFenwickTreeTest {

    private MyFenwickTree fenwickTree;
    private final int capacity = 10;

    @Before
    public void setUp() {
        fenwickTree = new MyFenwickTree(capacity);
    }

    @Test
    public void testInitialization() {
        // Test that the tree initializes correctly with all zeroes
        for (int i = 1; i <= capacity; i++) {
            assertEquals(0, fenwickTree.sum(i, i));
        }
    }

    @Test
    public void testAddAndSumSingleIndex() {
        fenwickTree.add(3, 5);
        assertEquals(5, fenwickTree.sum(3, 3));

        fenwickTree.add(5, 10);
        assertEquals(10, fenwickTree.sum(5, 5));
    }

    @Test
    public void testAddAndSumRange() {
        fenwickTree.add(3, 5);
        fenwickTree.add(5, 10);
        fenwickTree.add(7, 20);

        assertEquals(5, fenwickTree.sum(3, 3));
        assertEquals(15, fenwickTree.sum(3, 5));
        assertEquals(35, fenwickTree.sum(3, 7));
    }

    @Test
    public void testRangeSumMultipleUpdates() {
        fenwickTree.add(2, 3);
        fenwickTree.add(4, 7);
        fenwickTree.add(4, 5);  // Update the same index again
        assertEquals(15, fenwickTree.sum(1, 4)); // Sum of 3 + (7+5) = 15
    }

    @Test
    public void testSet() {
        fenwickTree.add(3, 10);
        fenwickTree.set(3, 7);

        assertEquals(7, fenwickTree.sum(3, 3)); // Updated to 7
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRangeSum() {
        // Expect an exception for invalid range
        fenwickTree.sum(5, 3);
    }

    @Test
    public void testPrefixSum() {
        fenwickTree.add(1, 5);
        fenwickTree.add(2, 3);
        fenwickTree.add(3, 7);
        fenwickTree.add(4, 6);

        assertEquals(5, fenwickTree.sum(1, 1));
        assertEquals(8, fenwickTree.sum(1, 2));  // 5 + 3
        assertEquals(15, fenwickTree.sum(1, 3)); // 5 + 3 + 7
        assertEquals(21, fenwickTree.sum(1, 4)); // 5 + 3 + 7 + 6
    }

    @Test
    public void testToString() {
        fenwickTree.add(1, 5);
        fenwickTree.add(3, 7);
        String expectedOutput = "[0, 5, 5, 7, 12, 0, 0, 0, 12, 0, 0]";
        assertEquals(expectedOutput, fenwickTree.toString());
    }

    @Test
    public void testCustomConstructorWithValues() {
        long[] initialValues = {0, 3, 5, 2, 6, 1, 4, 8, 7, 9, 10};
        MyFenwickTree customFenwickTree = new MyFenwickTree(10, initialValues);

        assertEquals(3, customFenwickTree.sum(1, 1));
        assertEquals(8, customFenwickTree.sum(1, 2)); // 3 + 5
        assertEquals(10, customFenwickTree.sum(1, 3)); // 3 + 5 + 2
        assertEquals(17, customFenwickTree.sum(1, 5)); // 3 + 5 + 2 + 6 + 1
    }
}
