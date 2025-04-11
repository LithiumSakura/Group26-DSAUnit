package com.lithiumsakura.DSAUnit.utils.graph;

public class Edge<T> {

    private final T source;
    private final T destination;
    private final int weight;

    public Edge(T source, T destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public T getSource() {
        return source;
    }

    public T getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

}
