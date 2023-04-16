/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example, given the below binary tree and sum = 22,

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1

the method returns the following:

[
   [5,4,11,2],
   [5,8,4,5]
]
*/

using System.Collections.Generic;

public class PathSumAll {
    public IList<IList<int>> PathSum(TreeNode root, int targetSum) {
        IList<IList<int>> result = new List<IList<int>>();

        if (root == null) return result;

        IList<int> list = new List<int>();
        list.Add(root.val);

        DfsHelper(root, targetSum-root.val, result, list);

        return result;
    }

    public void DfsHelper(TreeNode node, int targetSum, IList<IList<int>> result, IList<int> list){
        if (node.left==null && node.right==null && targetSum==0) {
            IList<int> temp = new List<int>(list);
            result.Add(temp);
        }

        //search path of left node
        if (node.left != null) {
            list.Add(node.left.val);

            DfsHelper(node.left, targetSum-node.left.val, result, list);

            list.RemoveAt(list.Count-1); // backtrack
        }

        //search path of right node
        if (node.right!=null) {
            list.Add(node.right.val);

            DfsHelper(node.right, targetSum-node.right.val, result, list);

            list.RemoveAt(list.Count-1); // backtrack
        }
    }
}