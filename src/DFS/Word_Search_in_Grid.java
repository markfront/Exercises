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

class Solution {
    public boolean exist(char[][] board, String word) {
        if (board==null || board.length==0 || word==null || word.length()==0) return false;
        
        int m = board.length;
        int n = board[0].length;
        
        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++)
                if (dfs(board, i, j, word, 0, m, n)) return true;
        
        return false;
    }
    
    // start from board[i][j], search for word char-by-char from k.
    public boolean dfs(char[][] board, int i, int j, 
                       String word, int k, int m, int n) {
        // means there is no match in board[i][j]
        if (i<0||i>=m||j<0||j>=n||k>=word.length()) return false; 
        
        if (board[i][j]==word.charAt(k)) {
            
            if (k==word.length()-1) return true; // by far, all chars should have been matched
            
            // this path has potential
            
            char temp = board[i][j];
            board[i][j] = '#'; // flag the cell as already checked
            
            if (dfs(board, i+1, j, word, k+1, m, n)) return true;
            if (dfs(board, i, j+1, word, k+1, m, n)) return true;
            if (dfs(board, i-1, j, word, k+1, m, n)) return true;
            if (dfs(board, i, j-1, word, k+1, m, n)) return true;
            
            board[i][j] = temp; // backtrack
        } else {
            // this path does not work, no need to go further.
        }
        
        return false;
    }
}
