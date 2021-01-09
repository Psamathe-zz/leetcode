package com.example.leetcode.challenge.test2020.september.week3;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word (last word means the last appearing word if we loop from left to right) in the string.
 *
 * If the last word does not exist, return 0.
 *
 * Note: A word is defined as a maximal substring consisting of non-space characters only.
 *
 * Example:
 *
 * Input: "Hello World"
 * Output: 5
 */
public class LengthLastWord {
    public static void main(String[] args) {

    }

    public int lengthOfLastWord(String s) {
        String[] strings = s.split(" ");
        int length = strings.length;
        if(length == 0)
            return 0;
        else {
            return strings[length-1].length();
        }

    }

    /**
     * faster solution
     * @param s
     * @return
     */
    public int lengthOfLastWordV1(String s) {
        int L = s.length();
        if (s == null || L == 0)
            return 0;

        char[] c = s.toCharArray();
        int cLen = c.length;

        boolean spaceAtEnd = true;
        int lastWordLen = 0;

        for (int i = cLen - 1; i >=0; i--) {
            if (c[i] != ' ') {
                lastWordLen++;
                spaceAtEnd = false;
            } else if (!spaceAtEnd) {
                break;
            }
        }
        return lastWordLen;
    }
}
