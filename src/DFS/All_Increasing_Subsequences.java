/*
https://leetcode.com/problems/increasing-subsequences/

Given an integer array, your task is to find all the different possible increasing subsequences of the given array, 
and the length of an increasing subsequence should be at least 2.

Example:

Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]

Constraints:

    The length of the given array will not exceed 15.
    The range of integer in the given array is [-100,100].
    The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
*/

class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        
        if (nums==null || nums.length<2) return new ArrayList<List<Integer>>();
        
        int n = nums.length;
        List<Integer> holder = new ArrayList<>();
        
        helper(nums, 0, holder, result, n);        
        
        return new ArrayList<List<Integer>>(result);
    }
    
    private void helper(int[] nums, int start, List<Integer> holder, 
                        Set<List<Integer>> result, int n) {
        // before adding more to the holder (as to be done in the following for-loop)
        // check the numbers in the current holder and add to result
        if (holder.size()>=2) {
            result.add(new ArrayList<Integer>(holder)); // use HashSet to avoid dups
        }
        
        for(int i=start; i<n; i++) {
            if (holder.isEmpty() || (holder.get(holder.size()-1) <= nums[i]) ) {
                
                holder.add(nums[i]);
                
                helper(nums, i+1, holder, result, n);
                
                holder.remove(holder.size()-1); // backtrack
            }
        }
    }
}
