/*
https://leetcode.com/problems/continuous-subarray-sum/discuss/99499/Java-O(n)-time-O(k)-space

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
