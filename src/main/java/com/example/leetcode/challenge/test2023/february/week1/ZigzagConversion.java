package com.example.leetcode.challenge.test2023.february.week1;

import java.util.ArrayList;
import java.util.List;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 *
 *
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * Example 3:
 *
 * Input: s = "A", numRows = 1
 * Output: "A"
 */
public class ZigzagConversion {
    public static void main(String[] args) {

    }
    public String convert(String s, int numRows) {
        if(numRows == 1)
            return s;
        List<StringBuilder> stringBuilderList = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            stringBuilderList.add(new StringBuilder());
        }
        int length = s.length();
        int index = 0;
        boolean down = true;
        for (int i = 0; i < length; i++) {
            stringBuilderList.get(index).append(s.charAt(i));
            if(index == 0) {
                index = 1;
                down = true;
            } else if(index == numRows - 1) {
                index = numRows - 2;
                down = false;
            } else if(down) {
                index++;
            } else {
                index--;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            stringBuilder.append(stringBuilderList.get(i));
        }
        return stringBuilder.toString();
    }

}
