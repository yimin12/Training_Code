package src.contest.graph;

import src.template.Solution;
import src.template.algorithm.graph.interfaces.Graph;
import src.template.algorithm.graph.data_structure.Graph_AdjacentList_Arrays;

import java.util.PriorityQueue;

/**
 * Link: https://www.acwing.com/problem/content/342/
 * There are N communication base stations in the suburbs and P bidirectional cables. The i-th cable connects base station Ai and Bi.
 * In particular, the 1st base station is the headquarters of the telecommunications company, and the Nth base station is located on a farm.
 * Now, the farmer wishes to upgrade the communication lines, where upgrading the i-th cable incurs a cost of Li.
 * The telephone company is currently offering a promotional event.
 * The farmer can designate a path from the 1st base station to the Nth base station, and specify that no more than K cables on this path will be upgraded by the telephone company for free.
 * The farmer only needs to pay for the most expensive cable among the remaining cables on that path.
 * The task is to find the minimum cost required to complete the upgrade.
 * Input Format:
 *    The first line contains three integers N, P, K.
 *    The next P+1 lines: the i+1 line contains three integers Ai, Bi, Li.
 * Output Format:
 *    Contains a single integer representing the minimum cost.
 * If there is no path between the 1st base station and the Nth base station, output -1
 * Data Range:
 * 0 ≤ K < N ≤ 1000,
 * 1 ≤ P ≤ 10000,
 * 1 ≤ Li ≤ 1000000
 *
 * Sample Input:
 * 5 7 1
 * 1 2 5
 * 3 1 4
 * 2 4 8
 * 3 2 3
 * 5 2 9
 * 3 4 7
 * 4 5 6
 *
 * Sample Output:
 * 4
 */
public class TelephoneLines implements Solution<Integer> {

    /**
     * In this case: values contains information of N, M, K
     * @param g
     * @param values
     * @return
     */
    @Override
    public Integer solve(Graph g, Integer... values) {
        return solution_1((Graph_AdjacentList_Arrays) g, values);
    }

    /**
     * dijkstra, similar idea with dynamic programming, status as node and induction rule as edge
     * @param g
     * @param values
     * @return
     */
    private Integer solution_1(Graph_AdjacentList_Arrays g, Integer... values) {
        if (values == null || values.length < 3) {
            throw new IllegalArgumentException("Invalid input, At least 3 values required");
        }

        int N = values[0], M = values[1], K = values[2];
        int[][] dist = new int[N + 1][M + 1];
        boolean[][] visited = new boolean[N + 1][M + 1];
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> (a[0] - b[0]));

        dist[1][0] = 0;
        q.offer(new int[]{0, 1, 0}); // params_1: dist, params_2: from, params_3: to
        while(!q.isEmpty()) {
            int i = q.peek()[1], j = q.peek()[2];
            q.poll();
            if (visited[i][j]) continue;
            visited[i][j] = true;
            for (int cursor = g.head[i]; cursor != 0; cursor = g.next[cursor]){
                int y = g.vertex[cursor], z = g.edges[cursor];
                if (dist[y][j] > Math.max(dist[i][j], z)) {
                    dist[y][j] = Math.max(dist[i][j], z);
                    q.offer(new int[]{dist[y][j], y, j});
                }
                if (j < K && dist[y][j+1] > dist[i][j]) {
                    dist[y][j+1] = dist[i][j];
                    q.offer(new int[]{dist[y][j+1], y, j + 1});
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int t = 0; t < K; t ++) {
            res = Math.min(res, dist[N][t]);
        }
        if (res == Integer.MAX_VALUE) return -1;
        else return res;
    }

    /**
     * Binary Search is always a good solution for minimax problems
     * @param g
     * @param values
     * @return
     */
    private Integer solution_2(Graph g, Integer... values) {
        if (values == null || values.length < 3) {
            throw new IllegalArgumentException("Invalid input, At least 3 values required");
        }
        int N = values[0], M = values[1], K = values[2];

        return null;
    }
}
