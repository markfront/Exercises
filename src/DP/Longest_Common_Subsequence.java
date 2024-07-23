/*
https://www.programcreek.com/2014/04/longest-common-subsequence-java/

The longest common subsequence (LCS) problem is the problem of finding the longest subsequence (not necessarily contiguous) common to all sequences in a set of sequences (often just two sequences).

Analysis

Let dp[i+1][j+1] be the length of the longest common subsequence of string a & b, 
when a[i] and b[j] are compared to each other. (0<=i<=a.length-1, 0<=j<=b.length-1) 
*/

public class Longest_Common_Subsequence {
    
    // Function to find the longest common subsequence
    public static String longestCommonSubsequence(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();

		// it's necessary to initialize the dp array. In Java, when you declare an array without explicitly 
		// initializing its elements, all elements are initialized to their default values. For integers, 
		// the default value is 0, which works well for initializing the dp array because the logic of 
		// dynamic programming relies on starting with some base cases (usually zeros) and then filling 
		// in the values iteratively.
        int[][] dp = new int[m + 1][n + 1];
        
        // Build the dp array
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1; // Increment length by 1
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // Take the maximum of the left and top cell
                }
            }
        }
        
        // Extract the longest common subsequence
        StringBuilder sb = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                //sb.insert(0, s1.charAt(i - 1)); // Insert character at the beginning of the StringBuilder
				sb.append(s1.charAt(i-1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        
        return sb.reverse().toString();
    }
    
    // Main method for testing
    public static void main(String[] args) {
        String s1 = "ab12cdexyz";
        String s2 = "xyzabc3d4e";
        System.out.println("Longest Common Subsequence: " + longestCommonSubsequence(s1, s2));
    }
}
