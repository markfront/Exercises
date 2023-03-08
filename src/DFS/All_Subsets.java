/*
https://leetcode.com/problems/subsets/

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

// If S is a finite set with |S| = n elements, then the number of subsets of S is |P(S)| = 2^n.

import java.util.*;

class All_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //Arrays.sort(nums); // not necessary, because the input numbers are unique
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result , List<Integer> tempList, 
                           int [] nums, int start){
        result.add(new ArrayList<>(tempList));
      
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(result, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        All_Subsets solution = new All_Subsets();

        int[] nums = new int[] { 1, 2, 3, 4 };

        List<List<Integer>> subsets = solution.subsets(nums);

        for(List<Integer> subset : subsets) {
            for(int i : subset) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
