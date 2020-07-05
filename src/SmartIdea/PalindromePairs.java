/*
https://leetcode.com/problems/palindrome-pairs/discuss/79210/The-Easy-to-unserstand-JAVA-Solution

There are several cases to be considered that isPalindrome(s1 + s2):

Case1: If s1 is a blank string, then for any string that is palindrome s2, s1+s2 and s2+s1 are palindrome.

Case 2: If s2 is the reversing string of s1, then s1+s2 and s2+s1 are palindrome.

Case 3: If s1[0:cut] is palindrome and there exists s2 is the reversing string of s1[cut+1:] , then s2+s1 is palindrome.

Case 4: Similiar to case3. If s1[cut+1: ] is palindrome and there exists s2 is the reversing string of s1[0:cut] , then s1+s2 is palindrome.

To make the search faster, build a HashMap to store the String-idx pairs.

*/

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (words==null || words.length==0) return result;
        ///*
        Map<String,Integer> map = new HashMap<>();
        for(int i=0; i<words.length; i++) {
            map.put(words[i], i);
        }
        //*/
        
        /* brute force O(N^2 * K)
        int i=0, j=0, n=words.length;
        while(i<n) {
            j=i+1;
            while(j<n) {
                if (isValid(words[i] + words[j])) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(i);
                    pair.add(j);
                    result.add(pair);
                } else if (isValid(words[j] + words[i])) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(j);
                    pair.add(i);
                    result.add(pair);
                }
                j++;
            } // end loop j
            i++;
        } // end loop i 
        */
        
        /*
        idea: for each word, split into half, go over all halves, 
        check if the left/right half is a palindrome. 
        If yes, then look for if reverse of the other half exists.
        */
        
        // case 1: if the reverse of a word exists:
        for(int i=0; i<words.length; i++) {
            String reverse = reverse(words[i]);
            if (map.containsKey(reverse) && map.get(reverse)!=i) {
                addPair(result, i, map.get(reverse));
                addPair(result, map.get(reverse), i);
            }
        }
        
        // case 2: if there is an empty word (should at most 1 because all words are unique)
        if (map.containsKey("")) {
            int zeroIdx = map.get("");
            for(int i=0; i<words.length; i++) {
                if (isValid(words[i]) && i != zeroIdx) {
                    addPair(result, i, zeroIdx);
                    addPair(result, zeroIdx, i);
                }
            }
        }
        
        for(int i=0; i<words.length; i++) {
            String s = words[i];
            for(int j=0; j<s.length(); j++) {                
                
                String left = s.substring(0,j);
                String right = s.substring(j);
                
                if (isValid(left)) {
                    String reverse = reverse(right);
                    if (map.containsKey(reverse) && map.get(reverse)!=i) {
                        addPair(result, i, map.get(reverse));
                    }
                }
                
                if (isValid(right)) {
                    String reverse = reverse(left);
                    if (map.containsKey(reverse) && map.get(reverse)!=i) {
                        addPair(result, map.get(reverse), i);
                    }
                }
            }
        }
        
        return result;
    }
    
    public boolean isValid(String s) {
        int i=0, j=s.length()-1;
        while(i<j) {
            if (s.charAt(i)!=s.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }
    
    public String reverse(String s) {
        char[] chars = s.toCharArray();
        int i=0, j=s.length()-1;
        while(i<j) {
            char t = chars[i];
            chars[i] = chars[j];
            chars[j] = t;
            i++; j--;
        }
        return new String(chars);
    }
                    
    public void addPair(List<List<Integer>> result, int i, int j) {
        List<Integer> pair = new ArrayList<>();
        pair.add(j);
        pair.add(i);
        if (!result.contains(pair))
            result.add(pair);
    }
}
