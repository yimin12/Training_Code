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
        if (root == null) {
            root = new TreeNode<>(key, value);
            size++;
            return true;
        }
        root = insert_iteratively(root, key, value);
        return true;
    }

    /**
     * If the key exists, just update the value
     * @param node
     * @param key
     * @param value
     * @return
     */
    private TreeNode<K, V> insert_recursively(TreeNode<K, V> node, K key, V value) {
        if (node == null) {
            this.size ++;
            return new TreeNode<>(key, value);
        }
        if (key.compareTo(node.getKey()) == 0) {
            node.setValue(value);
        } else if (key.compareTo(node.getKey()) < 0) {
            node.left = insert_recursively(node.left, key, value);
        } else {
            node.right = insert_recursively(node.right, key, value);
        }
        return node;
    }

    private TreeNode<K, V> insert_iteratively(TreeNode<K, V> root, K key, V value) {
        TreeNode<K, V> newNode = new TreeNode<>(key, value);
        TreeNode<K, V> current = root;
        TreeNode<K, V> parent = null;

        while (current != null) {
            parent = current;
            int cmp = key.compareTo(current.getKey());
            if (cmp == 0) {
                current.setValue(value);
                return root;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (key.compareTo(parent.getKey()) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        size++;
        return root;
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
    public List<K> postOrder() {
        List<K> list = new ArrayList<>();
        postOrder_recursive(root, list);
        return list;
    }

    private void postOrder_recursive(TreeNode<K, V> node, List<K> list) {
        if (node == null) return;
        postOrder_recursive(node.left, list);
        postOrder_recursive(node.right, list);
        list.add(node.getKey());
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
    public List<List<K>> levelOrder() {
        if (root == null) return new ArrayList<>();
        List<List<K>> list = new ArrayList<>();
        MyQueue<TreeNode<K, V>> queue = new Queue<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<K> cur_list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode<K, V> cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                cur_list.add(cur.getKey());
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
        TreeNode<K, V> node = remove_iterative(root, key);
        if (root.getKey().equals(key)) {
            this.root = node;
        }
        return true;
    }

    private TreeNode<K, V> remove_recursively(TreeNode<K, V> node, K key) {
        if (node == null) return null;
        if (key.compareTo(node.getKey()) < 0) {
            node.left = remove_recursively(node.left, key);
            return node;
        } else if(key.compareTo(node.getKey()) > 0) {
            node.right = remove_recursively(node.right, key);
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

    private TreeNode<K, V> remove_iterative(TreeNode<K, V> root, K key) {
        TreeNode<K, V> parent = null;
        TreeNode<K, V> current = root;

        while (current != null && !current.getKey().equals(key)) {
            parent = current;
            if (key.compareTo(current.getKey()) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            return root;
        }

        if (current.left != null && current.right != null) {
            TreeNode<K, V> successor = current.right;
            TreeNode<K, V> successorParent = current;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.setKey(successor.getKey());
            current.setValue(successor.getValue());

            current = successor;
            parent = successorParent;
        }

        TreeNode<K, V> child = (current.left != null) ? current.left : current.right;

        if (parent == null) {
            root = child; // Set new root if we were removing the root
        } else if (parent.left == current) {
            parent.left = child;
        } else {
            parent.right = child;
        }

        size--;
        return root;
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

    public void prettyPrint() {
        if (root == null) {
            System.out.println("The tree is empty.");
        } else {
            printSubtree(root, "", true);
        }
    }

    private void printSubtree(TreeNode<K, V> node, String prefix, boolean isLeft) {
        if (node != null) {
            // Print current node
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.getKey() + " : " + node.getValue());

            // If there are children, print branches
            if (node.left != null || node.right != null) {
                // Print left subtree
                printSubtree(node.left, prefix + (isLeft ? "│   " : "    "), true);
                // Print right subtree
                printSubtree(node.right, prefix + (isLeft ? "│   " : "    "), false);
            }
        }
    }


}
