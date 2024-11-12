package src.template.algorithm.data_structure.tree.interfaces;

import java.util.List;

public interface MySearchTree<K extends Comparable<K>, V> {

    public int size();
    public boolean isEmpty();
    public boolean contains(K key);
    public V get(K key);
    public boolean insert(K key, V value);
    public List<?> preOrder();
    public List<?> postOrder();
    public List<?> inOrder();
    public List<?> levelOrder();
    public V getMin();
    public V getMax();
    public void removeMin();
    public void removeMax();
    public boolean remove(K key);

}
