/*
https://leetcode.com/problems/target-sum/

You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. 
Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:

Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3. 

Constraints:

    The length of the given array is positive and will not exceed 20.
    The sum of elements in the given array will not exceed 1000.
    Your output answer is guaranteed to be fitted in a 32-bit integer.
*/

class Solution {
    public int findTargetSumWays(int[] nums, int S) {        
        
        int n = nums.length;
        
        int ways = dfs(nums, 0, S, 1, n) + dfs(nums, 0, S, -1, n);
        
        return ways;
    }
    
    private int dfs(int[] nums, int start, int S, int sign, int n) {
        if (start<0||start>=n) return 0;
        
        int x = nums[start] * sign;
        
        if (start==n-1 && x==S) return 1;
        
        return dfs(nums, start+1, S-x, 1, n) + dfs(nums, start+1, S-x, -1, n);
    }
}

// recursion with memory to avoid duplicate computation

class Solution {
    public int findTargetSumWays(int[] nums, int S) {        
        
        int n = nums.length;
        
        Map<String,Integer> map = new HashMap<>();
        
        int ways = dfs(nums, 0, S, 1, n, map) + dfs(nums, 0, S, -1, n, map);
        
        return ways;
    }
    
    private int dfs(int[] nums, int start, int S, int sign, int n, Map<String,Integer> map) {
        String key=start+"*"+sign+"->"+S;
        
        if (map.containsKey(key)) return map.get(key);
        
        if (start<0||start>=n) {
            map.put(key, 0);
            return 0;
        }
        
        int x = nums[start] * sign;
        
        if (start==n-1 && x==S) {
            map.put(key, 1);
            return 1;
        }
        
        int ways = dfs(nums, start+1, S-x, 1, n, map) + dfs(nums, start+1, S-x, -1, n, map);
        map.put(key, ways);
        
        return ways;
    }
}
