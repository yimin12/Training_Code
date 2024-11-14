package src.test.template.algorithm.data_structure.tree.impl;

import org.junit.Before;
import org.junit.Test;
import src.template.algorithm.data_structure.tree.impl.MyBinarySearchTree;
import src.template.algorithm.data_structure.tree.impl.TreeNode;

import java.util.List;

import static org.junit.Assert.*;

public class MyBinarySearchTreeTest {

    private MyBinarySearchTree<Integer, String> bst;

    @Before
    public void setUp() {
        bst = new MyBinarySearchTree<>();
    }

    @Test
    public void testInsertAndGetSize() {
        assertTrue(bst.insert(10, "Ten"));
        assertTrue(bst.insert(5, "Five"));
        assertTrue(bst.insert(15, "Fifteen"));
        assertEquals(3, bst.size());
    }

    @Test
    public void testInsertDuplicateKey() {
        bst.insert(10, "Ten");
        bst.insert(10, "New Ten");
        assertEquals(1, bst.size());  // Size should not increase
        assertEquals("New Ten", bst.get(10));  // Value should be updated
    }

    @Test
    public void testSearch() {
        bst.insert(10, "Ten");
        bst.insert(5, "Five");
        bst.insert(15, "Fifteen");

        assertEquals("Ten", bst.get(10));
        assertEquals("Five", bst.get(5));
        assertEquals("Fifteen", bst.get(15));
        assertNull(bst.get(20));  // Non-existing key
    }

    @Test
    public void testContains() {
        bst.insert(10, "Ten");
        assertTrue(bst.contains(10));
        assertFalse(bst.contains(5));
    }

    @Test
    public void testGetMin() {
        bst.insert(10, "Ten");
        bst.insert(5, "Five");
        bst.insert(15, "Fifteen");
        bst.insert(2, "Two");

        assertEquals("Two", bst.getMin());
    }

    @Test
    public void testGetMax() {
        bst.insert(10, "Ten");
        bst.insert(5, "Five");
        bst.insert(15, "Fifteen");
        bst.insert(20, "Twenty");

        assertEquals("Twenty", bst.getMax());
    }

    @Test
    public void testRemoveMin() {
        bst.insert(10, "Ten");
        bst.insert(5, "Five");
        bst.insert(15, "Fifteen");
        bst.insert(2, "Two");

        bst.removeMin();
        assertEquals("Five", bst.getMin());
        assertEquals(3, bst.size());
    }

    @Test
    public void testRemoveMax() {
        bst.insert(10, "Ten");
        bst.insert(5, "Five");
        bst.insert(15, "Fifteen");
        bst.insert(20, "Twenty");

        bst.removeMax();
        assertEquals("Fifteen", bst.getMax());
        assertEquals(3, bst.size());
    }

    @Test
    public void testRemove() {
        bst.insert(10, "Ten");
        bst.insert(5, "Five");
        bst.insert(15, "Fifteen");

        assertTrue(bst.remove(10));
        bst.prettyPrint();

        assertFalse(bst.contains(10));
        assertEquals(2, bst.size());
    }

    @Test
    public void testRemoveNonExistingKey() {
        bst.insert(10, "Ten");
        assertFalse(bst.remove(5));  // Non-existing key
        assertEquals(1, bst.size());
    }

    @Test
    public void testInOrderTraversal() {
        bst.insert(10, "Ten");
        bst.insert(5, "Five");
        bst.insert(15, "Fifteen");

        List<TreeNode<Integer, String>> inOrderList = bst.inOrder();
        assertEquals("Five", inOrderList.get(0).getValue());
        assertEquals("Ten", inOrderList.get(1).getValue());
        assertEquals("Fifteen", inOrderList.get(2).getValue());
    }

    @Test
    public void testPreOrderTraversal() {
        bst.insert(10, "Ten");
        bst.insert(5, "Five");
        bst.insert(15, "Fifteen");

        List<TreeNode<Integer, String>> preOrderList = bst.preOrder();
        assertEquals("Ten", preOrderList.get(0).getValue());
        assertEquals("Five", preOrderList.get(1).getValue());
        assertEquals("Fifteen", preOrderList.get(2).getValue());
    }

    @Test
    public void testPostOrderTraversal() {
        bst.insert(10, "Ten");
        bst.insert(5, "Five");
        bst.insert(15, "Fifteen");

        List<Integer> postOrderList = bst.postOrder();
        assertEquals(Integer.valueOf(5), postOrderList.get(0));
        assertEquals(Integer.valueOf(15), postOrderList.get(1));
        assertEquals(Integer.valueOf(10), postOrderList.get(2));
    }

    @Test
    public void testLevelOrderTraversal() {
        bst.insert(10, "Ten");
        bst.insert(5, "Five");
        bst.insert(15, "Fifteen");

        List<List<Integer>> levelOrderList = bst.levelOrder();
        assertEquals(Integer.valueOf(10), levelOrderList.get(0).get(0));
        assertEquals(Integer.valueOf(5), levelOrderList.get(1).get(0));
        assertEquals(Integer.valueOf(15), levelOrderList.get(1).get(1));
    }

    @Test
    public void testIsEmptyAndSize() {
        assertTrue(bst.isEmpty());
        bst.insert(10, "Ten");
        assertFalse(bst.isEmpty());
        assertEquals(1, bst.size());
    }

    @Test
    public void testEdgeCasesSingleElement() {
        bst.insert(10, "Ten");
        assertEquals("Ten", bst.getMin());
        assertEquals("Ten", bst.getMax());
        bst.removeMin();
        assertTrue(bst.isEmpty());
    }

    @Test
    public void testInsertManyElements() {
        for (int i = 1; i <= 1000; i++) {
            bst.insert(i, "Value " + i);
        }
        assertEquals(1000, bst.size());
        assertEquals("Value 1", bst.getMin());
        assertEquals("Value 1000", bst.getMax());
    }

    @Test
    public void testRemoveManyElements() {
        for (int i = 1; i <= 1000; i++) {
            bst.insert(i, "Value " + i);
        }
        for (int i = 1; i <= 500; i++) {
            bst.remove(i);
        }
        assertEquals(500, bst.size());
        assertEquals("Value 501", bst.getMin());
    }
}

