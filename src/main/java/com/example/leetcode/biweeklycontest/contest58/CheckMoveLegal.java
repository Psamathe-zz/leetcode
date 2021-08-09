package com.example.leetcode.biweeklycontest.contest58;


/**
 * You are given a 0-indexed 8 x 8 grid board, where board[r][c] represents the cell (r, c) on a game board. On the board, free cells are represented by '.', white cells are represented by 'W', and black cells are represented by 'B'.
 *
 * Each move in this game consists of choosing a free cell and changing it to the color you are playing as (either white or black). However, a move is only legal if, after changing it, the cell becomes the endpoint of a good line (horizontal, vertical, or diagonal).
 *
 * A good line is a line of three or more cells (including the endpoints) where the endpoints of the line are one color, and the remaining cells in the middle are the opposite color (no cells in the line are free). You can find examples for good lines in the figure below:
 *
 *
 * Given two integers rMove and cMove and a character color representing the color you are playing as (white or black), return true if changing cell (rMove, cMove) to color color is a legal move, or false if it is not legal.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [[".",".",".","B",".",".",".","."],[".",".",".","W",".",".",".","."],[".",".",".","W",".",".",".","."],[".",".",".","W",".",".",".","."],["W","B","B",".","W","W","W","B"],[".",".",".","B",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","W",".",".",".","."]], rMove = 4, cMove = 3, color = "B"
 * Output: true
 * Explanation: '.', 'W', and 'B' are represented by the colors blue, white, and black respectively, and cell (rMove, cMove) is marked with an 'X'.
 * The two good lines with the chosen cell as an endpoint are annotated above with the red rectangles.
 * Example 2:
 *
 *
 * Input: board = [[".",".",".",".",".",".",".","."],[".","B",".",".","W",".",".","."],[".",".","W",".",".",".",".","."],[".",".",".","W","B",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".","B","W",".","."],[".",".",".",".",".",".","W","."],[".",".",".",".",".",".",".","B"]], rMove = 4, cMove = 4, color = "W"
 * Output: false
 * Explanation: While there are good lines with the chosen cell as a middle cell, there are no good lines with the chosen cell as an endpoint.
 *
 *
 * [[".",".","W",".","B","W","W","B"],["B","W",".","W",".","W","B","B"],[".","W","B","W","W",".","W","W"],["W","W",".","W",".",".","B","B"],["B","W","B","B","W","W","B","."],["W",".","W",".",".","B","W","W"],["B",".","B","B",".",".","B","B"],[".","W",".","W",".","W",".","W"]]
 * 5
 * 4
 * "W"
 *
 */
public class CheckMoveLegal {
    public static void main(String[] args) {
        CheckMoveLegal checkMoveLegal = new CheckMoveLegal();
        boolean res= checkMoveLegal.checkMove(new char[][]{
                        {'.','.','W','.','B','W','W','B'},
                        {'B','W','.','W','.','W','B','B'},
                        {'.','W','B','W','W','.','W','W'},
                        {'W','W','.','W','.','.','B','B'},
                        {'B','W','B','B','W','W','B','.'},
                        {'W','.','W','.','.','B','W','W'},
                        {'B','.','B','B','.','.','B','B'},
                        {'.','W','.','W','.','W','.','W'}}
                , 5, 4, 'W');
        System.out.println(res);
    }

    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        int u = board.length;
        int v = board.length;
        int[][] dirs = new int[][]{
                {0,1},
                {1,0},
                {0,-1},
                {-1,0},
                {1,1},
                {1,-1},
                {-1,1},
                {-1,-1}
        };
        int i;
        int j;
        boolean temp;
        int count1;
        int count2;
        for (int[] dir: dirs){
            i = rMove + dir[0];
            j = cMove + dir[1];
            count1 = 0;
            count2 = 0;
            while ( i < u && i >= 0 && j < v && j>= 0){

                if(board[i][j] != '.' && board[i][j] != color && count2 == 0)
                    count1++;
                else if( board[i][j] == color && count2 == 0)
                    count2++;
                else
                    break;
                i += dir[0];
                j += dir[1];
            }
            temp = count1 > 0 && count2 > 0;
            if(temp)
                return true;
        }
        return false;
    }
}
