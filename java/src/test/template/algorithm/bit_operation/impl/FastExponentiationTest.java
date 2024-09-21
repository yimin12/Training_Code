package src.test.template.algorithm.bit_operation.impl;

import org.junit.Before;
import org.junit.Test;
import src.template.algorithm.bit_operation.FastExponentiation;

import static org.junit.Assert.*;

public class FastExponentiationTest {

    private FastExponentiation fastExponentiation;

    @Before
    public void setUp() {
        fastExponentiation = new FastExponentiation();
    }

    @Test
    public void testBitFastExponentiation() {
        assertEquals(Long.valueOf(9), fastExponentiation.bitFastExponentiation(3, 2, 10)); // 3^2 % 10 = 9
        assertEquals(Long.valueOf(4), fastExponentiation.bitFastExponentiation(2, 5, 7));  // 2^5 % 7 = 4
        assertEquals(Long.valueOf(1), fastExponentiation.bitFastExponentiation(5, 0, 11)); // 5^0 % 11 = 1
    }

    @Test
    public void testRecursiveFastExponentiation() {
        assertEquals(Long.valueOf(9), fastExponentiation.recursiveFastExponentiation(3, 2, 10)); // 3^2 % 10 = 9
        assertEquals(Long.valueOf(4), fastExponentiation.recursiveFastExponentiation(2, 5, 7));  // 2^5 % 7 = 4
        assertEquals(Long.valueOf(1), fastExponentiation.recursiveFastExponentiation(5, 0, 11)); // 5^0 % 11 = 1
    }
}

