/*
In computer science, the longest common substring problem is to find the longest string that is a substring of two or more strings.

Analysis

Given two strings a and b, let dp[i][j] be the length of the common substring ending at a[i] and b[j]. 
*/

public class Longest_Common_Substring {
  public static String solve(String a, String b) {
    if (a == null || b == null || a.length() == 0 || b.length() == 0)
      return "";

    int m = a.length();
    int n = b.length();

    // initialization:
    int[][] dp = new int[m + 1][n + 1];
    for (int i = 0; i <= m; i++)
      for (int j = 0; j <= n; j++)
        dp[i][j] = 0;

    // // corner case: when char 0 in one string matches a char in the other string,
    // // the length of common substring is 1.
    // for (int i = 0; i < m; i++) {
    //   if (a.charAt(i) == b.charAt(0)) {
    //     dp[i][0] = 1;
    //   }
    // }
    // for (int j = 0; j < n; j++) {
    //   if (a.charAt(0) == b.charAt(j)) {
    //     dp[0][j] = 1;
    //   }
    // }

    // fill the dp table
    int maxLen = 0; // keep the max length of the common substring
    int endIdx = 0; // the index where the common substring ends
    for (int i = 1; i <= m; i++)
      for (int j = 1; j <= n; j++) {
        if (a.charAt(i-1) == b.charAt(j-1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;

          if (dp[i][j] > maxLen) {
            maxLen = dp[i][j];
            endIdx = i;
          }
        }
      }

    return a.substring(endIdx - maxLen, endIdx);
  }

  public static void main(String[] args) {
    String str1 = "doublechecked";
    String str2 = "wobleched";

    String common = solve(str1, str2);
    System.out.println("str1  = " + str1);
    System.out.println("str2  = " + str2);
    System.out.println("comon = " + common);
  }
}
