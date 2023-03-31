package BinaryTree;

public class FlatternToList {
    /*
    it is possible to flatten a binary tree into a linked list iteratively using a stack. Here's the algorithm to do it:

    Create an empty stack and push the root node onto it.
    While the stack is not empty, pop a node from the stack and perform the following steps:
    a. If the popped node has a right child, push the right child onto the stack.
    b. If the popped node has a left child, push the left child onto the stack.
    c. If the stack is not empty, set the right child of the popped node to be the top element of the stack.
    d. Set the left child of the popped node to null.

    This implementation has a time complexity of O(n), where n is the number of nodes in the binary tree, and a space complexity of O(h), where h is the height of the binary tree.
     */
    public void flattenByIteration(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }

            if (!stack.isEmpty()) {
                node.right = stack.peek();
            }
            node.left = null;
        }
    }

    /*
    The time complexity of this algorithm is O(n), where n is the number of nodes in the binary tree, and the space complexity is O(h), where h is the height of the binary tree.
     */
    public void flattenByRecursion(TreeNode root) {
        // base case: if root is null or it's a leaf node, return
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }

        // recursively flatten the left and right subtrees
        flattenByRecursion(root.left);
        flattenByRecursion(root.right);

        // if the left subtree is not null
        if (root.left != null) {
            // find the rightmost node of the flattened left subtree
            TreeNode rightmost = root.left;
            while (rightmost.right != null) {
                rightmost = rightmost.right;
            }

            // make the right child of the rightmost node the right subtree of the root
            rightmost.right = root.right;

            // make the left subtree the new right subtree of the root
            root.right = root.left;

            // set the left subtree to null
            root.left = null;
        }
    }

}
