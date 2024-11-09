package src.test.template.algorithm.data_structure.hash.test;

import org.junit.Before;
import org.junit.Test;
import src.template.algorithm.data_structure.hash.MyRehashing;
import src.template.algorithm.data_structure.hash.Node;

import static org.junit.Assert.*;

public class MyRehashingTest {

    private MyRehashing<Integer, String> rehasher;

    @Before
    public void setUp() {
        rehasher = new MyRehashing<>();
    }

    @Test
    public void testRehashing() {
        Node<Integer, String>[] oldHashTable = new Node[4];
        oldHashTable[0] = new Node(1, "Value1");
        oldHashTable[1] = new Node(2, "Value2");
        oldHashTable[2] = new Node(3, "Value3");
        oldHashTable[3] = new Node(4, "Value4");
        // Test rehashing operation
        Node<Integer, String>[] newHashTable = rehasher.rehashing(oldHashTable);

        // Check the capacity of the new hash table (should be 4 * 2 = 8)
        assertEquals(16, newHashTable.length);
    }

    @Test
    public void testEmptyRehashing() {
        Node<Integer, String>[] oldHashTable = new Node[0];

        Node<Integer, String>[] newHashTable = rehasher.rehashing(oldHashTable);
        assertEquals(0, newHashTable.length);
    }

    @Test
    public void testNullRehashing() {
        Node<Integer, String>[] newHashTable = rehasher.rehashing(null);
        assertNull(newHashTable);
    }

    private boolean contains(Node<Integer, String>[] hashTable, Integer key, String value) {
        for (Node<Integer, String> node : hashTable) {
            while (node != null) {
                if (node.getKey().equals(key) && node.getValue().equals(value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }
}
