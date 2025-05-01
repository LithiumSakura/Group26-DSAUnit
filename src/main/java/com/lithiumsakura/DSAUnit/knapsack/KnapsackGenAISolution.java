package com.lithiumsakura.DSAUnit.knapsack;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution to the 0-1 Knapsack Problem
 *
 * This class implements the classic 0-1 knapsack problem using dynamic programming.
 * It finds the maximum value subset of items that fits within a given weight capacity.
 */
public class KnapsackGenAISolution {

    /**
     * Represents an item with a weight and a value
     */
    static class Item {
        String name;
        int weight;
        int value;

        public Item(String name, int weight, int value) {
            this.name = name;
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return name + " (weight: " + weight + ", value: " + value + ")";
        }
    }

    /**
     * Represents the solution to the knapsack problem
     */
    static class KnapsackSolution {
        int maxValue;
        List<Item> selectedItems;
        int totalWeight;

        public KnapsackSolution(int maxValue, List<Item> selectedItems, int totalWeight) {
            this.maxValue = maxValue;
            this.selectedItems = selectedItems;
            this.totalWeight = totalWeight;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Maximum value: ").append(maxValue).append("\n");
            sb.append("Total weight: ").append(totalWeight).append("\n");
            sb.append("Selected items:\n");
            for (Item item : selectedItems) {
                sb.append("- ").append(item).append("\n");
            }
            return sb.toString();
        }
    }

    /**
     * Solves the 0-1 knapsack problem using dynamic programming
     *
     * @param items List of available items
     * @param capacity Maximum weight capacity
     * @return KnapsackSolution containing the maximum value and selected items
     */
    public static KnapsackSolution solve(List<Item> items, int capacity) {
        int n = items.size();

        // Create a DP table where dp[i][w] represents the maximum value
        // that can be obtained using the first i items and with a weight capacity of w
        int[][] dp = new int[n + 1][capacity + 1];

        // Fill the DP table
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else {
                    // Current item details
                    Item currentItem = items.get(i - 1);

                    // If current item's weight is more than capacity, skip it
                    if (currentItem.weight > w) {
                        dp[i][w] = dp[i - 1][w];
                    } else {
                        // Max of two cases:
                        // 1. Current item is not included
                        // 2. Current item is included
                        dp[i][w] = Math.max(
                                dp[i - 1][w],
                                dp[i - 1][w - currentItem.weight] + currentItem.value
                        );
                    }
                }
            }
        }

        // Backtrack to find the selected items
        List<Item> selectedItems = new ArrayList<>();
        int remainingWeight = capacity;
        int maxValue = dp[n][capacity];

        for (int i = n; i > 0 && maxValue > 0; i--) {
            // If this item is included
            if (maxValue != dp[i - 1][remainingWeight]) {
                Item selectedItem = items.get(i - 1);
                selectedItems.add(selectedItem);
                maxValue -= selectedItem.value;
                remainingWeight -= selectedItem.weight;
            }
        }

        // Calculate total weight of selected items
        int totalWeight = selectedItems.stream().mapToInt(item -> item.weight).sum();

        return new KnapsackSolution(dp[n][capacity], selectedItems, totalWeight);
    }

    public static void run() {
        // Example usage
        List<Item> items = new ArrayList<>();
        items.add(new Item("Laptop", 3, 10));
        items.add(new Item("Camera", 2, 5));
        items.add(new Item("Smartphone", 1, 8));
        items.add(new Item("Headphones", 1, 3));
        items.add(new Item("Book", 2, 4));
        items.add(new Item("Tablet", 2, 7));

        int capacity = 5;

        KnapsackSolution solution = solve(items, capacity);
        System.out.println(solution);

        // Performance testing with larger datasets
        performanceTest();
    }

    /**
     * Tests the performance of the knapsack algorithm with a larger dataset
     */
    private static void performanceTest() {
        System.out.println("\n=== Performance Test ===");

        // Create a larger dataset
        List<Item> largeItemSet = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            largeItemSet.add(new Item("Item-" + i, 1 + i % 10, 3 + i % 15));
        }

        int largeCapacity = 50;

        // Measure execution time
        long startTime = System.nanoTime();
        KnapsackSolution solution = solve(largeItemSet, largeCapacity);
        long endTime = System.nanoTime();

        long nanosElasped = endTime - startTime;
        System.out.println("Solution for 30 items with capacity 50:");
        System.out.println("Maximum value: " + solution.maxValue);
        System.out.println("Total weight: " + solution.totalWeight);
        System.out.println("Number of selected items: " + solution.selectedItems.size());
        System.out.println("Execution time: " + (nanosElasped) + "ns");
    }
}