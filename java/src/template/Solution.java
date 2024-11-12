package src.template;

import src.template.algorithm.graph.interfaces.Graph;

public interface Solution<T> {

    public T solve(Graph g, T... values);
}
