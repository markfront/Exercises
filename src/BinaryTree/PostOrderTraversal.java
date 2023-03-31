package BinaryTree;

import java.util.*;

public class PostOrderTraversal {
    public List<Integer> traverseByRecursion(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }

        traverseByRecursion(root.left);
        traverseByRecursion(root.right);
        result.add(root.val);

        return result;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }

        // Stack 1 is used for pushing nodes for processing
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        // Stack 2 is used for storing the postorder traversal result in reverse order
        Stack<TreeNode> stack2 = new Stack<TreeNode>();

        // Push the root node onto stack 1
        stack1.push(root);

        // Continue processing nodes as long as stack 1 is not empty
        while (!stack1.isEmpty()) {
            // Pop the top node from stack 1
            TreeNode curr = stack1.pop();
            // Push the popped node onto stack 2
            stack2.push(curr);

            // Push the left child of the popped node onto stack 1, if it exists
            if (curr.left != null) {
                stack1.push(curr.left);
            }

            // Push the right child of the popped node onto stack 1, if it exists
            if (curr.right != null) {
                stack1.push(curr.right);
            }
        }

        // Pop nodes from stack 2 and add their values to the result list
        while (!stack2.isEmpty()) {
            TreeNode curr = stack2.pop();
            result.add(curr.val);
        }

        // Return the postorder traversal result
        return result;
    }
}
