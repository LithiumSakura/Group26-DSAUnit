package com.lithiumsakura.DSAUnit;

import com.lithiumsakura.DSAUnit.utils.Solution;
import com.lithiumsakura.DSAUnit.utils.graph.Colour;
import com.lithiumsakura.DSAUnit.utils.graph.Edge;
import com.lithiumsakura.DSAUnit.utils.graph.Graph;

import java.util.*;

public class MaxCut implements Solution {

    private final Graph<String> graph;

    public MaxCut() {
        this.graph = new Graph<>();
        graph.addEdge("test1", "test2");
        graph.addEdge("test2", "test3");
        graph.addEdge("test3", "test5");
        graph.addEdge("test5", "test4");
        graph.addEdge("test3", "test4");
        graph.addEdge("test5", "test6");
        /*graph.addEdge("test1", "test4");
        graph.addEdge("test1", "test3");
        graph.addEdge("test2", "test6");
        graph.addEdge("test3", "test4");
        graph.addEdge("test3", "test5");
        graph.addEdge("test5", "test6");*/

    }

    @Override
    public void naive() {
        Map<String, Colour> colourMap = graph.splitGraph("test1");
        System.out.println(colourMap);
        Set<String> allNodes = graph.getAllNodes();

        int maxUnique = 0;
        for (String node : allNodes) {
            int unique = 0;
            List<Edge<String>> edges = graph.depthFirstTraversal(node);
            for (Edge<String> edge : edges) {
                Colour firstColour = colourMap.get(edge.getSource());
                Colour secondColour = colourMap.get(edge.getDestination());
                if (firstColour != secondColour) {
                    unique++;
                }
            }
            System.out.printf("%s: %d\n", node, unique);
            if (unique > maxUnique) {
                maxUnique = unique;
            }
        }
        System.out.println(maxUnique);
    }

    @Override
    public void chatGPT() {

    }

    @Override
    public void best() {

    }
}


