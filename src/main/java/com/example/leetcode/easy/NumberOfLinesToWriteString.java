package com.example.leetcode.easy;

/**
 * We are to write the letters of a given string S, from left to right into lines.
 * Each line has maximum width 100 units, and if writing a letter would cause the width of the line to exceed 100 units,
 * it is written on the next line. We are given an array widths, an array where widths[0] is the width of 'a', widths[1] is the width of 'b', ..., and widths[25] is the width of 'z'.
 *
 * Now answer two questions: how many lines have at least one character from S,
 * and what is the width used by the last such line? Return your answer as an integer list of length 2.
 *
 *
 *
 * Example :
 * Input:
 * widths = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
 * S = "abcdefghijklmnopqrstuvwxyz"
 * Output: [3, 60]
 * Explanation:
 * All letters have the same length of 10. To write all 26 letters,
 * we need two full lines and one line with 60 units.
 * Example :
 * Input:
 * widths = [4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
 * S = "bbbcccdddaaa"
 * Output: [2, 4]
 * Explanation:
 * All letters except 'a' have the same length of 10, and
 * "bbbcccdddaa" will cover 9 * 10 + 2 * 4 = 98 units.
 * For the last 'a', it is written on the second line because
 * there is only 2 units left in the first line.
 * So the answer is 2 lines, plus 4 units in the second line.
 */
public class NumberOfLinesToWriteString {
    public static void main(String[] args) {
        int[] widths = new int[]{10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
        String s = "abcdefghijklmnopqrstuvwxyz";
        NumberOfLinesToWriteString numberOfLinesToWriteString = new NumberOfLinesToWriteString();
        int[] result = numberOfLinesToWriteString.numberOfLines(widths,s);
    }

    public int[] numberOfLines(int[] widths, String S) {
        int[] result = new int[2];
        int max = 100;
        result[0] = 1;
        for(char c : S.toCharArray()){
            if(max < widths[c-'a']){
                max = 100;
                result[0]++;
            }
            max -= widths[c-'a'];
        }
        result[1] = 100 - max;
        return result;
    }


    /**
     * less memory
     * @param widths
     * @param S
     * @return
     */
    public int[] numberOfLinesV3(int[] widths, String S) {
        int[] res = new int[2];
        int lineCount = 1;
        int widthCount = 0;
        for(char c : S.toCharArray()) {
            int charIndex = ((int) c) - 97;
            int charWidth = widths[charIndex];
            if(widthCount + charWidth > 100) {
                lineCount++;
                widthCount = charWidth;
            }
            else {
                widthCount += charWidth;
            }
        }
        res[0] = lineCount;
        res[1] = widthCount;
        return res;
    }
}
