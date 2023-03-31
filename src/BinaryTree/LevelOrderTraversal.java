package BinaryTree;

import java.util.*;

public class LevelOrderTraversal {
    public List<Integer> traverseByIteration(TreeNode root) {
        // Create a list to store the nodes in level order
        List<Integer> result = new ArrayList<Integer>();
        // Check if the root is null, return empty list if so
        if (root == null) {
            return result;
        }
        // Create a queue to store the nodes to be processed
        Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
        // Add the root node to the queue
        queue.offer(root);
        // While the queue is not empty
        while (!queue.isEmpty()) {
            // Get the size of the queue (number of nodes at this level)
            int size = queue.size();
            // Traverse all the nodes at this level
            for (int i = 0; i < size; i++) {
                // Remove the first node from the queue
                TreeNode node = queue.poll();
                // Add the value of the node to the result list
                result.add(node.val);
                // Add the left child to the queue if it exists
                if (node.left != null) {
                    queue.offer(node.left);
                }
                // Add the right child to the queue if it exists
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        // Return the result list
        return result;
    }


    /*
     In the recursive implementation, we first find the height of the binary tree using a helper function called height. Then, we traverse the tree level by level using another helper function called traverseLevel, which recursively traverses each level and adds the nodes to the result list. Finally, we return the result list. Note that this implementation is less efficient than the iterative implementation using a queue, as it requires us to traverse the tree multiple times.
     */
    public List<Integer> traverseByRecursion(TreeNode root) {
        // Create a list to store the nodes in level order
        List<Integer> result = new ArrayList<Integer>();

        // Find the height of the tree
        int height = height(root);

        // Traverse the tree level by level
        for (int i = 1; i <= height; i++) {
            // Traverse the current level and add the nodes to the result list
            // Here is the key step:
            // for each loop, always start from the root node to look for the nodes in the specified level!
            traverseLevel(root, i, result);
        }
        // Return the result list
        return result;
    }

    // Helper function to find the height of a binary tree
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            int leftHeight = height(node.left);
            int rightHeight = height(node.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // Helper function to traverse a level of a binary tree and add the nodes to a list
    private void traverseLevel(TreeNode node, int level, List<Integer> result) {
        if (node == null) {
            return;
        }
        if (level == 1) {
            // If we have reached the desired level, add the node to the result list
            result.add(node.val);
        } else {
            // Recursively traverse the left and right subtrees at the next level
            traverseLevel(node.left, level - 1, result);
            traverseLevel(node.right, level - 1, result);
        }
    }
}
