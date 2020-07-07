/*
https://leetcode.com/problems/word-search/

Given a 2D board and a word, find if the word exists in the grid.
The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.

Constraints:

    board and word consists only of lowercase and uppercase English letters.
    1 <= board.length <= 200
    1 <= board[i].length <= 200
    1 <= word.length <= 10^3
*/
public class WordSearchInBoard {
  public boolean find(char[][] board, String word) {
    if (board == null || board.length == 0 || board[0].length == 0 
        || word == null || word.length() == 0) return false;
    int m = board.length;
    int n = board[0].length;
    
    for(int i=0; i<m; i++)
      for(int j=0; j<n; j++) {
        if (dfs(board, i, j, word, 0)) {
          return true;
        }
      }
      
    return false;
  }
  
  public boolean dfs(char[][] board, int i, int j, String word, int k) {
    int m = board.length;
    int n = board[0].length;
    if (i<0 || j< 0 || i>=m || j>=n) return false;
    if (board[i][j] == word.charAt(k)) {
      if ( k == word.length()-1 ) return true; // found all chars in the word 
      char t = board[i][j];
      board[i][j] = '#';
      if (dfs(board, i+1, j, word, k+1) || 
          dfs(board, i, j+1, word, k+1) ||
          dfs(board, i-1, j, word, k+1) ||
          dfs(board, i, j-1, word, k+1)) {
          return true;
      }        
      board[i][j] = t; // backtrack
    }
    return false;
  }
}
