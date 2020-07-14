/*
https://leetcode.com/problems/subsets-ii/

Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (nums==null || nums.length==0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        
        Arrays.sort(nums);
        
        dfs(nums, new ArrayList(), result, 0);        
        
        return result;
    }
    
    public void dfs(int[] nums, List<Integer> holder, List<List<Integer>> result, int start) {
        result.add(new ArrayList<Integer>(holder));
        
        for(int i=start; i<nums.length; i++) {
            if (i>start && nums[i]==nums[i-1]) continue;
            
            holder.add(nums[i]);
            
            dfs(nums, holder, result, i+1);
            
            holder.remove(holder.size()-1);
        }
        
    }
}
