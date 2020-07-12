/*
https://leetcode.com/problems/number-of-islands/

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
*/

class Solution {
    public int numIslands(char[][] grid) {
        if (grid==null || grid.length==0) return 0;
        
        int m = grid.length;
        int n = grid[0].length;
        
        int count = 0;
        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++) {
                if (grid[i][j]=='1') {
                    // try expand the land to neighbors and flag them as visited
                    // so that they won't be counted again by later DFS.
                    dfs(grid, i, j, m, n);
                    
                    count++; // found a group of connected lands, so add 1 island to count.
                }
            }
        
        return count;
    }
    
    private void dfs(char[][] grid, int i, int j, int m, int n) {
        if (i<0||i>=m || j<0||j>=n) return;
        
        if (grid[i][j]=='1') {
            grid[i][j] = '#'; // label as a visited land

            dfs(grid, i+1, j, m, n);
            dfs(grid, i-1, j, m, n);
            dfs(grid, i, j+1, m, n);
            dfs(grid, i, j-1, m, n);
        }
    }
}
