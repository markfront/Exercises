1. 2 Pointers:

(1) Partition Linked List (use 2 dummy heads to hold 2 paritions, then chain up to return)
(2) Partition Array (maintain 2 sections S1 and S2, keep update lastS1 and firstS2)
(3) Longest Mountain in Array (use i and j for climb-start positions, track upStart and downStart)
(4) Subarray Product Less Than K (keep a sliding window of product<k, if >k then remove left by division, each step introduce j-i+1 count)
(5) Permutation in String Inclusion (keep a sliding window of fix size, each step update the window by remove left and add right)
(6) Statistics in Large Sample (one scan for min/max/mode/mean, two scan for median)
(7) Longest Word in Dictionary through Deleting (find if all chars in t are in s in the original order)
(8) 3 Sum (sort array, for each i (check to skip dup), go through the rest elements by j,k)
(9) 4 Sum (similar to 3 sum, for each i and i+1, use 2 pointers k and h in the rest elements)


2. Recursion:

(1) Find Kth Smallest Number in Array (partition array, keep 2 parts S1/S2, update lastS1/firstS2 on the go)
(2) Partition List by Given Number (use 2 dummy heads for smaller/bigger numbers, go over list, append to each, merge before return)
(3) Lowest Common Ancester in BT (use recursion on left/right child, base case root==p || root==q)
(4) Lowest Common Ancester in BST (use recursion, check order of root, root.left, root.right, base case root=null)
(5) Validate BST (recursion with helper function isValid(Node p, int min, int max), init min=MIN_VALUE, max=MAX_VALUE)
(6) BST Iterator (use a list to keep result, 2 ways to populate it: 
                  1. in-order traversal, or, 
                  2. use stack, start by push all left children till left most leave, then start pop, 
                     for each poped node, push all left children to left most leave, populate the right child.
(7) Path Sum Equals Given Sum ()

3. DFS + Backtracking:

https://leetcode.com/problems/subsets/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)

