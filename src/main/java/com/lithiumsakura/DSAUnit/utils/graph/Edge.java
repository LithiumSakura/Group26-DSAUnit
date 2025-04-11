package com.lithiumsakura.DSAUnit.utils.graph;

public class Edge<T> {

    private final T vertex;
    private final int weight;

    public Edge(T vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    public T getValue() {
        return vertex;
    }

    public int getWeight() {
        return weight;
    }

}
