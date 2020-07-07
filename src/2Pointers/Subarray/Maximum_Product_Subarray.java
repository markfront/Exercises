/*
Idea 1: 
Loop through the array twice. One goes forward, the other goes backward. Do product and update max each loop. 
This should be enough to cover products of all subarrays.

Idea 2:
Loop through the array, each time remember the max and min value for the previous product, 
the most important thing is to update the max and min value: we have to compare among max * A[i], min * A[i] as well as A[i], 
since this is product, a negative * negative could be positive.
*/

class Solution {
    public int maxProduct(int[] nums) {
        if (nums==null || nums.length==0) return Integer.MIN_VALUE;
        
        int prod = 1;
        int result = Integer.MIN_VALUE;
        
        // compute cumulative product forward
        for(int i = 0; i < nums.length; i++) {
            prod = prod * nums[i];
            result = Math.max(prod, result);
            if(prod == 0) {
                prod = 1;
            }
        }
        
        prod = 1;
        
        // compute cumulative product backward
        for(int i = nums.length - 1; i >= 0; i--) {        
            prod = prod * nums[i];
            result = Math.max(prod, result);
            if(prod == 0) {
                prod = 1;
            }      
        }
        return result;
    }
}


public class Solution {
    public int maxProduct(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int max = A[0], min = A[0], result = A[0];
        for (int i = 1; i < A.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * A[i], min * A[i]), A[i]);
            min = Math.min(Math.min(temp * A[i], min * A[i]), A[i]);
            if (max > result) {
                result = max;
            }
        }
        return result;
    }
}

