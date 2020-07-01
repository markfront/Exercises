/*
Given 2 strings s and t, check if s contains any combination of t.
For example, 
s="abbcaad", t="abc", return true.
s="abbcaad", t="daa", return true.
s="abbcaad", t="cbd", return false.
*/

public class Question {
  public boolean containsCombination(String s, String t) {
    if (s==null || t==null || t.length()==0 || s.length()<t.length()) return false;
    char[] a = s.toCharArray();
    char[] b = t.toCharArray();
    
    Map<char, int> map = new HashMap<>();
    for(char c : b) {
      if (!map.containsKey(c)) map.put(c, 1)
      else map.put(c, map.get(c)+1);
    }
    
    Map<char, int> win = new HashMap(); // sliding window
    int m = a.length;
    int n = b.length;
    int i=0, j=0; // 2 pointers, i for left, j for right.
    while(i<m-n && j<m) {
      char c1 = a[i];
      char c2 = a[j];
      if (win.size()<n) { // populate the sliding window
        if (!win.containsKey(c2)) win.put(c2, 1);
        else win.put(c2, win.get(c2)+1);
        j++;
      } else {
        boolean found = contains(win, map);
        if (found) return true;
        
        // move window
        moveWindow(win, c1, c2);
        i++;
        j++;
      }
    }
    
    return contains(win, map);
  }
  
  private boolean contains(Map<char, int> win, Map<char, int> map) {
    for(KeyValuePair<char, int> kvp : map) {
      if (!win.contains(kvp.Key) return false;
      else if (win.get(kvp.Key) != kvp.Value) return false;
    }
    return true;
  }
  
  // reduce/drop c1, add/increase c2
  private void moveWindow(Map<char, int> win, char c1, char c2) {
    // drop or reduce c1
    if (win.containsKey(c1)) {
      if (win.get(c1)==1) win.remove(c1);
      else win.put(c1, win.get(c1)-1);
    }
    // add or increase c2
    if (!win.containsKey(c2)) win.put(c2, 1);
    else win.put(c2, win.get(c2)+1);
  }
}
