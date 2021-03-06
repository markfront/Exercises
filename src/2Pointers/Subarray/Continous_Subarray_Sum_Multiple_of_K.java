/*
https://leetcode.com/problems/continuous-subarray-sum/discuss/99499/Java-O(n)-time-O(k)-space

Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray 
of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:

Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.

Example 2:

Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.

Constraints:

    The length of the array won't exceed 10,000.
    You may assume the sum of all the numbers is in the range of a signed 32-bit integer.


Idea: We iterate through the input array exactly once, keeping track of the running sum mod k of the elements in the process. 
If we find that a running sum value at index j has been previously seen before in some earlier index i in the array, 
then we know that the sub-array (i,j] contains a desired sum.

A proof sketch:
Suppose sum_i represents the running sum starting from index 0 and ending at i,
once we find a mod that has been seen, say modk, we have:
current one: sum_i = m*k + modk
previous one: sum_j = n*k + modk
Thus,
sum_i - sum_j = (m - n) *k
*/

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums==null || nums.length==0) return false;
        
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0, -1);
        
        int n = nums.length;
        int sum=0, i=0;
        while(i<n) {
            sum += nums[i];            
            if (k!=0) sum %= k; // handle corner case k==0
            if (map.containsKey(sum)) {
                int prev = map.get(sum);
                if (prev < i-1) return true;
            } else {
                map.put(sum, i);
            }            
            i++;
        }        
       
        return false;
    }
}
