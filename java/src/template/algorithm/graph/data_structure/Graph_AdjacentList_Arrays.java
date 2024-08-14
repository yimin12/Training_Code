package src.template.algorithm.graph.data_structure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 链式向前星
 */
public class Graph_AdjacentList_Arrays implements Graph {

    public int[] head, vertex, edges, next;
    public int tot = 0; // cursor
    private boolean isDirected;
    private int node, edge; // number of nodes, number of edges

    /**
     * Constructor to initialize the adjacent list and load the data from file
     * @param isDirected
     */
    public Graph_AdjacentList_Arrays(boolean isDirected) {
        if (!isDirected) {
            throw new UnsupportedOperationException("Not support undirected graph");
        }
        this.isDirected = isDirected;
    }

    public Graph_AdjacentList_Arrays() {
        this.isDirected = true;
    }

    @Override
    public void add(int from, int to, int weight) {
        tot ++;
        vertex[tot] = to;
        edges[tot] = weight;
        next[tot] = head[from];
        head[from] = tot;
    }

    @Override
    public void print() {
        System.out.println("Adjacency List Representation:");
        for (int i = 1; i <= node; i++) {
            System.out.print(i + ": ");
            for (int j = head[i]; j != 0; j = next[j]) {
                System.out.print(" -> (" + vertex[j] + ", " + edges[j] + ")");
            }
            System.out.println();
        }
        // Print the arrays
        System.out.println("\n Arrays:");
        System.out.print("Node Index:  ");
        for (int i = 1; i <= node; i++) {
            System.out.printf("%4d", i);
        }
        System.out.println();
        System.out.print("Node head:   ");
        for (int i = 1; i < head.length; i++) {
            System.out.printf("%4d", head[i]);
        }
        System.out.println();
        System.out.print("Vertex:      ");
        for (int i = 1; i <= tot; i++) {
            System.out.printf("%4d", vertex[i]);
        }
        System.out.println();
        System.out.print("Edge:        ");
        for (int i = 1; i <= tot; i++) {
            System.out.printf("%4d", edges[i]);
        }
        System.out.println();
        System.out.print("next:        ");
        for (int i = 1; i <= tot; i++) {
            System.out.printf("%4d", next[i]);
        }
    }

    @Override
    public void clear() {
        System.out.println("GC will handle it");
    }

    @Override
    public void readDataFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // here I want to use Functional interface and customize the add method only
            String line = br.readLine();
            if (line != null) {
                String[] firstLine = line.split(" ");
                this.node = Integer.parseInt(firstLine[0]);
                this.edge = Integer.parseInt(firstLine[1]);
                head = new int[node + 1];
                vertex = new int[edge + 1];
                edges = new int[edge + 1];
                next = new int[edge + 1];
            }
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int u = Integer.parseInt(parts[0]);
                int v = Integer.parseInt(parts[1]);
                int w = Integer.parseInt(parts.length > 2 ? parts[2] : "1"); // Default weight to 1 if not provided
                add(u, v, w);
                if (!isDirected) add(v, u, w);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getNumberOfNodes() {
        return this.node;
    }
}
