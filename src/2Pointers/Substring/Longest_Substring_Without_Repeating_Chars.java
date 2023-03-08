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
import java.util.*;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int result = 0;
        Set<Character> chars = new HashSet<>();
        while(right< s.length()){
            char c = s.charAt(right);
            if(!chars.contains(c)){
                chars.add(c);
                right++;
                result = Math.max(result, right - left);
            }
            else{
                chars.remove(s.charAt(left));
                left++;
            }
        }
        return result;
    }
}
