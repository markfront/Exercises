/*
This is a problem asked by Google.

Given a string, find the longest substring that contains only k unique characters. 
For example, given "abcbbbbcccbdddadacb" and k=2, the longest substring that contains 2 unique character is "bcbbbbcccb". 
*/

public class Question {
  public string solve(string s, int k) {
    if (s==null || s.length()<k) return null;
    
    char[] chars = s.toCharArray();
    Map<Character, Integer> win = new HashMap<>();
    int maxLen = Integer.MIN_VALUE;
    
    int i=0, j=0; // 2 pointers
    while(i<=j && j<chars.length) {      
      if (win.size() < k) {
        char c2 = chars[j]; // right boundary of the window
        // expand the window with c2
        if (!win.containsKey(c2)) win.put(c2, 1);
        else win.put(c2, win.get(c2)+1);
        j++;
      } else {
        // update the maxLen
        maxLen = Math.max(maxLen, j-i);
        
        char c1 = chars[i]; // left boundary of the window
        // try reduce or drop c1 from the window
        if (win.get(c1) == 0) win.remove(c1);
        else win.put(c1, win.get(c1)-1);
        
        i++;
      }
    }
    
    if (win.size()==k) {
      maxLen = Math.max(maxLen, j-i);
    }
    
    if (maxLen != Integer.MIN_VALUE) return s.substring(i, j-i+1);
    return null;
  }
}
