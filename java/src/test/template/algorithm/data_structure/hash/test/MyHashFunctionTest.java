package src.test.template.algorithm.data_structure.hash.test;

import org.junit.Before;
import org.junit.Test;
import src.template.algorithm.data_structure.hash.MyHashFunction;

import static org.junit.Assert.*;

public class MyHashFunctionTest {

    private MyHashFunction myHashFunction;

    @Before
    public void setUp() {
        myHashFunction = new MyHashFunction();
    }

    @Test
    public void testHashCodeBasic() {
        char[] key = {'a', 'b', 'c', 'd'};
        int hashSize = 1000;
        int expectedHash = (int) (((97 * Math.pow(33, 3) + 98 * Math.pow(33, 2) + 99 * 33 + 100) % hashSize));
        assertEquals(expectedHash, myHashFunction.hashCode(key, hashSize));
    }

    @Test
    public void testHashCodeLargeHashSize() {
        char[] key = {'a', 'b', 'c', 'd'};
        int hashSize = 1000000;
        int hashValue = myHashFunction.hashCode(key, hashSize);
        assertTrue(hashValue >= 0 && hashValue < hashSize);
    }

    @Test
    public void testHashCodeSingleCharacter() {
        char[] key = {'z'};
        int hashSize = 50;
        int expectedHash = 'z' % hashSize;
        assertEquals(expectedHash, myHashFunction.hashCode(key, hashSize));
    }

    @Test
    public void testHashCodeEmptyKey() {
        char[] key = {};
        int hashSize = 100;
        assertEquals(0, myHashFunction.hashCode(key, hashSize));
    }

    @Test
    public void testHashCodeSpecialCharacters() {
        char[] key = {'!', '@', '#', '$'};
        int hashSize = 100;
        int hashValue = myHashFunction.hashCode(key, hashSize);
        assertTrue(hashValue >= 0 && hashValue < hashSize);
    }

    @Test
    public void testHashCodeHashSizeOne() {
        char[] key = {'a', 'b', 'c'};
        int hashSize = 1;
        assertEquals(0, myHashFunction.hashCode(key, hashSize)); // Any key should hash to 0 when HASH_SIZE is 1
    }
}

