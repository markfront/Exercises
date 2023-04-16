/*
 Given an array of N numbers, return the maximum number of elements that fulfill the fact of having the same difference between each element after sorting the array ascendant.
 */

package 2Pointers.Subarray;

import java.util.*;

public class Max_Elements_with_Same_Differences {

    public static int maxElementsWithSameDifference(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> diffCount = new HashMap<>();
        Arrays.sort(arr); // sort the array in ascending order
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int diff = arr[j] - arr[i];
                if (!diffCount.containsKey(diff)) {
                    diffCount.put(diff, 2); // initialize count to 2 (for the current pair)
                } else {
                    diffCount.put(diff, diffCount.get(diff)+1);
                }
            }
        }

        int maxCount = 0;
        for (int diff : diffCount.keySet()) {
            int count = diffCount.get(diff);
            if (count > maxCount) {
                maxCount = count;
            }
        }

        return maxCount;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 3, 4, 2};
        int maxCount = maxElementsWithSameDifference(arr);
        System.out.println(maxCount);  // Output: 3
    }
}
