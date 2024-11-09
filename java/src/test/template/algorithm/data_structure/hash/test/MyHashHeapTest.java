package src.test.template.algorithm.data_structure.hash.test;

import org.junit.Before;
import org.junit.Test;
import src.template.algorithm.data_structure.hash.MyHashHeap;

import static org.junit.Assert.*;

public class MyHashHeapTest {

    private MyHashHeap<Integer> minHeap;
    private MyHashHeap<Integer> maxHeap;

    @Before
    public void setUp() {
        minHeap = new MyHashHeap<>("min");
        maxHeap = new MyHashHeap<>("max");
    }

    @Test
    public void testAddAndPeakMinHeap() {
        for (int value : new int[]{15, 22, 3, 9, 12, 7, 4, 1, 5, 8, 6, 2}) {
            minHeap.add(value);
        }
        assertEquals(Integer.valueOf(1), minHeap.peak());
    }

    @Test
    public void testAddAndPeakMaxHeap() {
        for (int value : new int[]{15, 22, 3, 9, 12, 7, 4, 1, 5, 8, 6, 2}) {
            maxHeap.add(value);
        }
        assertEquals(Integer.valueOf(22), maxHeap.peak());
    }

    @Test
    public void testPollMinHeap() {
        for (int value : new int[]{15, 22, 3, 9, 12, 7, 4, 1, 5, 8, 6, 2}) {
            minHeap.add(value);
        }
        assertEquals(Integer.valueOf(1), minHeap.poll());
        assertEquals(Integer.valueOf(2), minHeap.peak());
    }

    @Test
    public void testPollMaxHeap() {
        for (int value : new int[]{15, 22, 3, 9, 12, 7, 4, 1, 5, 8, 6, 2}) {
            maxHeap.add(value);
        }
        assertEquals(Integer.valueOf(22), maxHeap.poll());
        assertEquals(Integer.valueOf(15), maxHeap.peak());
    }

    @Test
    public void testDeleteElement() {
        for (int value : new int[]{15, 22, 3, 9, 12, 7, 4, 1, 5, 8, 6, 2}) {
            minHeap.add(value);
        }
        minHeap.delete(7);
        assertFalse(minHeap.contains(7));
        assertEquals(Integer.valueOf(1), minHeap.peak());
    }

    @Test
    public void testUpdateElement() {
        for (int value : new int[]{15, 22, 3, 9, 12, 7, 4, 1, 5, 8, 6, 2}) {
            minHeap.add(value);
        }
        minHeap.update(3, 18);
        assertEquals(Integer.valueOf(1), minHeap.peak());
        assertTrue(minHeap.contains(18));
    }

    @Test
    public void testUpdateAllElements() {
        for (int value : new int[]{15, 22, 3, 9, 12, 7, 4, 1, 5, 8, 6, 2}) {
            minHeap.add(value);
        }
        minHeap.update_all(3, 18);
        assertEquals(Integer.valueOf(1), minHeap.peak());
        assertTrue(minHeap.contains(18));
        minHeap.print();
        assertFalse(minHeap.contains(3));
    }

    @Test
    public void testAddDuplicateElements() {
        for (int value : new int[]{10, 10, 5, 5, 1, 1, 8, 8, 12, 12, 20, 20}) {
            minHeap.add(value);
        }
        assertEquals(12, minHeap.size());
        assertEquals(Integer.valueOf(1), minHeap.peak());
        minHeap.poll();
        assertEquals(Integer.valueOf(1), minHeap.peak());
    }

    @Test
    public void testPollUntilEmpty() {
        for (int value : new int[]{15, 22, 3, 9, 12, 7, 4, 1, 5, 8, 6, 2}) {
            minHeap.add(value);
        }
        while (!minHeap.isEmpty()) {
            minHeap.poll();
        }
        assertTrue(minHeap.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPollFromEmptyHeap() {
        minHeap.poll();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNonExistentElement() {
        for (int value : new int[]{15, 22, 3, 9, 12, 7, 4, 1, 5, 8, 6, 2}) {
            minHeap.add(value);
        }
        minHeap.delete(100); // Element not in the heap
    }

    @Test
    public void testStressTest() {
        for (int i = 0; i < 1000; i++) {
            minHeap.add(i);
        }
        assertEquals(Integer.valueOf(0), minHeap.peak());
        for (int i = 0; i < 500; i++) {
            minHeap.poll();
        }
        assertEquals(500, minHeap.size());
    }
}
