package com.lithiumsakura.DSAUnit;

import com.lithiumsakura.DSAUnit.utils.graph.Edge;
import com.lithiumsakura.DSAUnit.utils.graph.Graph;
import com.lithiumsakura.DSAUnit.utils.graph.GraphStringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class GraphTests {

    @Test
    void testDepthFirstTraversal() {
        Graph<String> graph = new Graph<>();
        graph.addEdge("test1", "test2");
        graph.addEdge("test2", "test3");
        graph.addEdge("test3", "test1");
        List<Edge<String>> edges = graph.depthFirstTraversal("test1");
        assertEquals("[test1, test2, test3]", GraphStringUtils.edgesToString(edges));
    }

}
