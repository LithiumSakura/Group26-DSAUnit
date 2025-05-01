package com.lithiumsakura.DSAUnit;

import com.lithiumsakura.DSAUnit.utils.Solution;

import java.util.List;

public class Main {

    private final static List<Solution> solutions = List.of(
            new MaxCut(),
            new Knapsack()
    );

    public static void main(String[] args) {

        System.out.println("=== Group 26 DSA ===");
        for (Solution solution : solutions) {
            System.out.printf("* Running test: %s%n", solution.name());
            System.out.println("** Naive/Brute Force solution:");
            solution.naive();
            System.out.println();
            System.out.println("** Generative AI solution:");
            solution.chatGPT();
            System.out.println();
            System.out.println("** Best solution:");
            solution.best();
        }


    }
}
