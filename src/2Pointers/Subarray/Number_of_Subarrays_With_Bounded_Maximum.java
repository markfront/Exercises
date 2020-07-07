/*
https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/

We are given an array A of positive integers, and two positive integers L and R (L <= R).

Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that subarray is at least L and at most R.

Example :
Input: 
A = [2, 1, 4, 3]
L = 2
R = 3
Output: 3
Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].

Note:

    L, R  and A[i] will be an integer in the range [0, 10^9].
    The length of A will be in the range of [1, 50000].

Idea: using sliding window.

For each window if the new element is in range (L,R), we add window size m=(j-i+1) to the count. 
This remains same until we get a new element in range (then we recompute window size m=j-i+1. 
If new element is greater than R, we update i to j and we set m=0;

*/

class Solution {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        
        int i=0, j=0, count=0;
        int m=0; //m is the number of subarrays that will be added when this element got added
        while(j<A.length) {
            if (A[j]>=L && A[j]<=R) {
                m = j-i+1;
            } else if (A[j]>R) {
                m = 0; // the continuity breaks, so we reset the window.
                i=j+1;
            } else {
                // m remain the same
                // so that every next number will introduce m subarrays (m>=0)
            }
            count += m;
            j++;
        }
        
        return count;
    }
}
