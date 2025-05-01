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
        // Step 1: Initialize two sets A and B (colouring red and blue)
        Map<String, Colour> nodeColorMap = new HashMap<>();
        Set<String> setA = new HashSet<>();
        Set<String> setB = new HashSet<>();

        // Step 2: Randomly assign vertices to set A and set B
        Random rand = new Random();
        for (String node : graph.getAllNodes()) {
            Colour color = rand.nextBoolean() ? Colour.RED : Colour.BLUE;
            nodeColorMap.put(node, color);
            if (color == Colour.RED) {
                setA.add(node);
            } else {
                setB.add(node);
            }
        }

        // Step 3: Perform greedy refinement of the partition
        boolean improvementMade;
        do {
            improvementMade = false;
            // Iterate over all nodes in the graph
            for (String node : graph.getAllNodes()) {
                Set<String> currentSet = nodeColorMap.get(node) == Colour.RED ? setA : setB;
                Set<String> oppositeSet = nodeColorMap.get(node) == Colour.RED ? setB : setA;

                // Count the edges between currentSet and oppositeSet
                int currentCutWeight = 0;
                int proposedCutWeight = 0;

                // For each adjacent node (neighbor), check if it's in the opposite set
                for (Edge<String> edge : graph.getAdjVertices(node)) {
                    String neighbor = edge.getDestination();
                    if (currentSet.contains(neighbor)) {
                        currentCutWeight++;
                    } else {
                        proposedCutWeight++;
                    }
                }

                // Move node to the opposite set if it increases the cut size
                if (proposedCutWeight > currentCutWeight) {
                    improvementMade = true;
                    currentSet.remove(node);
                    oppositeSet.add(node);
                    nodeColorMap.put(node, nodeColorMap.get(node) == Colour.RED ? Colour.BLUE : Colour.RED);
                }
            }
        } while (improvementMade);

        System.out.println(nodeColorMap.size());
    }

    @Override
    public void best() {

    }

    @Override
    public String name() {
        return "Max Cut";
    }

}


