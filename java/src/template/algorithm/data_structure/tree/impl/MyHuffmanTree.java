package src.template.algorithm.data_structure.tree.impl;

import src.template.algorithm.data_structure.hash.MyHashMap;

import java.util.PriorityQueue;

/**
 * Optimized the tree based on the weight.
 * Huffman encoding and decoding.
 */
public class MyHuffmanTree<Weight extends Comparable<Weight>, Data> {

    private MyHashMap<Data, String> prefix;
    private HuffmanNode<Weight, Data> root;

    public MyHuffmanTree() {
        prefix = new MyHashMap<>();
    }

    public String encode(Data[] dataset) {
        MyHashMap<Data, Integer> frequency = new MyHashMap<>();
        for (Data data : dataset) {
            for (int i = 0; i < dataset.length; i++) {
                frequency.put(data, frequency.getOrDefault(data, 0) + 1);
            }
        }
        root = build(frequency);
        StringBuilder sb = new StringBuilder();
        setPrefix(root, sb);
        sb = new StringBuilder();
        for (Data data : dataset) {
            sb.append(prefix.get(data));
        }
        return sb.toString();
    }

    private void setPrefix(HuffmanNode<Weight, Data> node, StringBuilder sb) {
        if (node != null) {
            if (node.isLeaf()) {
                prefix.put(node.getValue(), sb.toString());
            } else {
                sb.append('0');
                setPrefix(node.left, sb);
                sb.deleteCharAt(sb.length() - 1);

                sb.append('1');
                setPrefix(node.right, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public String decode(String encodedStr) {
        StringBuilder decodedStr = new StringBuilder();
        HuffmanNode<Weight, Data> currentNode = root;

        for (int i = 0; i < encodedStr.length(); i++) {
            char c = encodedStr.charAt(i);
            if (c == '0') {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }

            if (currentNode.isLeaf()) {
                decodedStr.append(currentNode.getValue());
                currentNode = root;
            }
        }
        return decodedStr.toString();
    }

    public HuffmanNode<Weight, Data> build(MyHashMap<Data, Integer> frequency) {
        PriorityQueue<HuffmanNode<Integer, Data>> pq = new PriorityQueue<>((a, b) -> a.getKey() - b.getKey());

        for (Data c : frequency.keySet()) {
            HuffmanNode<Integer, Data> node = new HuffmanNode<>(frequency.get(c), c);
            pq.offer(node);
        }

        while (pq.size() > 1) {
            HuffmanNode<Integer, Data> left = pq.poll();
            HuffmanNode<Integer, Data> right = pq.poll();
            int combinedWeight = left.getKey() + right.getKey();
            HuffmanNode<Integer, Data> parent = new HuffmanNode<>(combinedWeight, null);
            parent.left = left;
            parent.right = right;
            pq.offer(parent);
        }
        return (HuffmanNode<Weight, Data>) pq.poll();
    }

    static class HuffmanNode<Weight extends Comparable<Weight>, Data> extends TreeNode<Weight, Data> {
        HuffmanNode<Weight, Data> left, right;

        public HuffmanNode(Weight weight, Data data) {
            super(weight, data);
        }

        public int compareTo(HuffmanNode<Weight, Data> o) {
            return this.getKey().compareTo(o.getKey());
        }

        boolean isLeaf() {
            return left == null && right == null;
        }
    }
}
