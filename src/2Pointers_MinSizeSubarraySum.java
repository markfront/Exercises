/*
Given an array of n positive integers and a positive integer s, find the minimal length 
of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] 
has the minimal length of 2 under the problem constraint.
*/

public class Question {
  public int solve(int[] a, int s) {
    if (a==null || a.length==0) return 0;
    
    int min = Integer.MAX_VALUE;
    int sum=0, i=0, j=0; // left/right pointer
    while(i<=j && j<a.length) {
      if (sum<s) {
        sum += a[j]; 
        j++;
      } else { // sum>=s
        min = Math.min(min, j-i); // keep the min length seen so far
        if (min==1) return 1; // this is the global min, no need to check further
        sum -= a[i];
        i++;
      }
    }
    // handle when j reaches end of array
    while (sum>=s) {
      min = Math.min(min, j-i);
      sum -= a[i++]; 
    }
    return min;
  }  
}
