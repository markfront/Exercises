/*
https://leetcode.com/problems/longest-substring-without-repeating-characters/

Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 

Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s==null || s.length()==0) return 0;
        
        int maxLen = 0;
        
        Set<Character> set = new HashSet<>();
        int i=0, j=0;
        while(i<=j && j<s.length()) {
            char c = s.charAt(j);
            if (set.contains(c)) {
                set.remove(s.charAt(i));
                i++;
            } else {
                set.add(c);
                maxLen = Math.max(maxLen, set.size());
                j++;
            }
        }
        
        return maxLen;
    }
}
