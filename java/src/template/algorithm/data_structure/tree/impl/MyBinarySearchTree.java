package src.template.algorithm.data_structure.tree.impl;

import src.template.algorithm.data_structure.stack_queue.queue.impl.Queue;
import src.template.algorithm.data_structure.stack_queue.queue.interfaces.MyQueue;
import src.template.algorithm.data_structure.stack_queue.stack.impl.LinkedListStack;
import src.template.algorithm.data_structure.stack_queue.stack.interfaces.MyStack;
import src.template.algorithm.data_structure.tree.interfaces.MySearchTree;

import java.util.ArrayList;
import java.util.List;

public class MyBinarySearchTree <K extends Comparable<K>, V> implements MySearchTree <K, V> {

    TreeNode<K, V> root;
    private int size;

    public MyBinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    public boolean insert(K key, V value) {
        this.root = insert(root, key, value);
        return true;
    }

    /**
     * If the key exists, just update the value
     * @param node
     * @param key
     * @param value
     * @return
     */
    private TreeNode<K, V> insert(TreeNode<K, V> node, K key, V value) {
        if (node == null) {
            this.size ++;
            return new TreeNode<>(key, value);
        }
        if (key.compareTo(node.getKey()) == 0) {
            node.value = value;
        } else if (key.compareTo(node.getKey()) < 0) {
            node.left = insert(node.left, key, value);
        } else {
            node.right = insert(node.right, key, value);
        }
        return node;
    }

    @Override
    public List<TreeNode<K, V>> preOrder() {
        List<TreeNode<K, V>> list = new ArrayList<>();
        preOrder_recursive(root, list);
        return list;
    }

    private void preOrder_recursive(TreeNode<K, V> node, List<TreeNode<K, V>> list) {
        if (node == null) return;
        list.add(node);
        preOrder_recursive(node.left, list);
        preOrder_recursive(node.right, list);
    }

