package src.template.algorithm.data_structure.hash;

import src.template.algorithm.data_structure.heap.interfaces.Heap;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement Hash Heap Data structure
 *    This heap could support storing duplicate replicas with the same value
 *    0 start index - related the parent(), leftChild(), rightChild()
 * @param <T>
 */
public class HashHeap<T extends Comparable<T>> implements Heap<T> {

    List<T> heap;
    String mode;
    int size_t;
    MyHashMap<T, Node<Integer>> hash_table;

    public HashHeap(String mode) {
        this.hash_table = new MyHashMap<>();
        this.mode = mode;
        this.heap = new ArrayList<>();
        this.size_t = 0;
    }

    public T peak() {
        return heap.getFirst();
    }

    public int size() {
        return size_t;
    }

    public boolean isEmpty() {
        return size_t == 0;
    }

    @Override
    public void offer(T element) {
        add(element);
    }

    @Override
    public T peek() {
        return heap.getFirst();
    }

    private int parent(Integer index) {
        if (index == 0) {
            return -1;
        }
        return (index - 1) >> 1;
    }

    private int leftChild(Integer index) {
        return (index << 1) + 1;
    }

    private int rightChild(Integer index) {
        return (index << 1) + 2;
    }

    private boolean compare(T a, T b) {
        if (a.compareTo(b) < 0) {
            return this.mode.equals("min");
        } else {
            return this.mode.equals("max");
        }
    }

    private void swap(Integer left, Integer right) {
        T val_1 = heap.get(left), val_2 = heap.get(right);
        Integer replicas_a = hash_table.get(val_1).replicas, replicas_b = hash_table.get(val_2).replicas;
        hash_table.put(val_2, new Node<>(left, replicas_b));
        hash_table.put(val_1, new Node<>(right, replicas_a));
        heap.set(left, val_2);
        heap.set(right, val_1);
    }

    public T poll() {
        if (isEmpty()) throw new IllegalArgumentException("the heap is empty");
        this.size_t --;
        T cur_value = heap.getFirst();
        Node<Integer> cur_node = hash_table.get(cur_value);
        if (cur_node.replicas == 1) {
            swap(0, heap.size() - 1);
            hash_table.remove(cur_value);
            heap.removeLast();
            if (!heap.isEmpty()) {
                percolateDown(0);
            }
        } else {
            hash_table.put(cur_value, new Node<Integer>(0, cur_node.replicas - 1));
        }
        return cur_value;
    }

    @Override
    public void clear() {
        this.hash_table.clear();
        this.size_t = 0;
        heap = null;
    }

    public void add(T value) {
        this.add_multiple(value, 1);
    }

    private void add_multiple(T value, int times) {
        this.size_t += times;
        if (hash_table.containsKey(value)) {
            Node<Integer> cur_node = hash_table.get(value);
            hash_table.put(value, new Node<>(cur_node.index, cur_node.replicas + times));
        } else {
            heap.add(value);
            hash_table.put(value, new Node<>(heap.size() - 1, times));
        }
        percolateUp(heap.size() - 1);
    }

    public void delete(T value) {
        if (!hash_table.containsKey(value)) {
            throw new IllegalArgumentException("No such elements");
        }
        this.size_t --;
        Node<Integer> cur_node = hash_table.get(value);
        System.out.println(value.toString() + "+ " + cur_node.index + ":" + cur_node.replicas);
        int index = cur_node.index, replicas = cur_node.replicas;
        if (replicas == 1) {
            swap(index, heap.size() - 1);
            hash_table.remove(value);
            heap.removeLast();
            if (heap.size() > index) {
                // either go up, or go down, one will do nothing below
                percolateUp(index);
                percolateDown(index);
            }
        } else {
            hash_table.put(value, new Node<>(index, replicas - 1));
        }
    }

    public T update(T old_value, T new_value) {
        if (!hash_table.containsKey(old_value)) {
            return null;
        }
        delete(old_value);
        add(new_value);
        return old_value;
    }

    public T update_all(T old_value, T new_value) {
        if (!hash_table.containsKey(old_value)) {
            return null;
        }
        Node<Integer> cur_node = hash_table.get(old_value);
        Node<Integer> new_node = hash_table.getOrDefault(new_value, new Node<>(cur_node.index, cur_node.replicas));
        hash_table.remove(old_value);
        if (!hash_table.containsKey(new_value)) {
            heap.set(cur_node.index, new_value);
            hash_table.put(new_value, new_node);
            percolateUp(new_node.index);
            percolateDown(new_node.index);
        } else {
            hash_table.put(new_value, new Node<>(new_node.index, new_node.replicas + cur_node.replicas));
            delete(old_value);
        }
        return old_value;
    }

    private void percolateUp(Integer index) {
        while (parent(index) != -1) {
            int parent_index = parent(index);
            if (compare(heap.get(parent_index), heap.get(index))) {
                break;
            } else {
                swap(index, parent_index);
            }
            index = parent_index;
        }
    }

    private void percolateDown(Integer index) {
        while (leftChild(index) < heap.size()) {
            int left_index = leftChild(index), right_index = rightChild(index);
            int son;
            if (right_index >= heap.size() || compare(heap.get(left_index), heap.get(right_index))) {
                son = left_index;
            } else {
                son = right_index;
            }
            if (compare(heap.get(index), heap.get(son))) {
                break;
            } else {
                swap(index, son);
            }
            index = son;
        }
    }

    public boolean contains(T value) {
        return hash_table.containsKey(value);
    }

    public void print() {
        if (heap.isEmpty()) {
            System.out.println("Heap is empty");
            return;
        }

        System.out.print("Heap: [");
        for (int i = 0; i < heap.size(); i++) {
            System.out.print(heap.get(i));
            if (i < heap.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        if (hash_table.isEmpty()) {
            System.out.println("Hash Table is empty");
        } else {
            System.out.println("Hash Table:");
            hash_table.print();
        }

    }


    static class Node<Integer> {
        public Integer index;
        public Integer replicas;

        Node(Node<Integer> node) {
            this.index = node.index;
            this.replicas = node.replicas;
        }

        Node(Integer index, Integer replicas) {
            this.index = index;
            this.replicas = replicas;
        }

        @Override
        public String toString() {
            return "Node [index=" + index + ", replicas=" + replicas + "]";
        }
    }
}
