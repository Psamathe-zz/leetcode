package com.example.leetcode.challenge.test2021.may.week5;


import java.util.ArrayList;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 *
 */
public class NQueensII {
    public static void main(String[] args) {

    }


    List<List<String>> res = new ArrayList<>();

    public int totalNQueens(int n) {
        ArrayList<StringBuilder> track = new ArrayList<>();
        for(int i = 0; i < n; i++){
            StringBuilder str = new StringBuilder();
            for(int j = 0; j < n; j++){
                str.append('.');
            }
            track.add(str);
        }
        backtrace(track, 0);
        return res.size();
    }

    void backtrace(ArrayList<StringBuilder> track, int row){
        if(row == track.size()){
            ArrayList<String> track1 = new ArrayList<>();
            for(int i = 0; i < track.size(); i++){
                track1.add(track.get(i).toString());
            }
            res.add(track1);
            return;
        }

        int n = track.get(row).length();
        for(int col = 0; col < n; col++){
            if(!isValid(track,row,col))
                continue;
            track.get(row).setCharAt(col,'Q');
            backtrace(track, row+1);
            track.get(row).setCharAt(col,'.');
        }
    }
    boolean isValid(ArrayList<StringBuilder> track, int row, int col){
        int  n = track.size();
        for(int i = 0; i < n; i++){
            if(track.get(i).charAt(col) == 'Q')
                return false;
        }
        for(int i = row-1, j = col+1; i>=0 && j <n; i--,j++){
            if(track.get(i).charAt(j) == 'Q')
                return false;
        }
        for(int i= row-1, j = col-1; i>=0 && j >=0; i--,j--){
            if(track.get(i).charAt(j) == 'Q')
                return false;
        }
        return true;
    }

}
