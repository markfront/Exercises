/*
https://leetcode.com/problems/maximum-length-of-repeated-subarray/

Same-idea-of-Longest-Common-Substring

Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Example 1:

Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation: 
The repeated subarray with maximum length is [3, 2, 1]. 

Note:

    1 <= len(A), len(B) <= 1000
    0 <= A[i], B[i] < 100
*/

class Solution {
    public int findLength(int[] A, int[] B) {
        if(A == null||B == null) return 0;
        int m = A.length;
        int n = B.length;
        int max = 0;
        //dp[i+1][j+1] is the length of longest common subarray ending with nums[i] and nums[j]
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for(int i=1; i<=m; i++) dp[i][0] = 0;
        for(int j=1; j<=n; j++) dp[0][j] = 0;
        
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if (A[i-1] == B[j-1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    max = Math.max(max, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return max;
    }
}

