package src.template;

import src.template.algorithm.graph.data_structure.Graph;

public interface Solution<T> {

    public T solve(Graph g, T... values);
}
