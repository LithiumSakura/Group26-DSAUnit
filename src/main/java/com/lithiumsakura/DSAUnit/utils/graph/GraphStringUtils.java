package com.lithiumsakura.DSAUnit.utils.graph;

import java.util.Set;

public class GraphStringUtils {
    public static <T> String edgesToString(Set<Edge<T>> edges) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        boolean first = true;
        for (Edge<?> edge : edges) {
            if (!first) {
                builder.append(", ");
            }
            builder.append(edge.getSource());
            first = false;
        }
        builder.append("]");
        return builder.toString();
    }

}
