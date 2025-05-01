package com.lithiumsakura.DSAUnit;

import com.lithiumsakura.DSAUnit.knapsack.KnapsackGenAISolution;
import com.lithiumsakura.DSAUnit.knapsack.KnapsackImproved;
import com.lithiumsakura.DSAUnit.utils.Solution;

public class Knapsack implements Solution {

    private final int[] weights = {10, 5, 8, 3, 4, 7};
    private final int[] values = {3, 2, 1, 1, 2, 2};

    private int knapsack_brute_force(int capacity, int n) {
        System.out.println("knapsack_brute_force(" + capacity + "," + n + ")");
        if (n == 0 || capacity == 0)
            return 0;
        if (weights[n - 1] > capacity)
            return knapsack_brute_force(capacity, n - 1);
        else {
            int include_item = values[n - 1] + knapsack_brute_force(capacity - weights[n - 1], n - 1);
            int exclude_item = knapsack_brute_force(capacity, n - 1);
            return Math.max(include_item, exclude_item);
        }
    }

    @Override
    public void naive() {
        int n = values.length;
        int capacity = 10;
        System.out.println("\nMaximum value in Knapsack = " + knapsack_brute_force(capacity, n));
    }

    @Override
    public void chatGPT() {
        KnapsackGenAISolution.run();
    }

    @Override
    public void best() {
        KnapsackImproved.run();
    }

    @Override
    public String name() {
        return "Knapsack";
    }

}
