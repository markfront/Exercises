/*
https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6

The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
*/


class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public void flatten(TreeNode root) {
        if (root==null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;

        flatten(left);
        flatten(right);

        // set the flattened left branch as the right child of root
        root.right = left;

        // find the last node of the flattened left
        TreeNode curr = root;
        while(curr.right!=null) {
            curr = curr.right;
        }

        // chain up the flattened right branch
        curr.right = right;
    }
}
