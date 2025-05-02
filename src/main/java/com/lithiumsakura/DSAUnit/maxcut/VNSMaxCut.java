package com.lithiumsakura.DSAUnit.maxcut;

import com.lithiumsakura.DSAUnit.utils.graph.Colour;
import com.lithiumsakura.DSAUnit.utils.graph.Edge;
import com.lithiumsakura.DSAUnit.utils.graph.Graph;

import java.util.*;

public class VNSMaxCut<T> {

    private Map<T, Colour> splitGraph;
    private final List<Edge<T>> edges;

    public VNSMaxCut(Graph<T> graph, T rootNode) {
        this.splitGraph = graph.splitGraph(rootNode);
        this.edges = graph.depthFirstTraversal(rootNode);
    }

    public Map<T, Colour> run(int maxNeighbourHoods) {
        int neighbourhood = 1;

        while (neighbourhood <= maxNeighbourHoods) {
            // pick a random amount of neighbors
            // to explain, the graph is split into two sets (red and blue, in this case)
            // a 'neighbor' is basically taking one of the blue nodes and putting it in the red set.
            // a nth neighbour would be doing this n times.
            List<Map.Entry<T, Colour>> neighbors = randomNeighbor(splitGraph, neighbourhood);

            // flip every single node to the other set and see if it makes any difference
            Map<T, Colour> newSolution = localSearch(neighbors);

            // if it's better, yay!
            if (cutAmount(newSolution) > cutAmount(splitGraph)) {
                splitGraph = new HashMap<>();
                splitGraph.putAll(newSolution);
            } else {
                neighbourhood++;
            }
        }
        return splitGraph;
    }

    /**
     * Shuffles the graph and returns random neighbours.
     * @param splitGraph graph split into two sets
     * @param k number of sampled neighbours
     * @return entry set of the shuffled map.
     */
    private List<Map.Entry<T, Colour>> randomNeighbor(Map<T, Colour> splitGraph, int k) {
        List<Map.Entry<T, Colour>> neighbors = new ArrayList<>();
        List<Map.Entry<T, Colour>> graphEntries = new ArrayList<>(splitGraph.entrySet());

        // shuffle the graph. since it's comprised of two sets, this works.
        Collections.shuffle(graphEntries);

        for (int i = 0; i < k; i++) {
            neighbors.add(graphEntries.get(i));
        }
        return neighbors;
    }

    private Map<T, Colour> localSearch(List<Map.Entry<T, Colour>> graphSubSet) {
        Map<T, Colour> splitCopy = new HashMap<>(Map.copyOf(splitGraph));

        boolean improvementFound = true;
        while (improvementFound) {
            improvementFound = false;

            for (Map.Entry<T, Colour> entry : graphSubSet) {
                Colour currentColor = entry.getValue();
                Colour oppositeColor = currentColor == Colour.RED ? Colour.BLUE : Colour.RED;
                // flip the node color
                splitCopy.put(entry.getKey(), oppositeColor);
            }
        }
        return splitCopy;
    }

    // see the naive solution to understand this
    // we need the edges so we can compare two nodes correctly.
    public int cutAmount(Map<T, Colour> graph) {
        int cutAmount = 0;
        for (Edge<T> edge : edges) {
            T source = edge.getSource();
            T destination = edge.getDestination();
            Colour first = graph.get(source);
            Colour second = graph.get(destination);
            if (!first.equals(second)) {
                cutAmount += 1;
            }
        }
        return cutAmount;
    }
}
