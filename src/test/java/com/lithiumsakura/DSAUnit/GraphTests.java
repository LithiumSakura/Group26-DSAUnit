package com.lithiumsakura.DSAUnit;

import com.lithiumsakura.DSAUnit.utils.graph.Edge;
import com.lithiumsakura.DSAUnit.utils.graph.Graph;
import com.lithiumsakura.DSAUnit.utils.graph.GraphStringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

public class GraphTests {

    private final Graph<String> graph = new Graph<>();

    @Test
    void testDepthFirstTraversal() {
        graph.addEdge("test1", "test2", 5);
        graph.addEdge("test2", "test3", 7);
        graph.addEdge("test3", "test1", 9);
        Set<Edge<String>> edges = graph.depthFirstTraversal("test1");
        assertEquals("[test1, test2, test3]", GraphStringUtils.edgesToString(edges));
    }

}
