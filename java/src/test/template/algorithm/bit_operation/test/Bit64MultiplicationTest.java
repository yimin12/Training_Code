package src.test.template.algorithm.bit_operation.test;

import org.junit.Before;
import org.junit.Test;
import src.template.algorithm.bit_operation.Bit64Multiplication;

import static org.junit.Assert.*;

public class Bit64MultiplicationTest {

    private Bit64Multiplication bit64Multiplication;

    @Before
    public void setUp() {
        bit64Multiplication = new Bit64Multiplication();
    }

    @Test
    public void testBitMultiply() {
        assertEquals(Long.valueOf(2), bit64Multiplication.bitMultiply(3L, 4L, 5L)); // 3 * 4 % 5 = 2
        assertEquals(Long.valueOf(259106859), bit64Multiplication.bitMultiply(123456789L, 987654321L, 1000000007L)); // Large number multiplication
        assertEquals(Long.valueOf(0), bit64Multiplication.bitMultiply(123456789L, 0L, 1000000007L)); // Multiply by zero
        assertEquals(Long.valueOf(123456789L % 1000000007L), bit64Multiplication.bitMultiply(123456789L, 1L, 1000000007L)); // Multiply by one
    }

    @Test
    public void testRecursiveMultiply() {
        assertEquals(Long.valueOf(2), bit64Multiplication.recursiveMultiply(3L, 4L, 5L)); // 3 * 4 % 5 = 2
        assertEquals(Long.valueOf(259106859), bit64Multiplication.recursiveMultiply(123456789L, 987654321L, 1000000007L)); // Large number multiplication
        assertEquals(Long.valueOf(0), bit64Multiplication.recursiveMultiply(123456789L, 0L, 1000000007L)); // Multiply by zero
        assertEquals(Long.valueOf(123456789L % 1000000007L), bit64Multiplication.recursiveMultiply(123456789L, 1L, 1000000007L)); // Multiply by one
    }
}
