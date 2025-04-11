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
     * @param weight Weight
     */
    public void addEdge(T source, T dest, int weight) {
        if (!adjacencyList.containsKey(source)) {
            addVertex(source);
        }
        if (!adjacencyList.containsKey(dest)) {
            addVertex(dest);
        }
        Edge<T> destEdge = new Edge<>(source, dest, weight);
        adjacencyList.get(source).add(destEdge);
    }

    public void removeEdge(T source, T dest) {
        List<Edge<T>> firstList = adjacencyList.get(source);
        List<Edge<T>> secondList = adjacencyList.get(dest);
        if (firstList != null) {
            firstList.removeIf(edge -> edge.getSource().equals(dest));
        }
        if (secondList != null) {
            secondList.removeIf(edge -> edge.getSource().equals(source));
        }
    }

    public List<Edge<T>> getAdjVertices(T vertex) {
        return adjacencyList.get(vertex);
    }

    public Set<Edge<T>> depthFirstTraversal(T rootVertex) {

        Set<Edge<T>> visited = new LinkedHashSet<>();
        Stack<Edge<T>> stack = new Stack<>();
        List<Edge<T>> edges = getAdjVertices(rootVertex);

        for (Edge<T> edge : edges) {
            stack.push(edge); // Push the first edges from root vertex
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

    public int size() {
        return adjacencyList.size();
    }


}
