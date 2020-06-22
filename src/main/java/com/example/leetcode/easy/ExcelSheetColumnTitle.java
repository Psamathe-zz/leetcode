package com.example.leetcode.easy;

/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 *
 * For example:
 *
 *     1 -> A
 *     2 -> B
 *     3 -> C
 *     ...
 *     26 -> Z
 *     27 -> AA
 *     28 -> AB
 *     ...
 * Example 1:
 *
 * Input: 1
 * Output: "A"
 * Example 2:
 *
 * Input: 28
 * Output: "AB"
 * Example 3:
 *
 * Input: 701
 * Output: "ZY"
 */
public class ExcelSheetColumnTitle {

    public static void main(String[] args) {
        int n = 28;
        ExcelSheetColumnTitle excelSheetColumnTitle = new ExcelSheetColumnTitle();
        excelSheetColumnTitle.convertToTitle(n);
    }

    public String convertToTitle(int n) {
        StringBuffer stringBuffer = new StringBuffer();

        while (n!=0){
            n = n - 1;
            stringBuffer.insert(0,Character.toChars(n%26  + 'A'));
            n=n/26;
        }

        return stringBuffer.toString();
    }
}