    private void preOrder_iterative(TreeNode<K, V> node, List<TreeNode<K, V>> list) {
        if (node == null) return;
        MyStack<TreeNode<K, V>> stack = new LinkedListStack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode<K, V> top = stack.pop();
            if (top.left != null) {
                stack.push(top.left);
            }
            if (top.right != null) {
                stack.push(top.right);
            }
            list.add(top);
        }
    }

    @Override
    public List<TreeNode<K,V>> postOrder() {
        List<TreeNode<K, V>> list = new ArrayList<>();
        postOrder_recursive(root, list);
        return list;
    }

    private void postOrder_recursive(TreeNode<K, V> node, List<TreeNode<K, V>> list) {
        if (node == null) return;
        postOrder_recursive(node.left, list);
        postOrder_recursive(node.right, list);
        list.add(node);
    }

    /**
     * Method 1:
     *  post-order is the reverse order of pre-order with traversing right subtree before traversing left subtree
     * Method 2: -> implemented below
     *  check relation between the current node and the previous node to determine which direction to go next
     * @param node
     * @param list
     */
    private void postOrder_iterative(TreeNode<K, V> node, List<TreeNode<K, V>> list) {
        if (node == null) return;
        MyStack<TreeNode<K, V>> stack = new LinkedListStack<>();
        stack.push(node);
        TreeNode<K, V> prev = null;
        while (!stack.isEmpty()) {
            TreeNode<K, V> cur = stack.pop();
            // Case 1: going down
            if (prev == null || cur.equals(prev.left) || cur.equals(prev.right)) {
                if (cur.left != null) stack.push(cur.left);
                else if (cur.right != null) stack.push(cur.right);
                else {
                    // add to the result set if no child
                    stack.pop();
                    list.add(cur);
                }
            } else if (prev == cur.right || prev == cur.left && cur.right == null) {
            // Case 2: back up from right child or back up from left and no right child
                stack.pop();
                list.add(cur);
            } else {
            // Case 3: back up from left and it has right child
                stack.push(cur);
            }
            prev = cur;
        }
    }

    @Override
    public List<TreeNode<K, V>> inOrder() {
        List<TreeNode<K, V>> list = new ArrayList<>();
        inOrder_recursive(root, list);
        return list;
    }

    private void inOrder_recursive(TreeNode<K, V> node, List<TreeNode<K, V>> list) {
        if (node == null) return;
        inOrder_recursive(node.left, list);
        list.add(node);
        inOrder_recursive(node.right, list);
    }

    private void inOrder_iterative(TreeNode<K, V> node, List<TreeNode<K, V>> list) {
        if (node == null) return;
        MyStack<TreeNode<K, V>> stack = new LinkedListStack<>();
        TreeNode<K, V> cur = node;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                list.add(cur);
                cur = cur.right;
            }
        }
    }


    @Override
    public List<List<TreeNode<K, V>>> levelOrder() {
        if (root == null) return new ArrayList<>();
        List<List<TreeNode<K, V>>> list = new ArrayList<>();
        MyQueue<TreeNode<K, V>> queue = new Queue<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode<K, V>> cur_list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode<K, V> cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                cur_list.add(cur);
            }
            list.add(cur_list);
        }
        return list;
    }

    @Override
    public V getMin() {
        assert this.size != 0;
        TreeNode<K, V> minNode = minimum(root);
        return minNode.getValue();
    }

    private TreeNode<K, V> minimum(TreeNode<K, V> node) {
        if (node.left == null) return node;
        return minimum(node.left);
    }

    @Override
    public V getMax() {
        assert this.size != 0;
        TreeNode<K, V> maxNode = maximum(root);
        return maxNode.getValue();
    }

    private TreeNode<K, V> maximum(TreeNode<K, V> node) {
        if (node.right == null) return node;
        return maximum(node.right);
    }

    @Override
    public void removeMin() {
        if (root != null) {
            root = removeMin(root);
        }
    }

    private TreeNode<K, V> removeMin(TreeNode<K, V> node) {
        if (node.left == null) {
            TreeNode<K, V> rightNode = node.right;
            node.right = null;
            this.size --;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public void removeMax() {
        if (root != null) {
            root = removeMax(root);
        }
    }

    private TreeNode<K, V> removeMax(TreeNode<K, V> node) {
        if (node.right == null) {
            TreeNode<K, V> leftNode = node.left;
            node.left = null;
            this.size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    @Override
    public boolean remove(K key) {
        if (!contains(key)) return false;
        return remove(root, key) != null;
    }

    private TreeNode<K, V> remove(TreeNode<K, V> node, K key) {
        if (node == null) return null;
        if (key.compareTo(node.getKey()) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if(key.compareTo(node.getKey()) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            if (node.left == null) {
                TreeNode<K, V> rightNode = node.right;
                node.right = null;
                this.size --;
                return rightNode;
            }
            if (node.right == null) {
                TreeNode<K, V> leftNode = node.left;
                node.left = null;
                this.size --;
                return leftNode;
            }
        }
        TreeNode<K, V> successor = new TreeNode<>(minimum(node.right));
        this.size ++;
        successor.right = removeMin(node.right);
        successor.left = node.left;
        node.left = node.right = null;
        this.size --;
        return successor;
    }

    public boolean contains(K key) {
        return contains(root, key);
    }

    private boolean contains(TreeNode<K, V> node, K key) {
        if (node == null) return false;
        if (key.compareTo(node.getKey()) == 0) {
            return true;
        } else if (key.compareTo(node.getKey()) < 0) {
            return contains(node.left, key);
        } else {
            return contains(node.right, key);
        }
    }

    @Override
    public V get(K key) {
        return search(root, key);
    }

    private V search(TreeNode<K, V> node, K key) {
        if (node == null) return null;
        if (key.compareTo(node.getKey()) == 0) {
            return node.getValue();
        } else if (key.compareTo(node.getKey()) < 0) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private class TreeNode<K, V> {
        private K key;
        private V value;
        private TreeNode<K, V> left, right;

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
}
