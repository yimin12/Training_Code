package src.template.algorithm.data_structure.hash;

import java.util.*;

/**
* Description:
* 	A hashtable implementation of map, demonstration purpose, generic type is provided
* 	(You need to consider concurrency problem)
* 		Cases you need to synchronized: when you need to manipulate (read or write) information of HashMap
* 	supported operations:
* 		size();
* 		isEmpty();
* 		clear();
* 		put(K key, V value);
* 		get(K key)
* 		containsKey(K key)
* 		containsValue(V value) // check if the desired value is in the map. O(n)
* 		remove(K key)
*/

public class MyHashMap<K, V> {

    public static final int DEFAULT_CAPACITY = 16;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<K, V>[] hash_table;
    private int size; // current size
    private final float loadFactor; // determine if need to rehash

    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int capacity, float loadFactor) {
        if (capacity < 0) throw new IllegalArgumentException("capacity of HashMap could not be negative");
        this.hash_table = new Node[capacity];
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    public synchronized int size() {
        return size;
    }

    public synchronized boolean isEmpty() {
        return size == 0;
    }

    public synchronized void clear() {
        Arrays.fill(this.hash_table, null);
        this.size = 0;
    }

    public int hash(K key) {
        if (key == null) throw new NullPointerException("You can not hash a null key");
        // it would guarantee non-negative, java's % return remainder rather than modulus. the remainder can be negative, int range = [-2^31, 2^31-1]
        return key.hashCode() & 0X7FFFFFFF;
    }

    private int getIndex(K key) {
        return hash(key) % hash_table.length;
    }

    /**
     * You can overwrite the equals function
     * @param v1
     * @param v2
     * @return
     */
    private boolean equalsValue(V v1, V v2) {
        return Objects.equals(v1, v2);
    }

    public synchronized boolean containsValue(V value) {
        if (isEmpty()) return false;
        for (Node<K, V> node : hash_table) {
            while (node != null) {
                if (equalsValue(node.value, (V) value)) return true;
                node = node.next;
            }
        }
        return false;
    }

    private boolean equalsKey(K k1, K k2) {
        return Objects.equals(k1, k2);
    }

    public synchronized boolean containsKey(K key) {
        int index = getIndex(key);
        Node<K, V> node = hash_table[index];
        while (node != null) {
            if (equalsKey(key, node.key)) return true;
            node = node.next;
        }
        return false;
    }

    public synchronized V get(K key) {
        int index = getIndex(key);
        Node<K, V> node = hash_table[index];
        while (node != null) {
            if (equalsKey(key, node.key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public synchronized V put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> head = hash_table[index];
        Node<K, V> cur = head;
        while (cur != null) {
            if (equalsKey(key, cur.key)) {
                V oldValue = cur.value;
                cur.value = value;
                return oldValue;
            }
            cur = cur.next;
        }
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = head;
        hash_table[index] = newNode;
        this.size++;
        if (needRehashing()) {
            rehashing();
        }
        return null;
    }

    private boolean needRehashing() {
        float ratio = (size + 0.0f) / hash_table.length;
        return ratio >= loadFactor;
    }

    private void rehashing() {
        Node<K, V>[] old_hash_table = this.hash_table;
        this.hash_table = (Node<K, V>[])(new Node[hash_table.length << 1]);
        this.size = 0;
        for (Node<K, V> node : old_hash_table) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }

    public void print() {
        if (size == 0) {
            System.out.println("HashMap is empty");
        } else {
            System.out.println("HashMap contains " + size + " elements:");
            for (Node<K, V> node : hash_table) {
                if (node != null) {
                    System.out.println("Bucket:");
                    while (node != null) {
                        System.out.println("  Key: " + node.getKey().toString() + ", Value: " + node.getValue().toString());
                        node = node.next;
                    }
                }
            }
        }
    }

    public synchronized V remove(K key) {
        int index = getIndex(key);
        Node<K, V> prev = null;
        Node<K, V> cur = hash_table[index];
        while (cur != null) {
            if (equalsKey(key, cur.key)) {
                if (prev == null) {
                    hash_table[index] = cur.next;
                    this.size --;
                    return cur.value;
                } else {
                    prev.next = cur.next;
                    this.size --;
                    return cur.value;
                }
            }
            prev = cur;
            cur = cur.next;
        }
        return null;
    }

    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (Node<K, V> node : hash_table) {
            while (node != null) {
                set.add(node.key);
                node = node.next;
            }
        }
        return set;
    }

    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (Node<K, V> node : hash_table) {
            while (node != null) {
                values.add(node.value);
                node = node.next;
            }
        }
        return values;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (Node<K, V> node : hash_table) {
            while (node != null) {
                entrySet().add(new AbstractMap.SimpleEntry<>(node.key, node.value));
                node = node.next;
            }
        }
        return entrySet();
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        return entrySet().iterator();
    }

    public synchronized V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        return value != null ? value : defaultValue;
    }
}
