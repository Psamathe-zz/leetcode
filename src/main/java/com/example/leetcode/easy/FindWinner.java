package com.example.leetcode.easy;

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
 * [[0,2],[2,0],[2,1],[0,1],[1,2]]
 * {{0,2},{2,0},{2,1},{0,1},{1,2}}
 * "A"
 * Expected
 * "Draw"
 */
public class FindWinner {
    public static void main(String[] args) {
        int[][] moves = new int[][]{{0,2},{2,0},{2,1},{0,1},{1,2}};
        FindWinner findWinner = new FindWinner();
        String result = findWinner.tictactoe(moves);
        System.out.println(result);
    }

    public String tictactoe(int[][] moves) {
        int[][] map = new int[3][3];
        for(int i = 0; i < moves.length; i++){
            if(i%2 == 0){
                map[moves[i][0]][moves[i][1]] = 1;
            } else {
                map[moves[i][0]][moves[i][1]] = 2;
            }
        }
        int win = 0;
        for (int i = 0; i < 3; i++) {
            if(map[i][0]==map[i][1]&&map[i][1]==map[i][2]&&map[i][1]!=0) // row
                win = map[i][0];
            if(map[0][i]==map[1][i]&&map[1][i]==map[2][i]&&map[1][i]!=0) // col
                win = map[0][i];

        }

        if((map[0][0]==map[1][1] && map[0][0]==map[2][2] && map[0][0] !=0) || (map[0][2]==map[1][1] && map[2][0]==map[1][1] && map[1][1] !=0))  // col
            win = map[1][1] ;
        if(win == 1){
            return "A";
        } else if(win == 2){
            return "B";
        } else if(win == 0 && moves.length>=9){
            return "Draw";
        } else {
            return "Pending";
        }
    }
}
