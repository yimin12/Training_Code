package src.template.algorithm.graph.impl;

import src.template.algorithm.graph.interfaces.SSSP;
import src.template.algorithm.graph.data_structure.Graph;
import src.template.algorithm.graph.data_structure.Graph_AdjacentList_Arrays;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 *  1.	Initialization: Initialize the distance to the source vertex as zero (dist[from] = 0) and all
 *      other distances as Integer.MAX_VALUE.
 * 	2.	Relaxation: For each vertex, apply relaxation for all the edges (u, v) in the graph for node - 1 times.
 * 	    The relaxation process updates the distance to the vertex v if a shorter path from the source through u to v
 * 	    is found. That is, if dist[v] > dist[u] + weight(u, v), then update dist[v] to dist[u] + weight(u, v).
 * 	3.	Check for Negative Weight Cycles: Optionally, perform a final check to detect if there are negative weight
 * 	    cycles in the graph. If we can perform another relaxation step that decreases the distance, a negative weight
 * 	    cycle exists.
 */
public class BellmanFord implements SSSP {

    public Graph graph_container;
    public int node;
    public int[] dist, head, vertex, edges, next;;
    public boolean[] visited;

    /**
     * visited has different meaning here, visited[x] means if x exist in the queue right now
     * 为什么可以通过队列中不需要有多个相同节点来优化：
     *  在队列中，BFS中节点的作用是为了在当前层级中该节点产生其他相邻节点，保证图遍历的正确性就行了。不需要相同元素反复进队列，
     *  不影响正确性，达到了剪枝的效果
     * Worst case: All nodes populate n - 1 times, and each node has m edges
     * Time: O(m*(n-1)) -> O(m*n)
     * Space: O(n) -> we use visited to dedup
     * @param from
     * @param to
     * @return
     */
    private int spfa(int from, int to) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        Queue<Integer> queue = new ArrayDeque<>(); // Init Queue
        dist[from] = 0;
        queue.offer(from);
        visited[from] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            visited[cur] = false;
            for (int i = head[cur]; i != 0; i = next[i]) {
                int nei = vertex[i], dis = edges[i];
                if (dist[nei] > dist[cur] + dis) {
                    dist[nei] = dist[cur] + dis;
                    if (!visited[nei]) {
                        queue.offer(nei);
                        visited[nei] = true;
                    }
                }
            }
        }
        for (int i : dist) {
            System.out.printf("%d", i);
            System.out.println();
        }
        System.out.println("size " + dist.length);
        return (dist[to] == Integer.MAX_VALUE) ? -1 : dist[to];
    }


    /**
     * 最坏情况spfa的时间复杂度会退化成O(nm),而对于bellmanford，我们直接让每个点都扩展m-1次，因为在没有负权环的情况下，一个节点最多能扩展m-1次
     *
     * @param from
     * @param to
     * @return
     */
    private int bellmanford(int from, int to) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        dist[from] = 0;
        // if n - 1 times of traverse could not find a smaller value, then all the nodes have been updated.
        for (int cnt = 1; cnt <= node - 1; cnt ++) {
            boolean flag = false;
            for (int n = 1; n <= node; n ++) {
                for (int cur = head[n]; cur != 0; cur = next[cur]) {
                    int nei = vertex[cur], dis = edges[cur];
                    if (dist[nei] > dist[n] + dis) {
                        dist[nei] = dist[n] + dis;
                        flag = true;
                    }
                }
            }
            if (!flag) break;
        }
        return (dist[to] == Integer.MAX_VALUE) ? -1 : dist[to];
    }

    public BellmanFord() {
        this.graph_container = new Graph_AdjacentList_Arrays(); // 使用链式向前星
    }

    @Override
    public void readDataFromFile(final String file_path) {
        this.graph_container.readDataFromFile(file_path);
        this.node = this.graph_container.getNumberOfNodes();
        this.dist = new int[node + 1];
        this.visited = new boolean[node + 1];
        this.head = ((Graph_AdjacentList_Arrays)graph_container).head;
        this.vertex = ((Graph_AdjacentList_Arrays)graph_container).vertex;
        this.edges = ((Graph_AdjacentList_Arrays)graph_container).edges;
        this.next = ((Graph_AdjacentList_Arrays)graph_container).next;
    }

    @Override
    public int solution_template(int from, int to, int option) {
        switch (option) {
            case 1:
                return bellmanford(from, to);
            default:
                return spfa(from, to);
        }
    }
}
