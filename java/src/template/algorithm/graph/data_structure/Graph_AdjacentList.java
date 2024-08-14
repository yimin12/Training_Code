package src.template.algorithm.graph.data_structure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph_AdjacentList implements Graph {

    private List<Set<Edge>> adjacentList;
    private boolean isDirected;
    private int node, edge; // number of nodes, number of edges

    /**
     * Constructor to initialize the adjacent list and load the data from file
     * @param isDirected
     */
    public Graph_AdjacentList(boolean isDirected) {
        this.isDirected = isDirected;
    }

    /**
     * Add an edge to the graph
     * @param from
     * @param to
     * @param weight
     */
    @Override
    public void add(int from, int to, int weight) {
        adjacentList.get(from).add(new Edge(to, weight));
    }

    /**
     * Print the adjacency list
     */
    @Override
    public void print() {
        for (int i = 1; i < adjacentList.size(); i++) {
            System.out.print(i + ": ");
            for (Edge edge : adjacentList.get(i)) {
                System.out.print(" -> (" + edge.getTo() + ", " + edge.getWeight() + ")");
            }
            System.out.println();
        }
    }

    /**
     * No need to clear the memory, let the Garbage Collection handle it
     */
    @Override
    public void clear() {
        System.out.println("GC will handle it");
    }

    /**
     * Read data from file and initialize the adjacency list
     * @param filePath
     */
    public void readDataFromFile(final String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            if (line != null) {
                String[] firstLine = line.split(" ");
                this.node = Integer.parseInt(firstLine[0]);
                this.edge = Integer.parseInt(firstLine[1]);
                adjacentList = new ArrayList<>(node + 1);
                for (int i = 0; i <= node; i++) {
                    adjacentList.add(new HashSet<>());
                }
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

    static class Edge {
        final int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        public int getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }
    }
}
