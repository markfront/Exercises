/*
This is a problem asked by Google.

Given a string, find the longest substring that contains only two unique characters. 
For example, given "abcbbbbcccbdddadacb", the longest substring that contains 2 unique character is "bcbbbbcccb". 
*/

public class Question {
  public string solve(string s, int k) {
    if (s==null || s.length()<k) return null;
    
    char[] chars = s.toCharArray();
    Map<Character, Integer> map = new HashMap<>();
    int max = Integer.MIN_VALUE;
    int i=0, j=0; // 2 pointers
    while(i<=j && j<chars.length) {
      char c1 = chars[i];
      char c2 = chars[j];
      if (map.size() < k) {
        if (!map.containsKey(c2)) map.put(c2, 1);
        else map.put(c2, map.get(c2)+1);
        j++;
      } else {
        max = Math.max(max, j-i);
        
        shiftWindow(map, c1, c2);
        i++;
      }
    }
    
    if (map.size()==k) {
      max = Math.max(max, j-i);
    }
    
    if (max != Integer.MIN_VALUE) return s.substring(i, j-i+1);
    return null;
  }
  
  private void shiftWindow(Map<Character, Integer> map, char c1, char c2) {
    if (map.containsKey(c1)) {
      if (map.get(c1) == 0) map.remove(c1);
      else map.put(c1, map.get(c1)-1);
    }
    if (!map.containsKey(c2)) map.put(c2, 1);
    else map.put(c2, map.get(c2)+1);
  }
}
