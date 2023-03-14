/*
 * https://app.codesignal.com/interview-practice/question/rMe9ypPJkXgk3MHhZ/description
 * 
You have a collection of coins, and you know the values of the coins and the quantity of each type of coin in it. You want to know how many distinct sums you can make from non-empty groupings of these coins.

Example

For coins = [10, 50, 100] and quantity = [1, 2, 1], the output should be solution(coins, quantity) = 9.

Here are all the possible sums:

    50 = 50;
    10 + 50 = 60;
    50 + 100 = 150;
    10 + 50 + 100 = 160;
    50 + 50 = 100;
    10 + 50 + 50 = 110;
    50 + 50 + 100 = 200;
    10 + 50 + 50 + 100 = 210;
    10 = 10;
    100 = 100;
    10 + 100 = 110.

As you can see, there are 9 distinct sums that can be created from non-empty groupings of your coins.

Input/Output

    [execution time limit] 3 seconds (java)

    [input] array.integer coins

    An array containing the values of the coins in your collection.

    Guaranteed constraints:
    1 ≤ coins.length ≤ 20,
    1 ≤ coins[i] ≤ 104.

    [input] array.integer quantity

    An array containing the quantity of each type of coin in your collection. quantity[i] indicates the number of coins that have a value of coins[i].

    Guaranteed constraints:
    quantity.length = coins.length,
    1 ≤ quantity[i] ≤ 105,
    (quantity[0] + 1) * (quantity[1] + 1) * ... * (quantity[quantity.length - 1] + 1) <= 106.

    [output] integer

    The number of different possible sums that can be created from non-empty groupings of your coins. 
 */

 import java.util.*;

public class CombinationsPossibleSums {
    public int solution(int[] coins, int[] quantity) {
        Set<Integer> uniqSums = new HashSet<>();
        uniqSums.add(0);    //so we will always add the coin itself
        
        for (int i = 0; i < coins.length; i++) {
            List<Integer> newSums = new ArrayList<>();
            
            for (int existingSum : uniqSums) {
                for (int j = 1; j <= quantity[i]; j++) {
                    newSums.add(existingSum + (coins[i] * j));
                }
            }
            
            uniqSums.addAll(newSums);
        }
        
        return uniqSums.size() - 1;    //don't count 0
    }
    
    
    public int solution2(int[] coins, int[] quantity) {
        Set<Long> uniqSums = new HashSet<>();
        
        int n = coins.length;
        for(int k=1; k<=n; k++) {
            // select k out of the n types of coins
            List<List<Integer>> allGroups = new ArrayList<>();
            List<Integer> oneGroup = new ArrayList<>();
            dfsHelper(coins, quantity, n, k, 0, oneGroup, allGroups, uniqSums);
        }
        
        return uniqSums.size();
    }
    
    void dfsHelper(int[] coins, int[] quantity, int n, int k, int start, List<Integer> oneGroup, List<List<Integer>> allGroups, Set<Long> uniqSums) {
        if (oneGroup.size() == k) {
            List<Integer> coinList = new ArrayList<>();
            
            long sum=0;
            for(int i : oneGroup) {
                sum += coins[i];
                coinList.add(coins[i]);
            }
    
            allGroups.add(coinList);
            
            if (!uniqSums.contains(sum)) {
                uniqSums.add(sum);
            }

            int[] startIdx = new int[coins.length];
            for(int i=0; i<coins.length; i++) startIdx[i] = -1; // not selected
            for(int idx : oneGroup) startIdx[idx] = quantity[idx]; 
            
            List<List<Integer>> coinIdToSum = new ArrayList<>();
            sumHelper(coins, quantity, startIdx, coinIdToSum);
        }
        
        for(int i=start; i<n; i++) {
            oneGroup.add(i);
            dfsHelper(coins, quantity, n, k, i+1, oneGroup, allGroups, uniqSums);
            oneGroup.remove(oneGroup.size()-1);
        }
    }
    
    void sumHelper(int[] coins, int[] quantity, int[] starts, List<List<Integer>> coinIdToSum) {

    }
    
    long computeSum(int[] coins, List<Integer> coinIds) {
        long sum = 0;
        for(int id : coinIds)
            sum += coins[id];
        return sum;
    }
}