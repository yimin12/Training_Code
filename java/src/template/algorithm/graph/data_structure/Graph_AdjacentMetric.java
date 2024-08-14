package src.template.algorithm.graph.data_structure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Graph_AdjacentMetric implements Graph {

    public int[][] matrix;
    public boolean is_directed;
    private int node, edge; // number of node, number of edge

    /**
     * start from 1, let the indexes match the labels
     * init the adjacent matrix and load the data from file
     * @param is_directed
     */
    public Graph_AdjacentMetric(boolean is_directed) {
        this.is_directed = is_directed;
    }

    /**
     * We do not specify direct or un_direct graph here, will handle it in readData scope
     *   direct graph -> add(from, to, distance)
     *   un_direct graph -> min(add(from, to, distance),matrix[to][from])
     * considering self loop edge -> min(add(from, to, distance),matrix[from][to])
     * @param from
     * @param to
     * @param weight
     */
    @Override
    public void add(int from, int to, int weight) {
        matrix[from][to] = Integer.min(weight, matrix[from][to]);
    }

    /**
     * print the matrix with good view
     */
    @Override
    public void print() {
        int length = matrix.length;
        // Print column indices starting from 1
        System.out.printf("%4s", ""); // Empty corner space
        for (int col = 1; col < length; col++) {
            System.out.printf("%4d", col);
        }
        System.out.println();
        // Print matrix with row indices starting from 1
        for (int row = 1; row < length; row++) {
            System.out.printf("%4d", row); // Print row index starting from 1
            for (int col = 1; col < length; col ++) {
                if (matrix[row][col] == Integer.MAX_VALUE) {
                    System.out.printf("%4s", "#");
                } else {
                    System.out.printf("%4d", matrix[row][col]);
                }
            }
            System.out.println();
        }
    }

    /**
     * No need to clear the memory, Let the Garbage collection do it
     */
    @Override
    public void clear() {
        System.out.println("GC will handle it");
    }

    @Override
    public void readDataFromFile(final String file_path) {
        try (BufferedReader br = new BufferedReader(new FileReader(file_path))) {
            String line = br.readLine();
            if (line != null) {
                String[] firstLine = line.split(" ");
                this.node = Integer.parseInt(firstLine[0]);
                this.edge = Integer.parseInt(firstLine[1]);
                matrix = new int[node + 1][node + 1];
                for (int i = 1; i <= this.node; i ++) {
                    for (int j = 1; j <= this.node; j++) {
                        matrix[i][j] = Integer.MAX_VALUE;
                    }
                }
            }
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int u = Integer.parseInt(parts[0]);
                int v = Integer.parseInt(parts[1]);
                int w = Integer.parseInt(parts.length > 2 ? parts[2] : "1"); // Default weight to 1 if not provided
                add(u, v, w);
                if(!is_directed) add(v, u, w);
            }
            // Assume that from a to a's weight is 0
            for (int i = 0; i < node + 1; i++) {
                matrix[i][i] = 0;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getNumberOfNodes() {
        return this.node;
    }

    public int[][] getMatrix() {
        return matrix;
    }
}
