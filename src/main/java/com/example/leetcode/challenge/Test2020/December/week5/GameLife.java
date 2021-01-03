package com.example.leetcode.challenge.Test2020.December.week5;


/**
 * According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 * Example 2:
 *
 *
 * Input: board = [[1,1],[1,0]]
 * Output: [[1,1],[1,1]]
 */
public class GameLife {
    public static void main(String[] args) {

    }

    public void gameOfLife(int[][] board) {
        int u = board.length;
        int v = board[0].length;

        int[][] temp = new int[u+2][v+2];
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                temp[i+1][j+1] = board[i][j];
            }
        }
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                if(getRes(i + 1, j + 1, temp)){
                    board[i][j] = 1;
                } else
                    board[i][j] = 0;
            }
        }
    }

    int[][] dir = {{1,0}, {0, 1}, {-1, 0}, {0, -1}, {1,1}, {-1,-1}, {-1,1}, {1,-1}};
    public boolean getRes(int x, int y, int[][] temp ){
        int boardCount = 0;
        for(int i = 0; i < dir.length; i++) {
            boardCount += temp[x+dir[i][0]][y+dir[i][1]];
        }
        if(temp[x][y] == 1){
            if(boardCount < 2 || boardCount > 3)
                return false;
            else
                return true;
        } else {
            if(boardCount == 3)
                return true;
            else
                return false;
        }
    }

    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,1},{-1,-1},{1,-1}};

    public void gameOfLifeV1(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int live = 0;
                for(int[] dir : dirs){
                    int p = dir[0] + i;
                    int q = dir[1] + j;
                    if(p < 0 || q < 0 || p >= m || q >= n) continue;
                    if(board[p][q] == 1 || board[p][q] == 2) live++;
                }
                if(board[i][j] == 0 && live == 3) board[i][j] = 3;
                if(board[i][j] == 1 && (live < 2 || live > 3)) board[i][j] = 2;
            }
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < n; j++){
                board[i][j] %= 2;
            }
        }
 