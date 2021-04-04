package com.example.leetcode.biweeklycontest.contest49;

/**
 *You are given coordinates, a string that represents the coordinates of a square of the chessboard. Below is a chessboard for your reference.
 *
 *
 *
 * Return true if the square is white, and false if the square is black.
 *
 * The coordinate will always represent a valid chessboard square. The coordinate will always have the letter first, and the number second.
 *
 *
 *
 * Example 1:
 *
 * Input: coordinates = "a1"
 * Output: false
 * Explanation: From the chessboard above, the square with coordinates "a1" is black, so return false.
 * Example 2:
 *
 * Input: coordinates = "h3"
 * Output: true
 * Explanation: From the chessboard above, the square with coordinates "h3" is white, so return true.
 * Example 3:
 *
 * Input: coordinates = "c7"
 * Output: false
 */
public class DetermineColor {
    public static void main(String[] args) {

    }

    public boolean squareIsWhite(String coordinates) {
        int x = coordinates.charAt(0) - 'a';
        int y = coordinates.charAt(1) - '1';

        return x % 2 + y % 2 == 1;
    }
}
