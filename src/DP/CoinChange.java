/*
Given a set of coins and a total money amount. Write a method to compute the smallest number of coins
to make up the given amount. If the amount cannot be made up by any combination of the given coins, return -1.

For example:
Given [2, 5, 10] and amount=6, the method should return -1.
Given [1, 2, 5] and amount=7, the method should return 2. 
*/
import java.util.Arrays;

public class DP_CoinChange {
  // Time complexity is O(amount * num_of_coins) and space complexity is O(amount).
  public int solve(int[] coins, int amount) {
    if (coins==null || coins.length==0 || amount<=0) return -1;
    
    int[] dp = new int[amount+1]; // dp to keep the number of coins that make the amount.
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    
    for(int a=1; a<=amount; a++) {
      for(int coin: coins) {
        if (coin == a) {
          dp[a] = 1; // just need 1 coin to make the amount
        } else {
          if (coin > a) continue;
          else {
            if (dp[a-coin] == Integer.MAX_VALUE) continue;
            else {
              dp[a] = Math.min(dp[a], dp[a-coin] + 1); // add 1 more coin to the previous amount
            }
          }
        }
      }
    }
    
    if (dp[amount]==Integer.MAX_VALUE) return -1; 
    
    return dp[amount];
  }
  
  // recursion with memory
  public int recursion(int[] coins, int amount) {
    if (coins==null || coins.length==0 || amuont<=0) return -1;
    
    int[] mem = new int[amount+1];
    Arrays.fill(mem, Integer.MAX_VALUE);
    mem[0] = 0;
    
    return helper(coins, amount, mem);
  }
  
  // return number of coins needed to make the amount a.
  public int helper(int[] coins, int a, int[] mem) {
    if (mem[a] != Integer.MAX_VALUE) return mem[a];
    
    for(int coin : coins) {
      if (coin == a) {
        mem[a] = 1;
        return 1; // use 1 coin to make the amount a
      } else {
        if (coin > a) continue; // cannot make the amount using this coin
        else {
          int r = helper(coins, a-coin, mem);
          if (r != -1) {
            mem[a] = Math.min(mem[a], r + 1);
          } else {
            continue;
          }
        }
      }
    }
    
    int result = (mem[a] == Integer.MAX_VALUE) ? -1 : mem[a];
    
    return result;
  }
}



