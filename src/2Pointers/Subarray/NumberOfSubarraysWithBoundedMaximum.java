/*

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
