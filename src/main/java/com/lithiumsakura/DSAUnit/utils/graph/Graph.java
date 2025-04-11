package com.lithiumsakura.DSAUnit.utils.graph;

import java.util.*;

public class Graph<T> {

    private final Map<T, List<Edge<T>>> adjacencyList = new HashMap<>();

    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void removeVertex(Edge<T> vertex) {
        adjacencyList.values().forEach(list -> list.remove(vertex));
        adjacencyList.remove(vertex.getValue());
    }

    public Edge<T> addEdge(T source, T dest, int weight) {
        if (!adjacencyList.containsKey(source)) {
            addVertex(source);
        }
        if (!adjacencyList.containsKey(dest)) {
            addVertex(dest);
        }
        Edge<T> edge = new Edge<>(dest, weight);
        adjacencyList.get(source).add(edge);
        return edge;
    }

    public void removeEdge(T source, T dest) {
        List<Edge<T>> firstList = adjacencyList.get(source);
        List<Edge<T>> secondList = adjacencyList.get(dest);
        if (firstList != null) {
            firstList.removeIf(edge -> edge.getValue().equals(dest));
        }
        if (secondList != null) {
            secondList.removeIf(edge -> edge.getValue().equals(source));
        }
    }

    public List<Edge<T>> getAdjVertices(T vertex) {
        return adjacencyList.get(vertex);
    }

    public Set<Edge<T>> depthFirstTraversal(Edge<T> rootEdge) {
        Set<Edge<T>> visited = new HashSet<>();
        Stack<Edge<T>> stack = new Stack<>();
        stack.push(rootEdge);
        while (!stack.isEmpty()) {
            Edge<T> edge = stack.pop();
            if (!visited.contains(edge)) {
                visited.add(edge);
                for (Edge<T> v : getAdjVertices(edge.getValue())) {
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
