package com.lithiumsakura.DSAUnit;

import com.lithiumsakura.DSAUnit.utils.graph.Colour;
import com.lithiumsakura.DSAUnit.utils.graph.Edge;
import com.lithiumsakura.DSAUnit.utils.graph.Graph;

import java.util.*;

public class MaxCut {

    private final Graph<String> graph;

    public MaxCut() {
        this.graph = new Graph<>();
        graph.addEdge("test1", "test2");
        graph.addEdge("test2", "test3");
        graph.addEdge("test3", "test5");
        graph.addEdge("test5", "test4");
        graph.addEdge("test3", "test4");
        graph.addEdge("test4", "test6");
    }


    public void run() {

        List<String> blueNodes = new ArrayList<>();
        List<String> redNodes = new ArrayList<>();

        Map<String, Colour> colourMap = graph.groupedDepthFirstTraversal("test1");
        System.out.println(colourMap);

        for (Map.Entry<String, Colour> entry : colourMap.entrySet()) {
            if (entry.getValue() == Colour.BLUE) {
                blueNodes.add(entry.getKey());
            } else {
                redNodes.add(entry.getKey());
            }
        }

    }

}
