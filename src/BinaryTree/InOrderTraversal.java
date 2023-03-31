package BinaryTree;

import java.util.*;

public class InOrderTraversal {
    public List<Integer> traverseByRecursion(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }

        traverseByRecursion(root.left);
        result.add(root.val);
        traverseByRecursion(root.right);

        return result;
    }

    public List<Integer> traverseByIteration(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }

        return result;
    }
}
