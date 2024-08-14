package src.template.algorithm.graph.data_structure;

public interface Graph {
    void add(int from , int to, int weight);
    void print();
    void clear();
    void readDataFromFile(final String filename);
    int getNumberOfNodes();
}
