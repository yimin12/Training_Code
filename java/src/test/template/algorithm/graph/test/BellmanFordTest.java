package src.test.template.algorithm.graph.test;

import org.junit.Before;
import org.junit.Test;
import src.template.algorithm.graph.impl.BellmanFord;

import static org.junit.Assert.*;

public class BellmanFordTest {

    private BellmanFord bellmanford;
    final String path = "src/resource/data/dummy_data1.txt";

    @Before
    public void setUp() {
        bellmanford = new BellmanFord();
        bellmanford.readDataFromFile(path);
    }

    @Test
    public void testGraphInitialization() {
        assertNotNull(bellmanford.graph_container);
    }

    @Test
    public void testReadDataFromFile() {
        assertEquals(3, bellmanford.node);
        assertNotNull(bellmanford.dist);
        assertNotNull(bellmanford.visited);
        assertNotNull(bellmanford.head);
        assertNotNull(bellmanford.edges);
        assertNotNull(bellmanford.vertex);
        assertNotNull(bellmanford.next);
    }

    @Test
    public void testSPFA() {
        assertEquals(3, bellmanford.solution_template(1, 3, 2));
    }

    @Test
    public void testBellmanFord() {
        assertEquals(3, bellmanford.solution_template(1, 3, 1));
    }
}

