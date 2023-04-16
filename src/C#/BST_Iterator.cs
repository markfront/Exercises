/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST. Calling next() will return the next smallest number in the BST. Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
*/

using System.Collections.Generic;

public class BST_Iterator {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    Stack<TreeNode> stack;

	public BST_Iterator(TreeNode root) {
		stack = new Stack<TreeNode>();
		while (root != null) {
			stack.Push(root);
			root = root.left;
		}
	}

	public boolean HasNext() {
		return stack.Count > 0;
	}

	public int Next() {
		TreeNode node = stack.Pop();
		int result = node.val;
		if (node.right != null) {
			node = node.right;
			while (node != null) {
				stack.Push(node);
				node = node.left;
			}
		}
		return result;
	}
}