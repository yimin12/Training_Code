package src.template.algorithm.graph.impl;

import src.template.algorithm.graph.interfaces.SSSP;
import src.template.algorithm.graph.data_structure.Graph;
import src.template.algorithm.graph.data_structure.Graph_AdjacentMetric;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Algorithms:
 *  1. init dist[1] = 0, the others should Integer.MAX_VALUE
 *  2. find an unvisited node as an entry, marks it as visited
 *  3. get all the out-going node by (from, to, dis), if dist[to] > dist[from] + dis,
 *      then update dist[to] -> dist[to] = min(dist[to], dist[from] + dis)
 * Restrictions:
 *  1. Dijkstra bases on greedy thought, and it requires all the weight/distance of
 *      weight are non-negative
 */
public class Dijkstra implements SSSP {

    public Graph graph_container;
    public int node;
    public int[] dist;
    public int[][] graph;
    public boolean[] visited;

    public Dijkstra() {
        this.graph_container = new Graph_AdjacentMetric(true);
    }

    /**
     * Populate the node with all neighbours rather than use closest one
     * Time: O(m + n^2) -> O(n^2) where the max value of m is n^2 (dense graph)
     *  m: traverse the edge
     *  n: populate the node with all neighbours n*n in worst case
     * Space: O(n)
     *  n: visited and dist
     * Note: dist[from] = 0, the whoever gets distance as 0, it is the starting point
     * @param from
     * @param to
     * @return the shortest distance between start and end
     */
    private int dijkstra_naive(int from, int to) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        dist[from] = 0;
        for (int cnt = 1; cnt <= node; cnt++) {
            int min = Integer.MAX_VALUE, x = -1;
            for (int i = 1; i <= node; i++) {
                if (!visited[i] && dist[i] < min) {
                    min = dist[i];
                    x = i;
                }
            }
            // All remaining nodes are inaccessible from the current node -> dead end
            if (x == -1) break;
            visited[x] = true;
            for (int y = 1; y <= node; y ++) {
                dist[y] = (graph[x][y] != 0 && dist[x] != Integer.MAX_VALUE)
                        ? Math.min(dist[y], dist[x] + graph[x][y]) : dist[y];
            }
        }
        return (dist[to] == Integer.MAX_VALUE) ? -1 : dist[to];
    }

    /**
     * Populate the node with the closest one only
     * Time: O((m+n)*logn) -> O(m*logn) where m > n
     *  logn: you have n nodes in the heap or any other sorting data_structure for worst case
     *        heap: update/delete -> O(logn), peek/getMin -> O(1)
     *  n*logn: n times of updating the heap from start to end
     * Space: O(n) in worst case
     * Note: Best first search
     * @param from
     * @param to
     * @return
     */
    private int dijkstra_optimal(int from, int to) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        dist[from] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a1[1] - a2[1]); // Could Use Tuple class later
        pq.offer(new int[]{from, 0});
        // General BFS template using priority queue
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curDis = cur[1], curNode = cur[0];
            if(visited[curNode]) continue;
            visited[curNode] = true;
            for (int nei = 1; nei <= node; nei ++) {
                if (graph[curNode][nei] != 0 && !visited[nei] && dist[curNode] != Integer.MAX_VALUE) {
                    int newDis = dist[curNode] + graph[curNode][nei];
                    if (newDis < dist[nei]) {
                        dist[nei] = newDis;
                        pq.offer(new int[]{nei, newDis});
                    }
                }
            }
        }
        return (dist[to] == Integer.MAX_VALUE) ? -1 : dist[to];
    }

    @Override
    public void readDataFromFile(final String file_path) {
        this.graph_container.readDataFromFile(file_path);
        this.node = this.graph_container.getNumberOfNodes();
        this.dist = new int[node + 1];
        this.visited = new boolean[node + 1];
        this.graph = ((Graph_AdjacentMetric)graph_container).getMatrix();
    }

    @Override
    public int solution_template(int from, int to, int option) {
        switch (option) {
            case 1:
                return dijkstra_naive(from, to);
            default:
                return dijkstra_optimal(from, to);
        }
    }
}
