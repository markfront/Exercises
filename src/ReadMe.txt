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



