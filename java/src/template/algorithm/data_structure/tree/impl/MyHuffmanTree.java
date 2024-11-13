package src.template.algorithm.data_structure.tree.impl;

/**
 * Optimized the tree based on the weight
 *  Huffman encoding and decoding
 */
public class MyHuffmanTree <T extends Comparable<T>> {

    public class HuffmanNode<T extends Comparable<T>> extends TreeNode<T, T>{
        public HuffmanNode(T key, T value) {
            super(key, value);
        }
    }
    public MyHuffmanTree() {

    }
}
