/*
 * 
There is a row of N houses, where each house can be painted one of 3 colors: red, blue, or green. The cost of painting each house with a specific color is different. You have to paint all the houses such that no two adjacent houses have the same color. The cost of painting each house with a certain color is represented by a N x 3 cost matrix. The task is to find the minimum cost to paint all houses.

Example:

Input: costs = [[17,2,17], [16,16,5], [14,3,19]]
Output: 10
Explanation: min cost = 2 + 5 + 3 = 10

Input: costs = []
Output: 0

*/

import java.util.*;

public class PaintHouse {
    public static int solveByDP(int[][] costs) {
        int n = costs.length;
        if (n == 0) {
            return 0;
        }
    
        int[][] dp = new int[n][3]; //keep the costs of each house on the 3 colors
        dp[0] = costs[0]; // costs of house 0
    
        for (int i = 1; i < n; i++) {
            // house i color must be different from house i-1
            dp[i][0] = costs[i][0] + Math.min(dp[i-1][1], dp[i-1][2]); // the i-th house's cost on red color
            dp[i][1] = costs[i][1] + Math.min(dp[i-1][0], dp[i-1][2]); // the i-th house's cost on blue color
            dp[i][2] = costs[i][2] + Math.min(dp[i-1][0], dp[i-1][1]); // the i-th house's cost on green color
        }
    
        return Math.min(
            Math.min(
                dp[n-1][0], 
                dp[n-1][1]
            ), 
            dp[n-1][2]
        );
    }

    public static int recursionWithCache(int[][] costs) {
        int n = costs.length;
        if (n == 0) {
            return 0;
        }
        // Initialize a cache array to store previously computed results
        int[][] cache = new int[n][3];
        for (int[] row : cache) {
            Arrays.fill(row, -1);
        }
        // Call the recursive function for each color and return the minimum cost
        return Math.min(
            paintCost(costs, n - 1, 0, cache), 
            Math.min(
                paintCost(costs, n - 1, 1, cache), 
                paintCost(costs, n - 1, 2, cache)
            )
        );
    }
    
    private static int paintCost(int[][] costs, int n, int color, int[][] cache) {
        // Base case: if we have reached the first house, return the cost of painting it with the given color
        if (n == 0) {
            return costs[0][color];
        }
        // Check if we have already computed the result for this index and color before
        if (cache[n][color] != -1) {
            return cache[n][color];
        }
        // Compute the minimum cost of painting the current house with the given color, using the results of the previous house
        int minCost = Integer.MAX_VALUE;
        if (color == 0) {
            minCost = costs[n][0] + Math.min(paintCost(costs, n - 1, 1, cache), paintCost(costs, n - 1, 2, cache));
        }
        if (color == 1) {
            minCost = costs[n][1] + Math.min(paintCost(costs, n - 1, 0, cache), paintCost(costs, n - 1, 2, cache));
        }
        if (color == 2) {
            minCost = costs[n][2] + Math.min(paintCost(costs, n - 1, 0, cache), paintCost(costs, n - 1, 1, cache));
        }
        // Store the result in the cache before returning it
        cache[n][color] = minCost;
        return minCost;
    }
}
