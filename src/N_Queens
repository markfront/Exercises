public class N_Queens {

  private boolean occupied(int[][] board, int i, int j) {
    return board[i][j] == 1;
  }
  private boolean available(int[][] board, int i, int j) {
    return board[i][j] == 0;
  }
  private boolean underAttack(int[][] board, int i, int j) {
    // rules to check if a Queen will be under attack if placed in row i and col j.
  }
  
  public void solve(int N) {
    int[][] board = new int[N][N];
    for(int i=0; i<N; i++)
      for(int j=0; j<N; j++)
        board[i][j] = 0;
        
    for(int q=0; q<N; q++) {
      // try place each queen in the board
      if (!canPlace(q, board, 0, 0, N)) {
        break;
      }
    }
  }
  
  public boolean canPlace(int q, int[][] board, int i, int j, int N) {
    if (i<0 || j<0 || i>=N || j>=N) return true;
    
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        if (available(board, i, j)) {
          if (!underAttack(board, i, j)) {
            board[i][j] = 1; // label it as occupied
            return true;
          } else {          
            int t = board[i][j]; // save the cell's status before going deep along the path
            board[i][j] = 1;
            if (canPlace(q, board, i+1, j, N) ||
                canPlace(q, board, i, j+1, N) ||
                canPlace(q, board, i-1, j, N) ||
                canPlace(q, board, i, j-1, N) ) {
              return true;
            }
            board[i][j] = t; // backtrack to the saved previous status
          }
        }
      } // end-loop-j
    } // end-loop-i
      
    return false;
  }
}
