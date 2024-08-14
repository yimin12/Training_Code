package src.test.template.algorithm.graph.impl;

import org.junit.Before;
import org.junit.Test;
import src.template.algorithm.graph.impl.Dijkstra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DijkstraTest {

    private Dijkstra dijkstra;
    final String path = "src/resource/data/dummy_data1.txt";

    @Before
    public void setUp() {
        dijkstra = new Dijkstra();
        dijkstra.readDataFromFile(path);
    }

    @Test
    public void testGraphInitialization() {
        assertNotNull(dijkstra.graph_container);
    }

    @Test
    public void testReadDataFromFile() {
        assertEquals(3, dijkstra.node); // Assuming the test file initializes a graph with 5 nodes
        assertNotNull(dijkstra.dist);
        assertNotNull(dijkstra.visited);
        assertNotNull(dijkstra.graph);
    }

    @Test
    public void testDijkstraNaive() {
        assertEquals(3, dijkstra.solution_template(1, 3, 1));
    }

    @Test
    public void testDijkstraOptimal() {
        assertEquals(3, dijkstra.solution_template(1, 3, 2));
    }
}

