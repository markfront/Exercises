/*
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example, given [10, 9, 2, 5, 3, 7, 101, 18], the longest increasing subsequence is [2, 3, 7, 101]. Therefore the length is 4.
*/

public class LongestIncreasingSubsequence {
  public int solve(int[] nums) {
    if (nums==null || nums.length==0) return 0;
    
    int[] max = new int[nums.length]; // keep length of the longest subsequence so far
    Arrays.fill(max, 1); // each number is a length=1 subsequence by itself
    
    int result = 1; // keep the global max
    for(int i=0; i<nums.length; i++) {
      for(int j=0; j<=i; j++) {
        if (nums[j]<nums[i]) {
          max[i] = Math.max(max[i], max[j] + 1);
        }
      } // now max[i] is the length of the longest subsequence seen so far     
      result = Math.max(result, max[i]); 
    }
    return result;
  }
}
