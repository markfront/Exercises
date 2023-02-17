/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

*/

public class Solution {
    public IList<IList<int>> ThreeSum(int[] nums) {
        IList<IList<int>> results = new List<IList<int>>();

        int n = nums.Length;
        //if (n<3) return results;

        Array.Sort(nums);
        
        int i=0, j, k;
        while(i<n-2)
        {
            j = i+1;
            k = n-1;
            
            while(j<k)
            {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum > 0) k--;
                else if (sum < 0) j++;
                else
                {
                    var triplet = new List<int>() { nums[i], nums[j], nums[k] };
                    results.Add(triplet);
                    
                    while( j<k && nums[j] == triplet[1]) j++;
                    while( j<k && nums[k] == triplet[2]) k--;
                }
            } // end inner loop

            int currStart = nums[i];
            while (i<n-2 && nums[i]==currStart) 
            {
                i++;
            }
        } // end outer loop

        return results;
    }
}