/*
https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.

Note:
You may assume k is always valid, 1 ≤ k ≤ n2.
*/

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length, lo = matrix[0][0], hi = matrix[n - 1][n - 1];
        
        while (lo <= hi) {
            int mi = lo + ((hi - lo) >> 1);
            int count = countNonBigger(mi, matrix);
            if (count < k) {
                lo = mi + 1;
            } else {
                hi = mi - 1;
            }
        }
        
        return lo;
    }
    
    /*
    In the utility method countNonBigger, we can make use of the sorting feature of the matrix. 
    That is, if we start at the bottomleft, for the bigger, we can only move right, for the smaller, 
    we can only move up, and accumulate the number of elements upon the cell (inclusive) within the same column.
    */
    private static int countNonBigger(int target, int[][] matrix) {
        int n = matrix.length, i = n - 1, j = 0, cnt = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] > target) {
                i--;
            } else {
                cnt += i + 1;
                j++;
            }
        }
        
        return cnt;
    }
}
