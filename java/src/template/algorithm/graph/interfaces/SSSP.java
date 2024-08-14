package src.template.algorithm.graph.interfaces;

import src.template.Reader;

/**
 * Single Source Shortest Path -> SSSP
 * Given a directed graph G = (V, E) where V represents vertices and E represents edges
 * |V| = n, |E| = m, we will follow this pattern that n is the number of vertices and m is the number of edges
 * vertex label as [1, n] and (x, y, z) represents from, to and distance(weight)
 */
public interface SSSP extends Reader {

    // offer option in case you have different implementation
    int solution_template(int start, int end, int option);
}
