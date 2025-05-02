package com.lithiumsakura.DSAUnit;

import com.lithiumsakura.DSAUnit.maxcut.VNSMaxCut;
import com.lithiumsakura.DSAUnit.utils.Solution;
import com.lithiumsakura.DSAUnit.utils.graph.Colour;
import com.lithiumsakura.DSAUnit.utils.graph.Edge;
import com.lithiumsakura.DSAUnit.utils.graph.Graph;

import java.util.*;

public class MaxCut implements Solution {

    private final Graph<String> graph;

    public MaxCut() {
        this.graph = new Graph<>();
        for (int i = 1; i <= 100; i++) {
            String nodeName = "node" + i;
            for (int j = i + 1; j <= 100; j++) {
                String otherNode = "node" + j;
                graph.addEdge(nodeName, otherNode);
            }
        }
        System.out.println(graph.size());
    }

    @Override
    public void naive() {

        long startTime = System.currentTimeMillis();

        Map<String, Colour> colourMap = graph.splitGraph("node1");
        Set<String> allNodes = graph.getAllNodes();
        int maxUnique = 0;
        // loop over all nodes to vary the root node
        for (String node : allNodes) {
            int unique = 0;
            // traverse the graph
            List<Edge<String>> edges = graph.depthFirstTraversal(node);
            for (Edge<String> edge : edges) {
                Colour firstColour = colourMap.get(edge.getSource());
                Colour secondColour = colourMap.get(edge.getDestination());
                // colours don't match? that's a cut.
                if (firstColour != secondColour) {
                    unique++;
                }
            }
            System.out.printf("%s: %d\n", node, unique);
            if (unique > maxUnique) {
                maxUnique = unique;
            }
        }
        long endTime = System.currentTimeMillis();
        // number of cuts found
        System.out.println(maxUnique);
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }

    @Override
    public void chatGPT() {
        long startTime = System.currentTimeMillis();
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

        long endTime = System.currentTimeMillis();
        System.out.println(nodeColorMap.size());
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }

    @Override
    public void best() {
        long startTime = System.currentTimeMillis();
        VNSMaxCut<String> vnsMaxCut = new VNSMaxCut<>(graph, "node1");
        Map<String, Colour> run = vnsMaxCut.run(50);
        long endTime = System.currentTimeMillis();
        System.out.println(vnsMaxCut.cutAmount(run));
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }

    @Override
    public String name() {
        return "Max Cut";
    }

}


