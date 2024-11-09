package src.template.algorithm.data_structure.hash;

public class Node<K, V> {
    final K key;
    V value;
    public Node<K, V> next;
    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node [key=" + key + ", value=" + value + "]";
    }
}
