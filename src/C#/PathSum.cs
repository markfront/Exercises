/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1

return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
*/
using System.Collections.Generic;

public class PathSum {
    // Definition for binary tree
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public bool HasPathSum(TreeNode root, int sum) {
        if (root == null) return false;

        Queue<TreeNode> nodes = new Queue<TreeNode>();
        Queue<int> values = new Queue<int>();

        nodes.Enqueue(root);
        values.Enqueue(root.val);

        while(!nodes.Count > 0) {
            TreeNode node = nodes.Dequeue();
            int pathSum = values.Dequeue();

            if (node.left==null && node.right==null && pathSum==sum) return true;

            if (node.left!=null) {
                nodes.Enqueue(node.left);
                values.Enqueue(pathSum + node.left.val);
            }

            if (node.right!=null) {
                nodes.Enqueue(node.right);
                values.Enqueue(pathSum + node.right.val);
            }
        }

        return false;
    }

    public bool hasPathSum2(TreeNode root, int sum) {
        if (root == null) return false;

        if (root.val == sum && (root.left == null && root.right == null))
            return true;

        return hasPathSum(root.left, sum - root.val)
            || hasPathSum(root.right, sum - root.val);
    }
}