package com.lithiumsakura.DSAUnit.utils.graph;

import java.util.*;

public class Graph<T> {

    private final Map<T, List<Edge<T>>> adjacencyList = new LinkedHashMap<>();

    private void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new LinkedList<>());
    }

    public void removeEdge(Edge<T> vertex) {
        adjacencyList.values().forEach(list -> list.remove(vertex));
        adjacencyList.remove(vertex.getSource());
    }

    /**
     * Add an edge to the graph
     * @param source First vertex
     * @param dest Second vertex
     */
    public void addEdge(T source, T dest) {
        if (!adjacencyList.containsKey(source)) {
            addVertex(source);
        }
        if (!adjacencyList.containsKey(dest)) {
            addVertex(dest);
        }
        Edge<T> destEdge = new Edge<>(source, dest);
        adjacencyList.get(source).add(destEdge);
    }

    public void removeEdge(T source, T dest) {
        List<Edge<T>> firstList = adjacencyList.get(source);
        List<Edge<T>> secondList = adjacencyList.get(dest);
        if (firstList != null) {
            firstList.removeIf(edge -> edge.getSource().equals(dest));
        }
        secondList.removeIf(edge -> edge.getSource().equals(source));
    }

    public List<Edge<T>> getAdjVertices(T vertex) {
        return adjacencyList.get(vertex);
    }

    public List<Edge<T>> depthFirstTraversal(T rootVertex) {

        List<Edge<T>> visited = new ArrayList<>();
        Stack<Edge<T>> stack = new Stack<>();
        List<Edge<T>> edges = getAdjVertices(rootVertex);

        for (Edge<T> edge : edges) {
            stack.push(edge);
        }

        while (!stack.isEmpty()) {
            Edge<T> edge = stack.pop();
            if (!visited.contains(edge)) {
                visited.add(edge);
                for (Edge<T> v : getAdjVertices(edge.getDestination())) {
                    stack.push(v);
                }
            }
        }
        return visited;

    }

    /**
     * Turns this graph into 2 sets of coloured graphs (red or blue). Must be even.
     * @param rootVertex Where to start
     * @return A map of nodes to their associated colours.
     */
    public Map<T, Colour> groupedDepthFirstTraversal(T rootVertex) {

        Map<T, Colour> nodeColorMap = new HashMap<>();
        // nodes that we need to process (not accounted for yet)
        Stack<T> stack = new Stack<>();

        stack.push(rootVertex);
        nodeColorMap.put(rootVertex, Colour.RED);

        while (!stack.isEmpty()) {
            T current = stack.pop();
            Colour currentColor = nodeColorMap.get(current);
            Colour oppositeColor;
            // the current opposite colour. it alternates as it goes through the loop.
            if (currentColor == Colour.RED)  {
                oppositeColor = Colour.BLUE;
            } else {
                oppositeColor = Colour.RED;
            }
            // loop through all edges associated with this node
            for (Edge<T> edge : getAdjVertices(current)) {
                // other node which connects to this node
                T neighbor = edge.getDestination();
                // is it already accounted for? if not, mark it as the current opposite colour and add it to the stack to process
                if (!nodeColorMap.containsKey(neighbor)) {
                    nodeColorMap.put(neighbor, oppositeColor);
                    stack.push(neighbor);
                } else if (nodeColorMap.get(neighbor) == currentColor) {
                    // this already has the current colour. is the size of this graph even?
                    continue;
                }
            }
        }

        return nodeColorMap;

    }

    public Set<T> getAllNodes() {
        return adjacencyList.keySet();
    }

    public int size() {
        return adjacencyList.size();
    }


}
