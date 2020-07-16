/*
https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/

Given a string and a string dictionary, find the longest string in the dictionary that can be formed 
by deleting some characters of the given string. If there are more than one possible results, 
return the longest word with the smallest lexicographical order. 
If there is no possible result, return the empty string.

Example 1:

Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output: 
"apple"

Example 2:

Input:
s = "abpcplea", d = ["a","b","c"]

Output: 
"a"

Note:

    All the strings in the input will only contain lower-case letters.
    The size of the dictionary won't exceed 1,000.
    The length of all the strings in the input won't exceed 1,000.
*/

class Solution {
    public String findLongestWord(String s, List<String> d) {
        if (s==null || d==null || s.length()==0 || d.size()==0) return "";
        
        String result = "";
        for(String t : d) {
            if (check(s, t)) {
                if (result.length() < t.length()) {
                    result = t;
                } else if (result.length() == t.length()) {
                    if (t.compareTo(result)<0) {
                        result = t;
                    }
                }
            }
        }
        return result;
    }
    
    // check if t can be formed by deleting some chars in s. 
    private boolean check(String s, String t) {
        int i=0;
        int j=0;
        while(j<t.length()) {
            while(i<s.length() && s.charAt(i)!=t.charAt(j)) {
                i++;
            }
            if (i==s.length()) return false;
            
            i++;
            j++;
        }
        return true;
    }
}
