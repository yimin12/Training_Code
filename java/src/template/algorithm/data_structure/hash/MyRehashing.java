package src.template.algorithm.data_structure.hash;

public class MyRehashing<K, V> {

    public Node<K, V>[] rehashing(Node<K, V>[] hash_table) {
        if (hash_table == null || hash_table.length == 0) return hash_table;
        int capacity = hash_table.length, newCapacity = hash_table.length << 2;
        Node<K, V>[] newHashTable = (Node<K, V>[])(new Node[newCapacity]);
        for (Node<K, V> node : hash_table) {
            while (node != null) {
                int pos = hashCode(node.getKey(), newCapacity);
                insert_table(newHashTable, pos, node.getKey(), node.getValue());
                node = node.next;
            }
        }
        return newHashTable;
    }

    public int hashCode(K key, int capacity) {
        int hash = key.hashCode();
        if (hash < 0) {
            hash = (hash % capacity + capacity) % capacity;
        } else {
            hash %= capacity;
        }
        return hash;
    }

    private void insert_table(Node<K, V>[] hash_table, int pos,K key, V val) {
        Node<K, V> node = hash_table[pos];
        if (node == null) hash_table[pos] = node;
        else {
            while (node != null) {
                node = node.next;
            }
            node.next = new Node(key, val);
        }
    }
}
