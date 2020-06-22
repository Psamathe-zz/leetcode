package com.example.leetcode.weeklycontest.test186;

/**
 * Given a string s of zeros and ones, return the maximum score after splitting the string into two non-empty substrings (i.e. left substring and right substring).
 *
 * The score after splitting a string is the number of zeros in the left substring plus the number of ones in the right substring.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "011101"
 * Output: 5
 * Explanation:
 * All possible ways of splitting s into two non-empty substrings are:
 * left = "0" and right = "11101", score = 1 + 4 = 5
 * left = "01" and right = "1101", score = 1 + 3 = 4
 * left = "011" and right = "101", score = 1 + 2 = 3
 * left = "0111" and right = "01", score = 1 + 1 = 2
 * left = "01110" and right = "1", score = 2 + 1 = 3
 * Example 2:
 *
 * Input: s = "00111"
 * Output: 5
 * Explanation: When left = "00" and right = "111", we get the maximum score = 2 + 3 = 5
 * Example 3:
 *
 * Input: s = "1111"
 * Output: 3
 */
public class MaximumScoreAfterSplittingString {

    public static void main(String[] args) {
        String input = "00111";
        MaximumScoreAfterSplittingString maximumScoreAfterSplittingString = new MaximumScoreAfterSplittingString();
        int result = maximumScoreAfterSplittingString.maxScore(input);
        System.out.println(result);
    }

    public int maxScore(String s) {
        int temp = 0;
        int oneNumber = 0;
        int result = 0;
        char[] chars = s.toCharArray();
        for(char c : chars){
            if(c == '1')
                oneNumber++;
        }
        temp = oneNumber;
        for (int i = 0; i < chars.length -1; i++) {
            if(chars[i] == '0'){
                temp++;
            } else
                temp--;
            if(result < temp)
                result = temp;
        }

        return result;
    }


    /**
     * faster solution
     * @param s
     * @return
     */
    public int maxScoreV2(String s) {

        if(s.length() == 0)
            return 0;

        int max = 0, left = 0, right = 1;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '0')
                ++left;
            else
                --right;
            if(i != s.length() - 1)
                max = Math.max(max, left + right);
        }

        return max- right;
    }
}
