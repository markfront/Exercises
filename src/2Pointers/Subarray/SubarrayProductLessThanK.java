/*
https://leetcode.com/problems/subarray-product-less-than-k/

Use a sliding window [i,j] to keep product less than K. If larger than K, drop the leftmost number.
Also note the trick to do count.
*/

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums==null || nums.length==0 || k<=1) return 0;
        
        int count=0;
        long prod=1l;
        
        int i=0, j=0;
        while(j<nums.length) {
            prod *= nums[j];
            
            while (i<=j && prod >=k) {
                prod /= nums[i++];                
            }
            
            count += (j-i+1);
            
            j++;
        }
        
        return count;
    }
}
