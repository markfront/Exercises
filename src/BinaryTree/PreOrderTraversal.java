package BinaryTree;

import java.util.*;

public class PreOrderTraversal {
    public List<Integer> traverseByRecursion(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }

        result.add(root.val);
        traverseByRecursion(root.left);
        traverseByRecursion(root.right);

        return result;
    }

    public List<Integer> traverseByIteration(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode node = stack.pop();
            result.add(node.val);

            // push the right child first so that it gets processed after the left child
            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return result;
    }
}
