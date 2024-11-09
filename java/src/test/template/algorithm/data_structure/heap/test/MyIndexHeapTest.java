package src.test.template.algorithm.data_structure.heap.test;

import org.junit.Before;
import org.junit.Test;
import src.template.algorithm.data_structure.heap.MyIndexHeap;

import static org.junit.Assert.*;

public class MyIndexHeapTest {

    private MyIndexHeap<Integer> maxHeap;
    private MyIndexHeap<Integer> minHeap;

    @Before
    public void setUp() {
        maxHeap = new MyIndexHeap<>("max", 10);
        minHeap = new MyIndexHeap<>("min", 10);
    }

    @Test
    public void testOfferAndPollMaxHeap() {
        maxHeap.offer(0, 15);
        maxHeap.offer(1, 10);
        maxHeap.offer(2, 20);
        maxHeap.offer(3, 5);

        maxHeap.prettyPrint();
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
        minHeap.prettyPrint();


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

        assertEquals(Integer.valueOf(25), maxHeap.getMax());
        assertEquals(1, maxHeap.getMaxIndex());
    }

    @Test
    public void testUpdateValueMinHeap() {
        minHeap.offer(0, 15);
        minHeap.offer(1, 10);
        minHeap.offer(2, 20);

        minHeap.update(2, 5);

        assertEquals(Integer.valueOf(5), minHeap.getData(2));
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
    public void testPollMaxIndex() {
        maxHeap.offer(0, 30);
        maxHeap.offer(1, 40);
        maxHeap.offer(2, 20);

        assertEquals(1, maxHeap.pollMaxIndex());
        assertEquals(0, maxHeap.pollMaxIndex());
        assertEquals(2, maxHeap.pollMaxIndex());
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
    public void testEdgeCases() {
        maxHeap.offer(0, 100);
        maxHeap.offer(1, 100);
        maxHeap.offer(2, 100);

        assertEquals(Integer.valueOf(100), maxHeap.poll());
        assertEquals(Integer.valueOf(100), maxHeap.poll());
        assertEquals(Integer.valueOf(100), maxHeap.poll());
        assertTrue(maxHeap.isEmpty());
    }
}
