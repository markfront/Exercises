/*
https://leetcode.com/problems/binary-subarrays-with-sum/

In an array A of 0s and 1s, how many non-empty subarrays have sum S?

Example 1:

Input: A = [1,0,1,0,1], S = 2
Output: 4
Explanation: 
The 4 subarrays are bolded below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]

Note:

    A.length <= 30000
    0 <= S <= A.length
    A[i] is either 0 or 1.
*/

class Solution {
    public int numSubarraysWithSum(int[] A, int S) {
        if (A==null || A.length<S || S<0) return 0;
        
        int count=0;
        int sum = 0;
        int i=0, j=i, k; // keep sliding window [i, j]
        while(j<A.length) {
            sum += A[j];
            
            while(sum > S && i<j) {
                sum -= A[i];
                i++;
            } 
            
            if (sum == S) { // sum of elem in [i, j]
                count++;
                
                k = i;
                while(A[k]==0 && k<j) { count++; k++; }                
            }
            
            j++;
        }
        
        return count;
    }
}
