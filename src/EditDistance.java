public class EditDistance {
  public int distance(string s1, string s2) {
    if (s1==null || s2==null) return -1;
    int len1 = s1.length();
    int len2 = s2.length();
    
    if (len1 == 0) return len2;
    if (len2 == 0) return len1;
    
    int[][] mem = new int[len1+1][len2+1];
    
    mem[0][0] = 0;
    for(int i=1; i<=len1; i++) mem[i][0] = i; // s1[0:i] has i+1 chars
    for(int j=1; j<=len2; j++) mem[0][j] = j; // s2[0:j] has j+1 chars
    for(int i=1; i<=len1; i++) 
      for(int j=1; j<len2; j++)
        mem[i][j] = -1;
    
    //return recursion(s1, len1-1, s2, len2-1, mem);
    return dp(s1, len1, s2, len2, mem);
  }
  
  public int recursion(string s1, int i, stirng s2, int j, int[][] mem) {
    if (i==0) return mem[0][j]; // base case
    if (j==0) return mem[i][0]; // base case
    
    if (mem[i][j] != -1) return mem[i][j]; // use memory
    
    if (s1.charAt(i) == s2.charAt(j)) return helper(s1, i-1, s2, j-1, mem);
    
    int insert  = 1 + helper(s1, i-1, s2, j, mem);
    int delete  = 1 + helper(s1, i, s2, j-1, mem);
    int replace = 1 + helper(s1, i-1, s2, j-1, mem);
    
    int min = Math.min(replace, Math.min(insert, delete));
    
    mem[i][j] = min;
    
    return min;
  }
  
  public int dp(string s1, int len1, string s2, int len2, int[][] mem) {
    for(int i=1; i<len1; i++)
      for(int j=1; j<len2; j++) {
        if (s1.charAt(i) == s2.charAt(j)) {
          mem[i][j] = mem[i-1][j-1];
        } else {
          int insert = 1 + mem[i-1][j];
          int delete = 1 + mem[i][j-1];
          int replace = 1 + mem[i-1][j-1];
          mem[i][j] = Math.min(replace, Math.min(insert, delete));
        }        
      }
    
    return mem[len1][len2];
  }
}
