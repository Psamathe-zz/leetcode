package com.example.leetcode.challenge.test2021.september.week4;

/**
 * You are given an n x n binary grid board. In each move, you can swap any two rows with each other, or any two columns with each other.
 *
 * Return the minimum number of moves to transform the board into a chessboard board. If the task is impossible, return -1.
 *
 * A chessboard board is a board where no 0's and no 1's are 4-directionally adjacent.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
 * Output: 2
 * Explanation: One potential sequence of moves is shown.
 * The first move swaps the first and second column.
 * The second move swaps the second and third row.
 * Example 2:
 *
 *
 * Input: board = [[0,1],[1,0]]
 * Output: 0
 * Explanation: Also note that the board with 0 in the top left corner, is also a valid chessboard.
 * Example 3:
 *
 *
 * Input: board = [[1,0],[1,0]]
 * Output: -1
 * Explanation: No matter what sequence of moves you make, you cannot end with a valid chessboard.
 *
 */
public class TransformChessboard {
    public static void main(String[] args) {
        TransformChessboard transformChessboard = new TransformChessboard();
        int res = transformChessboard.movesToChessboard(new int[][]{
                {1,0},
                {0,1},
        });
        System.out.println(res);
    }

    public int movesToChessboard(int[][] board) {
        int n = board.length, rowSum = 0, colSum = 0, rowDiff = 0, colDiff = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((board[0][0] ^ board[i][0] ^ board[0][j] ^ board[i][j]) == 1)
                    return -1;
            }
        }
        for (int i = 0; i < n; ++i) {
            rowSum += board[0][i];
            colSum += board[i][0];
            rowDiff += (board[i][0] == i % 2) ? 1 : 0;
            colDiff += (board[0][i] == i % 2) ? 1 : 0;
        }
        if (n / 2 > rowSum || rowSum > (n + 1) / 2) return -1;
        if (n / 2 > colSum || colSum > (n + 1) / 2) return -1;
        if (n % 2 == 1) {
            if (rowDiff % 2 == 1) rowDiff = n - rowDiff;
            if (colDiff % 2 == 1) colDiff = n - colDiff;
        } else {
            rowDiff = Math.min(n - rowDiff, rowDiff);
            colDiff = Math.min(n - colDiff, colDiff);
        }
        return (rowDiff + colDiff) / 2;
    }
}
