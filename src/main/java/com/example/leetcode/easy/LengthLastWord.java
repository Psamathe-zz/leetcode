package com.example.leetcode.easy;

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
        String s = "   ";
        LengthLastWord lengthLastWord = new LengthLastWord();
        int result = lengthLastWord.lengthOfLastWord(s);
        System.out.println(result);
    }
    public int lengthOfLastWord(String s) {
        String[] strs = s.split(" ");
        if(strs.length == 0)
            return 0;
        return strs[strs.length - 1].length();
    }

    public int lengthOfLastWordV1(String s) {
        int result = 0;
        char[] chars = s.trim().toCharArray();

        for(int i = chars.length - 1; i>=0;i--){
            if(chars[i] == ' '){
                break;
            } else {
                result++;
            }
        }
        return result;
    }
}
