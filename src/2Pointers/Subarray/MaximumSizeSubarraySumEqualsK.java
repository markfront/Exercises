/*
https://www.programcreek.com/2014/10/leetcode-maximum-size-subarray-sum-equals-k-java/

Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

Example 1:
Given nums = [1, -1, 5, -2, 3], k = 3,
return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
*/

public int maxSubArrayLen(int[] nums, int k) {
    HashMap<Integer, Integer> map = new HashMap<>(); // keep the sum at step i
    map.put(0, -1);
    int result = 0;
    int sum = 0;
    
    // the idea is to keep a running sum (prefix sum)
    
    for(int i=0; i<nums.length; i++){
        sum += nums[i];
        if(map.containsKey(sum - k)){
        
            // if we've seen a prev_sum (at previous index i0) = sum-k, that means
            // there is the sum of subarray [i0+1, i] = sum - (sum-k) = k.
            // so we update the result with the length of this subarray.
            
            result = Math.max(result, i - map.get(sum - k));
        }
        map.putIfAbsent(sum, i);
    }
 
    return result;
}
