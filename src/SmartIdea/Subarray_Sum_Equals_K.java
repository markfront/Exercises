/*
https://leetcode.com/problems/subarray-sum-equals-k/

Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:

Input:nums = [1,1,1], k = 2
Output: 2 

Constraints:

    The length of the array is in range [1, 20,000].
    The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
*/

class Solution {
    public int subarraySum(int[] nums, int k) {
        // track how many times a sum has been seen
        // sum(i) = nums[0] + nums[1] + ... + nums[i]
        // therefore, sum[i:j] = sum[j] - sum[i]
        Map<Integer, Integer> map = new HashMap<>(); 
        map.put(0, 1); // sum=0 occurs 1 time

        int count = 0;
        int sum = 0;

        //e.g., 1 1 2 1 1
        for(int i=0; i<nums.length; i++){
            sum += nums[i];
            int n = map.getOrDefault(sum-k, 0);
            count += n;

            map.put(sum, map.getOrDefault(sum,0)+1);
        }

        return count;
    }
}
