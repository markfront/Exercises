/*
https://app.codesignal.com/arcade/intro/level-12/uRWu6K8E7uLi3Qtvx

Construct a square matrix with a size N × N containing integers from 1 to N * N in a spiral order, starting from top-left and in clockwise direction.

Example

For n = 3, the output should be

solution(n) = [[1, 2, 3],
               [8, 9, 4],
               [7, 6, 5]]

Input/Output

    [execution time limit] 3 seconds (java)

    [input] integer n

    Matrix size, a positive integer.

    Guaranteed constraints:
    3 ≤ n ≤ 100.

    [output] array.array.integer

 */

public class SpiralNumbers {
    public int[][] solution(int n) {
        int[][] a = new int[n][n];
        
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                a[i][j] = -1;
        
        int i=0, j=0, k=1, N=n*n, run_count = 0;
        int curr_row=0, curr_col=0;
        boolean rowwise=true, ascend=true;
        while(k<=N) {
            if (rowwise) {
                j=curr_col;
                while(j>=0 && j<n) {
                    if (a[curr_row][j]==-1) {
                        a[curr_row][j] = k++;
                        curr_col = j;
                    }
                    j += (ascend) ? 1 : -1;
                }
                run_count++;
            } else {
                i=curr_row;
                while(i>=0 && i<n) {
                    if (a[i][curr_col]==-1) {
                        a[i][curr_col] = k++;
                        curr_row = i;
                    }
                    i += (ascend) ? 1 : -1;
                }
                run_count++;
            }
                    
            rowwise = !rowwise;
            ascend = (run_count % 2 == 0)? !ascend : ascend;
        }
        
        return a;
    }
    
}
