package com.example.leetcode.biweeklycontest.old.contest52;


import java.util.Arrays;

/**
 * You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:
 *
 * A stone '#'
 * A stationary obstacle '*'
 * Empty '.'
 * The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.
 *
 * It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
 *
 * Return an n x m matrix representing the box after the rotation described above.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: box = [["#",".","#"]]
 * Output: [["."],
 *          ["#"],
 *          ["#"]]
 * Example 2:
 *
 *
 *
 * Input: box = [["#",".","*","."],
 *               ["#","#","*","."]]
 * Output: [["#","."],
 *          ["#","#"],
 *          ["*","*"],
 *          [".","."]]
 * Example 3:
 *
 *
 *
 * Input: box = [["#","#","*",".","*","."],
 *               ["#","#","#","*",".","."],
 *               ["#","#","#",".","#","."]]
 * Output: [[".","#","#"],
 *          [".","#","#"],
 *          ["#","#","*"],
 *          ["#","*","."],
 *          ["#",".","*"],
 *          ["#",".","."]]
 */
public class RotatingBox {
    public static void main(String[] args) {

    }

    public char[][] rotateTheBox(char[][] box) {
        int u = box.length;
        int v = box[0].length;

        char[][] res = new char[v][u];
        for (int i = 0; i < v; i++) {
            Arrays.fill(res[i],'.');
        }
        int available;
        for (int i = 0; i < u; i++) {
            available = v - 1;
            for (int j = v - 1; j >= 0 ; j--) {
                if(box[i][j] == '*'){
                    res[j][u-1-i] = '*';
                    available = j - 1;
                } else if(box[i][j] == '#'){
                    res[available][u-1-i] = '#';
                    available -= 1;
                }
            }
        }
        return res;
    }
}
