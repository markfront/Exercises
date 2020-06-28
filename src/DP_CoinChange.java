/*
Given a set of coins and a total money amount. Write a method to compute the smallest number of coins
to make up the given amount. If the amount cannot be made up by any combination of the given coins, return -1.

For example:
Given [2, 5, 10] and amount=6, the method should return -1.
Given [1, 2, 5] and amount=7, the method should return 2. 
*/

public class DP_CoinChange {
  
  public int solve(int[] coins, int amout) {
    if (coins==null || coins.length==0 || amuont<=0) return -1;
    
    int[] dp = new int[amount+1];
    Arrays.fill(dp, Integer.MAX_VALUE;
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
              dp[a] = Math.Min(dp[a], dp[a-coin] + 1); // add 1 more coin to the previous amount
            }
          }
        }
      }
    }
    
    if (dp[amount]==Integer.MAX_VALUE) return -1; 
    
    return dp[amount];
  }
}

// Time complexity is O(amount * num_of_coins) and space complexity is O(amount).
