/*
https://www.programcreek.com/2014/10/leetcode-maximum-size-subarray-sum-equals-k-java/

*/

public int maxSubArrayLen(int[] nums, int k) {
  int result = 0;
  
  Map<Integer,Integer> map = new HashMap<>(); 
  
  int sum = 0;
  for(int i=0; i<nums.length; i++) {
    sum += nums[i];
    if (map.containsKey(sum-k)) {
      result = Math.max(result, i - map.get(sum-k));
    }
    map.put(sum, i);
  }
  
  return result;
}
