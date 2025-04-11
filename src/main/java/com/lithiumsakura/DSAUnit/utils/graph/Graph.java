package com.lithiumsakura.DSAUnit.utils.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T> {

    private final Map<T, List<Edge<T>>> adjacencyList = new HashMap<>();

    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void removeVertex(Edge<T> vertex) {
        adjacencyList.values().forEach(list -> list.remove(vertex));
        adjacencyList.remove(vertex.getValue());
    }

    public void addEdge(T source, T dest, int weight) {
        if (!adjacencyList.containsKey(source)) {
            addVertex(source);
        }
        if (!adjacencyList.containsKey(dest)) {
            addVertex(dest);
        }
        adjacencyList.get(source).add(new Edge<>(dest, weight));
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

    public int size() {
        return adjacencyList.size();
    }


}
