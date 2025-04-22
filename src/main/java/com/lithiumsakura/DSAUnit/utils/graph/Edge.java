package com.lithiumsakura.DSAUnit.utils.graph;

public class Edge<T> {

    private final T source;
    private final T destination;

    public Edge(T source, T destination) {
        this.source = source;
        this.destination = destination;
    }

    public T getSource() {
        return source;
    }

    public T getDestination() {
        return destination;
    }

}
