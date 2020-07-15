/*
https://leetcode.com/problems/longest-mountain-in-array/

Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:

    B.length >= 3
    There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]

(Note that B could be any subarray of A, including the entire array A.)

Given an array A of integers, return the length of the longest mountain. 

Return 0 if there is no mountain.

Example 1:

Input: [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.

Example 2:

Input: [2,2,2]
Output: 0
Explanation: There is no mountain.

Note:

    0 <= A.length <= 10000
    0 <= A[i] <= 10000

Follow up:

    Can you solve it using only one pass?
    Can you solve it in O(1) space?
*/

class Solution {
    public int longestMountain(int[] A) {
        if (A==null || A.length<3) return 0;
        
        int maxLen = 0;
        
        int i=0, j=1;
        boolean upStart=false;
        boolean downStart=false;
        
        while(j<A.length) {
            if (A[j]>A[j-1]) { // go up
                if (!upStart) { // possibly a new mountain
                    upStart = true;
                    i=j-1;
                    if (downStart) {
                        downStart=false; // useless downhill 
                    } else {
                        // 
                    }
                } else {
                    if (downStart) { // there was a mountain
                        maxLen = Math.max(maxLen, j-i);
                        i = j-1;
                        //upStart = true;
                        downStart = false;
                    } else {
                        // continue going up
                    }
                }
            } else if (A[j]<A[j-1]) { // go down
                if (!downStart) {
                    downStart = true;
                } else {
                    if (upStart) {
                        // there was a peak
                    } else {
                        // downhill
                    }
                }
            } else { // go flat
                if (upStart && downStart) { // there was a mountain
                    maxLen = Math.max(maxLen, j-i);
                }
                i = j;
                upStart = false;
                downStart = false;
            }
            j++;
        }
        
        if (upStart && downStart) { // there was a mountain
            maxLen = Math.max(maxLen, j-i);
        }
        
        return maxLen;
    }
}
