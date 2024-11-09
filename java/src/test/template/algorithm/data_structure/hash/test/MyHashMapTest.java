package src.test.template.algorithm.data_structure.hash.test;

import org.junit.Before;
import org.junit.Test;
import src.template.algorithm.data_structure.hash.MyHashMap;

import static org.junit.Assert.*;

public class MyHashMapTest {

    private MyHashMap<String, Integer> myHashMap;

    @Before
    public void setUp() {
        myHashMap = new MyHashMap<>();
    }

    @Test
    public void testPutAndGet() {
        myHashMap.put("apple", 10);
        myHashMap.put("banana", 20);
        myHashMap.put("cherry", 30);

        assertEquals(Integer.valueOf(10), myHashMap.get("apple"));
        assertEquals(Integer.valueOf(20), myHashMap.get("banana"));
        assertEquals(Integer.valueOf(30), myHashMap.get("cherry"));
    }

    @Test
    public void testUpdateValue() {
        myHashMap.put("apple", 10);
        assertEquals(Integer.valueOf(10), myHashMap.get("apple"));

        myHashMap.put("apple", 15);
        assertEquals(Integer.valueOf(15), myHashMap.get("apple"));
    }

    @Test
    public void testContainsKey() {
        myHashMap.put("apple", 10);
        myHashMap.put("banana", 20);

        assertTrue(myHashMap.containsKey("apple"));
        assertTrue(myHashMap.containsKey("banana"));
        assertFalse(myHashMap.containsKey("cherry"));
    }

    @Test
    public void testRemove() {
        myHashMap.put("apple", 10);
        myHashMap.put("banana", 20);

        assertEquals(Integer.valueOf(10), myHashMap.remove("apple"));
        assertNull(myHashMap.get("apple"));
        assertFalse(myHashMap.containsKey("apple"));

        assertNull(myHashMap.remove("cherry")); // Remove non-existent key should return null
    }

    @Test
    public void testSizeAndIsEmpty() {
        assertTrue(myHashMap.isEmpty());
        assertEquals(0, myHashMap.size());

        myHashMap.put("apple", 10);
        myHashMap.put("banana", 20);

        assertFalse(myHashMap.isEmpty());
        assertEquals(2, myHashMap.size());

        myHashMap.remove("apple");
        assertEquals(1, myHashMap.size());

        myHashMap.clear();
        assertTrue(myHashMap.isEmpty());
        assertEquals(0, myHashMap.size());
    }

    @Test
    public void testClear() {
        myHashMap.put("apple", 10);
        myHashMap.put("banana", 20);

        myHashMap.clear();
        assertTrue(myHashMap.isEmpty());
        assertEquals(0, myHashMap.size());
    }

    @Test
    public void testContainsValue() {
        myHashMap.put("apple", 10);
        myHashMap.put("banana", 20);

        assertTrue(myHashMap.containsValue(10));
        assertTrue(myHashMap.containsValue(20));
        assertFalse(myHashMap.containsValue(30));
    }
}

