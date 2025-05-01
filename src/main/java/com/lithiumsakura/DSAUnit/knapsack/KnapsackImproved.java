package com.lithiumsakura.DSAUnit.knapsack;

public class KnapsackImproved {

    public static int[] knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;

        // Handle edge cases
        if (n == 0 || capacity == 0) {
            return new int[]{0};
        }

        // Create DP table: dp[i][w] represents the maximum value that can be obtained
        // using the first i items and with a maximum capacity of w
        int[][] dp = new int[n + 1][capacity + 1];

        // Fill the dp table
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i-1] <= w) {
                    // We have two choices: include the item or exclude it
                    dp[i][w] = Math.max(values[i-1] + dp[i-1][w-weights[i-1]], dp[i-1][w]);
                } else {
                    // Item is too heavy, can't include it
                    dp[i][w] = dp[i-1][w];
                }
            }
        }

        // Backtrack to find which items were selected
        java.util.List<Integer> selectedItems = new java.util.ArrayList<>();
        int w = capacity;
        for (int i = n; i > 0; i--) {
            // Check if the item was included in the optimal solution
            if (dp[i][w] != dp[i-1][w]) {
                selectedItems.add(i-1);
                w -= weights[i-1];
            }
        }

        // Convert to array in original order
        java.util.Collections.reverse(selectedItems);
        int[] result = new int[selectedItems.size() + 1];
        result[0] = dp[n][capacity]; // First element is the maximum value

        // The rest are the selected item indices
        for (int i = 0; i < selectedItems.size(); i++) {
            result[i + 1] = selectedItems.get(i);
        }

        return result;
    }

    /**
     * Helper function to display results in a readable format
     */
    public static void displayResults(int[] values, int[] weights, int capacity, int[] result) {
        System.out.println("Knapsack Problem Results:");
        System.out.println("-----------------------");
        System.out.println("Items available: " + weights.length);
        System.out.println("Knapsack capacity: " + capacity);
        System.out.println("Maximum value achieved: " + result[0]);

        System.out.print("Selected items (indices): [");
        int totalWeight = 0;
        int totalValue = 0;

        for (int i = 1; i < result.length; i++) {
            int index = result[i];
            if (i > 1) System.out.print(", ");
            System.out.print(index);
            totalWeight += weights[index];
            totalValue += values[index];
        }
        System.out.println("]");

        System.out.print("Selected item weights: [");
        for (int i = 1; i < result.length; i++) {
            if (i > 1) System.out.print(", ");
            System.out.print(weights[result[i]]);
        }
        System.out.println("]");

        System.out.print("Selected item values: [");
        for (int i = 1; i < result.length; i++) {
            if (i > 1) System.out.print(", ");
            System.out.print(values[result[i]]);
        }
        System.out.println("]");

        System.out.println("Total weight used: " + totalWeight);
        System.out.println();
    }

    // Main method to run all test cases
    public static void run() {
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;

        long startTime = System.nanoTime();
        int[] result = knapsack(weights, values, capacity);
        long endTime = System.nanoTime();
        long nanosElasped = endTime - startTime;
        displayResults(values, weights, capacity, result);
        System.out.println("Execution time: " + (nanosElasped) + "ns");
    }

}