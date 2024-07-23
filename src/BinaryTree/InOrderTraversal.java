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

        // Process initial node and all its left descendants
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }

        // Loop until the stack is empty
        while (!stack.empty()) {
            // Retrieve the top node from the stack
            curr = stack.pop();

            // Add the retrieved node's value to the list
            result.add(curr.val);

            // Move to the right child and add any left children to the stack
            curr = curr.right;

            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
        }

        return result;
    }
}
