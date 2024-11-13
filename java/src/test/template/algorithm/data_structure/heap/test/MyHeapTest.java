package src.test.template.algorithm.data_structure.heap.test;

import org.junit.Before;
import org.junit.Test;
import src.template.algorithm.data_structure.heap.impl.MyHeap;

import static org.junit.Assert.*;

public class MyHeapTest {

    private MyHeap<Integer> maxHeap;
    private MyHeap<Integer> minHeap;

    @Before
    public void setUp() {
        maxHeap = new MyHeap<>("max", 10);
        minHeap = new MyHeap<>("min", 10);
    }

    @Test
    public void testOfferAndPollMaxHeap() {
        maxHeap.offer(0, 15);
        maxHeap.offer(1, 10);
        maxHeap.offer(2, 20);
        maxHeap.offer(3, 5);

        assertEquals(Integer.valueOf(20), maxHeap.poll());
        assertEquals(Integer.valueOf(15), maxHeap.poll());
        assertEquals(Integer.valueOf(10), maxHeap.poll());
        assertEquals(Integer.valueOf(5), maxHeap.poll());
        assertTrue(maxHeap.isEmpty());
    }

    @Test
    public void testOfferAndPollMinHeap() {
        minHeap.offer(0, 15);
        minHeap.offer(1, 10);
        minHeap.offer(2, 20);
        minHeap.offer(3, 5);

        assertEquals(Integer.valueOf(5), minHeap.poll());
        assertEquals(Integer.valueOf(10), minHeap.poll());
        assertEquals(Integer.valueOf(15), minHeap.poll());
        assertEquals(Integer.valueOf(20), minHeap.poll());
        assertTrue(minHeap.isEmpty());
    }

    @Test
    public void testUpdateValueMaxHeap() {
        maxHeap.offer(0, 15);
        maxHeap.offer(1, 10);
        maxHeap.offer(2, 20);

        maxHeap.update(1, 25);

        assertEquals(Integer.valueOf(25), maxHeap.peek());
        assertEquals(1, maxHeap.getMaxIndex());
    }

    @Test
    public void testUpdateValueMinHeap() {
        minHeap.offer(0, 15);
        minHeap.offer(1, 10);
        minHeap.offer(2, 20);

        minHeap.update(2, 5);

        assertEquals(Integer.valueOf(5), minHeap.poll());
    }

    @Test
    public void testContains() {
        maxHeap.offer(0, 15);
        maxHeap.offer(1, 10);

        assertTrue(maxHeap.contains(0));
        assertTrue(maxHeap.contains(1));
        assertFalse(maxHeap.contains(2));
    }

    @Test
    public void testGetMaxIndex() {
        maxHeap.offer(0, 10);
        maxHeap.offer(1, 20);
        maxHeap.offer(2, 15);

        assertEquals(1, maxHeap.getMaxIndex());
    }

    @Test
    public void testSizeAndIsEmpty() {
        assertTrue(maxHeap.isEmpty());

        maxHeap.offer(0, 15);
        maxHeap.offer(1, 10);

        assertEquals(2, maxHeap.size());
        assertFalse(maxHeap.isEmpty());

        maxHeap.poll();
        assertEquals(1, maxHeap.size());

        maxHeap.poll();
        assertTrue(maxHeap.isEmpty());
    }

    @Test
    public void testEdgeCasesWithDuplicates() {
        maxHeap.offer(0, 100);
        maxHeap.offer(1, 100);
        maxHeap.offer(2, 100);

        assertEquals(Integer.valueOf(100), maxHeap.poll());
        assertEquals(Integer.valueOf(100), maxHeap.poll());
        assertEquals(Integer.valueOf(100), maxHeap.poll());
        assertTrue(maxHeap.isEmpty());
    }

    @Test
    public void testExpansion() {
        MyHeap<Integer> heap = new MyHeap<>("max", 2); // Small initial capacity

        heap.offer(0, 10);
        heap.offer(1, 20);

        // Trigger expansion
        heap.offer(2, 30);

        assertEquals(3, heap.size());
        assertEquals(Integer.valueOf(30), heap.poll()); // Check highest priority element after expansion
    }

    @Test
    public void testClear() {
        maxHeap.offer(0, 15);
        maxHeap.offer(1, 10);
        maxHeap.offer(2, 20);

        maxHeap.clear();
        assertTrue(maxHeap.isEmpty());
    }
}
