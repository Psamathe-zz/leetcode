package com.example.leetcode.challenge.test2021.september.week3;

/**
 * Tic-tac-toe is played by two players A and B on a 3 x 3 grid.
 *
 * Here are the rules of Tic-Tac-Toe:
 *
 * Players take turns placing characters into empty squares (" ").
 * The first player A always places "X" characters, while the second player B always places "O" characters.
 * "X" and "O" characters are always placed into empty squares, never on filled ones.
 * The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 * Given an array moves where each element is another array of size 2 corresponding to the row and column of the grid where they mark their respective character in the order in which A and B play.
 *
 * Return the winner of the game if it exists (A or B), in case the game ends in a draw return "Draw", if there are still movements to play return "Pending".
 *
 * You can assume that moves is valid (It follows the rules of Tic-Tac-Toe), the grid is initially empty and A will play first.
 *
 *
 *
 * Example 1:
 *
 * Input: moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
 * Output: "A"
 * Explanation: "A" wins, he always plays first.
 * "X  "    "X  "    "X  "    "X  "    "X  "
 * "   " -> "   " -> " X " -> " X " -> " X "
 * "   "    "O  "    "O  "    "OO "    "OOX"
 * Example 2:
 *
 * Input: moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
 * Output: "B"
 * Explanation: "B" wins.
 * "X  "    "X  "    "XX "    "XXO"    "XXO"    "XXO"
 * "   " -> " O " -> " O " -> " O " -> "XO " -> "XO "
 * "   "    "   "    "   "    "   "    "   "    "O  "
 * Example 3:
 *
 * Input: moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]
 * Output: "Draw"
 * Explanation: The game ends in a draw since there are no moves to make.
 * "XXO"
 * "OOX"
 * "XOX"
 * Example 4:
 *
 * Input: moves = [[0,0],[1,1]]
 * Output: "Pending"
 * Explanation: The game has not finished yet.
 * "X  "
 * " O "
 * "   "
 */
public class FindWinner {
    public static void main(String[] args) {
        FindWinner findWinner =  new FindWinner();
        findWinner.tictactoe(new int[][]{{2,2},{0,2},{1,0},{0,1},{2,0},{0,0}});
    }

    public String tictactoe(int[][] moves) {
        int[][]table = new int[3][3];
        int index = 0;
        for (int[] move : moves){
            table[move[0]][move[1]] = index % 2 == 0 ? -1:1;
            index++;
        }
        int x = 0;
        int y = 0;
        int diagonal = 0;
        int diagona2 = 0;
        for (int i = 0; i < 3; i++) {
            x = 0;
            y = 0;
            for (int j = 0; j < 3; j++) {
                x += table[i][j];
                y += table[j][i];
            }
            if(x == -3)
                return "A";
            else if(x == 3)
                return "B";
            if(y == -3)
                return "A";
            else if(y == 3)
                return "B";
            diagonal += table[i][i];
            diagona2 += table[i][2 - i];
        }
        if(diagonal == -3)
            return "A";
        else if(diagonal == 3)
            return "B";
        if(diagona2 == -3)
            return "A";
        else if(diagona2 == 3)
            return "B";
        return moves.length == 9?"Draw":"Pending";
    }

}
