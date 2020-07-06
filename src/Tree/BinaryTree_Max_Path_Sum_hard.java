/*
https://leetcode.com/problems/binary-tree-maximum-path-sum/

Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6

Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
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
    public int maxPathSum(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        
        int[] max = new int[] {Integer.MIN_VALUE};
        
        helper(root, max);
        
        return max[0];
    }
    
    private int helper(TreeNode node, int[] max) {
        if (node==null) return 0;
        
        int left_max = helper(node.left, max);
        int right_max = helper(node.right, max);
        
        int local_max = Math.max(node.val, Math.max(left_max+node.val, right_max+node.val));
        
        max[0] = Math.max(max[0], Math.max(local_max, left_max+node.val+right_max));
        
        return local_max;
    }
}
