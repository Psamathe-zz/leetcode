package com.example.leetcode.weeklycontest.old.test260;


/**
 * You are given an m x n matrix board, representing the current state of a crossword puzzle. The crossword contains lowercase English letters (from solved words), ' ' to represent any empty cells, and '#' to represent any blocked cells.
 *
 * A word can be placed horizontally (left to right or right to left) or vertically (top to bottom or bottom to top) in the board if:
 *
 * It does not occupy a cell containing the character '#'.
 * The cell each letter is placed in must either be ' ' (empty) or match the letter already on the board.
 * There must not be any empty cells ' ' or other lowercase letters directly left or right of the word if the word was placed horizontally.
 * There must not be any empty cells ' ' or other lowercase letters directly above or below the word if the word was placed vertically.
 * Given a string word, return true if word can be placed in board, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [['#', ' ', '#'], [' ', ' ', '#'], ['#', 'c', ' ']], word = 'abc'
 * Output: true
 * Explanation: The word 'abc' can be placed as shown above (top to bottom).
 * Example 2:
 *
 *
 * Input: board = [[' ', '#', 'a'], [' ', '#', 'c'], [' ', '#', 'a']], word = 'ac'
 * Output: false
 * Explanation: It is impossible to place the word because there will always be a space/letter above or below it.
 * Example 3:
 *
 *
 * Input: board = [['#', ' ', '#'], [' ', ' ', '#'], ['#', ' ', 'c']], word = 'ca'
 * Output: true
 * Explanation: The word 'ca' can be placed as shown above (right to left).
 *
 */
public class CheckWord {
    public static void main(String[] args) {
        CheckWord checkWord = new CheckWord();
        checkWord.placeWordInCrossword(new char[][]{
                {' ', '#', 'a'},
                {' ', '#', 'c'},
                {' ', '#', 'a'}
        },"");
    }

    int row;
    int col;
    char[][] b;
    char[] w;
    int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int l;
    public boolean placeWordInCrossword(char[][] board, String word) {
        row = board.length;
        col = board[0].length;
        b = board;
        w = word.toCharArray();
        l = word.length();
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                // ' '或与word首字符相同 则进行check
                if(!(board[i][j] == ' ' || board[i][j] == w[0]))
                    continue;
                if(check(i, j))
                    return true;
            }
        }
        return false;
    }
    public boolean check(int x, int y) {
        for(int i = 0; i < 4; i++) {
            // 末位字符不超边界
            int desX = x + (l - 1) * move[i][0];
            int desY = y + (l - 1) * move[i][1];
            if(desX < 0 || desX >= row || desY < 0 || desY >= col)
                continue;
            // 首位前和末位后 超边界或为障碍才可继续
            int nx = desX + move[i][0];
            int ny = desY + move[i][1];
            int mx = x - move[i][0];
            int my = y - move[i][1];
            if(!((nx < 0 || nx >= row || ny < 0 || ny >= col) || b[nx][ny] == '#')) continue;
            if(!((mx < 0 || mx >= row || my < 0 || my >= col) || b[mx][my] == '#')) continue;
            // 从x,y匹配到desX,desY
            int idx = 1;
            int curX = x;
            int curY = y;
            boolean isOK = true;
            for(int j = 1; j < l; j++) {
                curX += move[i][0];
                curY += move[i][1];
                // ' ' 或与 word[idx] 相同
                if(b[curX][curY] == ' ' || b[curX][curY] == w[idx])
                    idx++;
                else {
                    isOK = false;
                    break;
                }
            }
            if(isOK) return true;
        }
        return false;
    }

}
