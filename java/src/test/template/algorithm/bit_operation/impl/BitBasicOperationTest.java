package src.test.template.algorithm.bit_operation.impl;

import org.junit.Before;
import org.junit.Test;
import src.template.algorithm.bit_operation.BitBasicOperation;

import static org.junit.Assert.*;

public class BitBasicOperationTest {

    private BitBasicOperation bitBasicOperation;

    @Before
    public void setUp() {
        bitBasicOperation = new BitBasicOperation();
    }

    // Test for bitGetter method
    @Test
    public void testBitGetter() {
        assertEquals(1, bitBasicOperation.bitGetter(5, 0));  // 5 -> 101, 0th bit is 1
        assertEquals(0, bitBasicOperation.bitGetter(5, 1));  // 5 -> 101, 1st bit is 0
        assertEquals(1, bitBasicOperation.bitGetter(5, 2));  // 5 -> 101, 2nd bit is 1
    }

    // Test for bitSetter method
    @Test
    public void testBitSetter() {
        assertEquals(7, bitBasicOperation.bitSetter(5, 1));  // 5 -> 101, after setting 1st bit -> 111 (7)
        assertEquals(5, bitBasicOperation.bitSetter(5, 0));  // 5 -> 101, 0th bit is already 1
        assertEquals(13, bitBasicOperation.bitSetter(5, 3)); // 5 -> 101, after setting 3rd bit -> 1101 (13)
    }

    // Test for bitResetter method
    @Test
    public void testBitResetter() {
        assertEquals(1, bitBasicOperation.bitResetter(5, 2)); // 5 -> 101, after resetting 2nd bit -> 001 (1)
        assertEquals(5, bitBasicOperation.bitResetter(5, 1)); // 5 -> 101, 1st bit is already 0
        assertEquals(4, bitBasicOperation.bitResetter(5, 0)); // 5 -> 101, after resetting 0th bit -> 100 (4)
    }

    // Test for isPowerOfTwo method
    @Test
    public void testIsPowerOfTwo() {
        assertTrue(bitBasicOperation.isPowerOfTwo(4));  // 4 is a power of 2
        assertTrue(bitBasicOperation.isPowerOfTwo(16)); // 16 is a power of 2
        assertFalse(bitBasicOperation.isPowerOfTwo(6)); // 6 is not a power of 2
    }

    // Test for isPowerOfTwo1 method (alternative method)
    @Test
    public void testIsPowerOfTwo1() {
        assertTrue(bitBasicOperation.isPowerOfTwo1(4));  // 4 is a power of 2
        assertTrue(bitBasicOperation.isPowerOfTwo1(16)); // 16 is a power of 2
        assertFalse(bitBasicOperation.isPowerOfTwo1(6)); // 6 is not a power of 2
    }

    // Test for countDifferentBits method (Method 1)
    @Test
    public void testCountDifferentBits() {
        assertEquals(2, bitBasicOperation.countDifferentBits(5, 3));  // 5 -> 101, 3 -> 011, 2 bits differ
        assertEquals(1, bitBasicOperation.countDifferentBits(7, 5));  // 7 -> 111, 5 -> 101, 1 bit differs
        assertEquals(3, bitBasicOperation.countDifferentBits(15, 8)); // 15 -> 1111, 8 -> 1000, 3 bits differ
    }

    // Test for countDifferentBits2 method (Method 2, divide and conquer)
    @Test
    public void testCountDifferentBits2() {
        assertEquals(2, bitBasicOperation.countDifferentBits2(5, 3));  // 5 -> 101, 3 -> 011, 2 bits differ
        assertEquals(1, bitBasicOperation.countDifferentBits2(7, 5));  // 7 -> 111, 5 -> 101, 1 bit differs
        assertEquals(3, bitBasicOperation.countDifferentBits2(15, 8)); // 15 -> 1111, 8 -> 1000, 3 bits differ
    }

    @Test
    public void testClearLowestBit() {
        assertEquals(8, bitBasicOperation.clearLowestBit(12));  // 12 -> 1100, clear lowest 1 -> 1000 (8)
        assertEquals(4, bitBasicOperation.clearLowestBit(6));   // 6 -> 110, clear lowest 1 -> 100 (4)
        assertEquals(0, bitBasicOperation.clearLowestBit(1));   // 1 -> 01, clear lowest 1 -> 0
        assertEquals(0, bitBasicOperation.clearLowestBit(0));   // 0 -> 0, already no set bits
    }
}
