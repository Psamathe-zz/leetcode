package com.example.leetcode.challenge.June.week3;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * Explanation:
 *
 * Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'.
 * Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
 * Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */
public class SurroundedRegions {
    public static void main(String[] args) {

    }

    public void solve(char[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((i == 0 || i == board.length - 1 || j == 0 || j == board[i].length - 1) && board[i][j] == 'O')
                    solveDFS(board, i, j);
            }
        }
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == '$') board[i][j] = 'O';
            }
        }
    }

    void solveDFS(char[][] board, int i, int j) {
        if (board[i][j] == 'O') {
            board[i][j] = '$';
            if (i > 0 && board[i - 1][j] == 'O')
                solveDFS(board, i - 1, j);
            if (j < board[i].length - 1 && board[i][j + 1] == 'O')
                solveDFS(board, i, j + 1);
            if (i < board.length - 1 && board[i + 1][j] == 'O')
                solveDFS(board, i + 1, j);
            if (j > 0 && board[i][j - 1] == 'O')
                solveDFS(board, i, j - 1);
        }
    }


    /**
     * faster solution
     * @param board
     */
    public void solveV1(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0)
            return;

        int m = board.length, n = board[0].length;

        for(int j = 0; j<n; j++){
            if(board[0][j] == 'O')
                bfs(board, 0, j);
            if(board[m-1][j] == 'O')
                bfs(board, m-1, j);
        }

        for(int i = 0; i<m; i++){
            if(board[i][0] == 'O')
                bfs(board, i, 0);
            if(board[i][n-1] == 'O')
                bfs(board, i, n-1);
        }

        convert(board, 'O', 'X');
        convert(board, 'I', 'O');

    }

    public void bfs(char[][] board, int i, int j){
        if(i < 0 || i>= board.length || j < 0 || j>=board[0].length || board[i][j] != 'O')
            return;
        board[i][j] = 'I';
        bfs(board, i-1, j);
        bfs(board, i+1, j);
        bfs(board, i, j-1);
        bfs(board, i, j+1);
    }

    public void convert(char[][] board, char a, char b){
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[0].length; j++){
                if(board[i][j] == a)
                    board[i][j] = b;
            }
        }
    }
}
