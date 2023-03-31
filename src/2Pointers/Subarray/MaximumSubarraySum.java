/*
https://leetcode.com/problems/maximum-subarray/

Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

*/

import java.util.*;

public class MaximumSubarraySum {
    class IterativeSolution {
        public int maxSubArray(int[] nums) {
            if (nums==null || nums.length==0) return Integer.MIN_VALUE;

            int result = nums[0];

            int n = nums.length;
            int[] sum = new int[n];
            sum[0] = nums[0];

            for(int i=1; i<n; i++) {
                sum[i] = Math.max(nums[i], sum[i-1] + nums[i]);
                result = Math.max(result, sum[i]);
            }

            return result;
        }
    }

    /*
    The idea:
    algorithm that operates on arrays: it starts at the left end (element A[1]) and scans through to the right end (element A[n]), keeping track of the maximum sum subvector seen so far. The maximum is initially A[0]. Suppose we've solved the problem for A[1 .. i - 1]; how can we extend that to A[1 .. i]? The maximum
    sum in the first I elements is either the maximum sum in the first i - 1 elements (which we'll call MaxSoFar), or it is that of a subvector that ends in position i (which we'll call MaxEndingHere).

    MaxEndingHere is either A[i] plus the previous MaxEndingHere, or just A[i], whichever is larger.

    public static int maxSubArray(int[] A) {
        int maxSoFar=A[0], maxEndingHere=A[0];
        for (int i=1;i<A.length;++i){
            maxEndingHere= Math.max(maxEndingHere+A[i],A[i]);
            maxSoFar=Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }
    */

    class DivideAndConquer {
        public int maxSubArray(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }

            int mid = nums.length / 2;
            int[] left = Arrays.copyOfRange(nums, 0, mid);
            int[] right = Arrays.copyOfRange(nums, mid, nums.length);
            int left_sum = maxSubArray(left);
            int right_sum = maxSubArray(right);

            // find the maximum subarray sum that includes the midpoint
            int left_max = nums[mid-1];
            int right_max = nums[mid];
            int cur_sum = 0;

            for (int i = mid-1; i >= 0; i--) {
                cur_sum += nums[i];
                left_max = Math.max(left_max, cur_sum);
            }

            cur_sum = 0;

            for (int i = mid; i < nums.length; i++) {
                cur_sum += nums[i];
                right_max = Math.max(right_max, cur_sum);
            }

            return Math.max(left_sum, Math.max(right_sum, left_max+right_max));
        }
    }

    /*
     * Prove by Induction:
    To prove the correctness of the Divide and Conquer approach for the maximum subarray problem, we can use induction.

    Base case: When the length of the input array is 1, the maximum subarray sum is the only element in the array. This case is trivially correct.

    Induction step: Assume that the algorithm is correct for all input arrays of length less than n. We need to show that the algorithm is correct for an input array of length n.

    Let max_sum be the maximum subarray sum in the input array. We need to show that the algorithm returns max_sum.

    Case 1: The maximum subarray sum lies entirely in the left half of the array or entirely in the right half of the array. In this case, the algorithm recursively finds the maximum subarray sum in the left and right halves and returns the maximum of the two. By the induction hypothesis, the algorithm is correct for the left and right halves. Therefore, the algorithm is correct in this case.

    Case 2: The maximum subarray sum crosses the midpoint and lies partially in the left half and partially in the right half. In this case, the algorithm recursively finds the maximum subarray sum in the left and right halves and finds the maximum subarray sum that includes the midpoint. By the induction hypothesis, the algorithm is correct for the left and right halves. Therefore, we only need to show that the algorithm correctly finds the maximum subarray sum that includes the midpoint.

    Let left_max be the maximum subarray sum ending at the midpoint in the left half of the array. We can find left_max using a simple loop that iterates from the midpoint-1 to the beginning of the array and computes the maximum subarray sum ending at each position. Similarly, let right_max be the maximum subarray sum starting at the midpoint in the right half of the array. We can find right_max using a simple loop that iterates from the midpoint to the end of the array and computes the maximum subarray sum starting at each position. Then, the maximum subarray sum that includes the midpoint is left_max + right_max.

    We can show that the algorithm correctly finds left_max and right_max using induction. Assume that the algorithm correctly finds left_max and right_max for input arrays of length less than n. We need to show that the algorithm correctly finds left_max and right_max for an input array of length n.

    To find left_max, the algorithm recursively finds the maximum subarray sum in the left half of the array. By the induction hypothesis, the algorithm correctly finds left_max for the left half of the array. Then, the algorithm computes the maximum subarray sum ending at each position in the left half of the array, including the midpoint. Therefore, the algorithm correctly finds left_max for the input array.

    Similarly, to find right_max, the algorithm recursively finds the maximum subarray sum in the right half of the array. By the induction hypothesis, the algorithm correctly finds right_max for the right half of the array. Then, the algorithm computes the maximum subarray sum starting at each position in the right half of the array, including the midpoint. Therefore, the algorithm correctly finds right_max for the input array.

    Finally, the algorithm returns the maximum of left_sum, right_sum, and left_max + right_max, which is the maximum subarray sum in the input array. Therefore, the algorithm is correct for an input array of length n.

    By induction, the algorithm is correct for all input arrays.
     */
}