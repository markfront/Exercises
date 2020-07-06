/*
https://leetcode.com/problems/path-sum-ii/

Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1

Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
 

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root==null) return result;
        
        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        
        helper(root, sum-root.val, path, result);
        
        return result;
    }
    
    // Idea: use DFS
    private void helper(TreeNode node, int sum, List<Integer> path, 
                        List<List<Integer>> result) {
        if (node.left==null && node.right==null) {
            if (sum==0) {
                // found 1 path
                // Note: need to create a new ArrayList!!!
                result.add(new ArrayList(path)); 
                return;
            } else {
                return;
            }
        } 
        
        if (node.left != null) {
            path.add(node.left.val);
            helper(node.left, sum - node.left.val, path, result);
            path.remove(path.size()-1);
        } 
        
        if (node.right != null) {
            path.add(node.right.val);
            helper(node.right, sum - node.right.val, path, result);
            path.remove(path.size()-1);
        }
    }
}


