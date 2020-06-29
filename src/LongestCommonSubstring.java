/*
In computer science, the longest common substring problem is to find the longest string that is a substring of two or more strings.

Analysis

Given two strings a and b, let dp[i][j] be the length of the common substring ending at a[i] and b[j]. 
*/

public class LongestCommonSubstring {
  public int solve(string a, string b) {
    if (a==null || b==null || a.length()==0 || b.length()==0) return -1;
    
    int m = a.length();
    int n = b.length();
    
    int[][] dp = new int[m][n];
    
    // initialization
    for(int i=0; i<m; i++) {
      if (a.charAt(i)==b.charAt(0)) {
            dp[i][0] = 1;
        }
    }
    for(int j=0; j<m; j++) {
      if (a.charAt(0)==b.charAt(j)) {
            dp[0][j] = 1;
        }
    }
    
    // fill the dp table
    int max = 0; // keep the global max
    for(int i=1; i<m; i++)
      for(int j=1; j<n; j++) {
        if (a.charAt(i)==b.charAt(j)) {
          dp[i][j] = dp[i-1][j-1] + 1;
          
          if (dp[i][j]>max) max = dp[i][j];
        }
      }
    
    return max;    
  }
}
