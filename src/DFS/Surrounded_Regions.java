/*
https://leetcode.com/problems/surrounded-regions/submissions/

Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X

After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. 
Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. 
Two cells are connected if they are adjacent cells connected horizontally or vertically.
*/

class Solution {
    public void solve(char[][] board) {
        if(board == null || board.length == 0) return;
        int m = board.length;
        int n = board[0].length;
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // check from 'O' on boundary, to extend livable region and label as '*'
                if(i == 0 || i == m-1 || j == 0 || j == n-1) {
                    if(board[i][j] == 'O') dfs(i, j, board, m, n);
                }
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == '*') board[i][j] ='O';
                else board[i][j] = 'X';
            }
        }
        return;
    }
    
    private void dfs(int i, int j, char[][] board, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n) return;
        if (board[i][j] == 'X' || board[i][j] == '*') return;
        
        board[i][j] = '*';
        
        if (i+1 < m) dfs(i+1, j, board, m, n);
        if (i-1 > 0) dfs(i-1, j, board, m, n);
        if (j+1 < n) dfs(i, j+1, board, m, n);
        if (j-1 > 0) dfs(i, j-1, board, m, n);
    }
}
