package src.template.algorithm.data_structure.tree.impl;

public class TreeNode<K, V> {
    private K key;
    private V value;
    TreeNode<K, V> left;
    TreeNode<K, V> right;

    public TreeNode(K key, V value) {
        this.key = key;
        this.value = value;
        left = right = null;
    }

    public TreeNode(TreeNode<K, V> node) {
        this.key = node.key;
        this.value = node.value;
        this.left = node.left;
        this.right = node.right;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}