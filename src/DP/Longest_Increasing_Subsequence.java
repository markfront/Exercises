/*
https://leetcode.com/problems/longest-increasing-subsequence/description/

https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/

Given an unsorted array of integers, find the length of longest strictly increasing subsequence.

For example, given [10, 9, 2, 5, 3, 7, 101, 18], the longest increasing subsequence is [2, 3, 7, 101]. Therefore the length is 4.
*/
import java.util.*;

public class Longest_Increasing_Subsequence {
  // Solution 1: O(N^2) time complexity using DP
  public int solve(int[] nums) {
    if (nums==null || nums.length==0) return 0;

    int[] max = new int[nums.length]; // keep length of the longest subsequence so far
    Arrays.fill(max, 1); // each number is a length=1 subsequence by itself

    int result = 1; // keep the global max
    for(int i=0; i<nums.length; i++) {
      for(int j=0; j<=i; j++) {
        if (nums[j]<nums[i]) {
          max[i] = Math.max(max[i], max[j] + 1);
        }
      } // now max[i] is the length of the longest subsequence seen so far
      result = Math.max(result, max[i]);
    }
    return result;
  }

  // Solution 2: O(N*logN) using Patience Sorting
  public static int lengthOfLIS(int[] nums) {
    // Create an empty list to store the piles of cards
    List<Integer> piles = new ArrayList<>();

    // Iterate over the elements of the input array
    for (int x : nums) {
        // Perform a binary search to find the index of the pile where we can place num
        // Note the piles is guaranteed to be sorted!
        int pileIndex = Collections.binarySearch(piles, x);

        // If pileIndex is positive, it means x is in the pile, we can add num to the corresponding pile
        if (pileIndex >= 0) {
            piles.set(pileIndex, x);
        }
        // If pileIndex is negative, it means x is not in the pile, we need to create a new pile with num as its top card
        else {
            // Convert the negative index to the index where num should be inserted
            pileIndex = -(pileIndex + 1);
            // If pileIndex is equal to the size of the list, we need to add num as a new pile
            if (pileIndex == piles.size()) {
                piles.add(x);
            }
            // Otherwise, we can replace the top card of the corresponding pile with num
            else {
                piles.set(pileIndex, x);
            }
        }
    }

    // The number of piles represents the length of the longest increasing subsequence
    return piles.size();
  }

  // The Patience Sorting using Hash Map:
  public static int PatienceSort(int[] nums) {
    // Create a HashMap to store the tops of the piles
    // The key represents the index of the pile, and the value represents the top card of the pile
    // which is the last card added to the pile.
    Map<Integer, Integer> topOfPile = new HashMap<>();

    // Iterate over the elements of the input array
    for (int num : nums) {
        // Perform a binary search to find the index of the pile where we can place num
        int left = 0, right = topOfPile.size() - 1;
        while (left <= right) { // Changed the condition from "left < right" to "left <= right"
            int mid = left + (right - left) / 2;
            if (topOfPile.get(mid) < num) {
                left = mid + 1;
            } else {
                right = mid - 1; // Changed the update to "right = mid - 1"
            }
        }

        // If left is equal to the size of the pile, we need to create a new pile with num as its top card
        // Otherwise, extend the pile at index left with num
        topOfPile.put(left, num);
    }

    // The number of piles represents the length of the longest increasing subsequence
    return topOfPile.size();
  }

}
