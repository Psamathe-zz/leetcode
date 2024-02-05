package com.example.leetcode.weeklycontest.old.test205;

/**
 * Given a string s containing only lower case English letters and the '?' character, convert all the '?' characters into lower case letters such that the final string does not contain any consecutive repeating characters. You cannot modify the non '?' characters.
 *
 * It is guaranteed that there are no consecutive repeating characters in the given string except for '?'.
 *
 * Return the final string after all the conversions (possibly zero) have been made. If there is more than one solution, return any of them. It can be shown that an answer is always possible with the given constraints.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "?zs"
 * Output: "azs"
 * Explanation: There are 25 solutions for this problem. From "azs" to "yzs", all are valid. Only "z" is an invalid modification as the string will consist of consecutive repeating characters in "zzs".
 * Example 2:
 *
 * Input: s = "ubv?w"
 * Output: "ubvaw"
 * Explanation: There are 24 solutions for this problem. Only "v" and "w" are invalid modifications as the strings will consist of consecutive repeating characters in "ubvvw" and "ubvww".
 * Example 3:
 *
 * Input: s = "j?qg??b"
 * Output: "jaqgacb"
 * Example 4:
 *
 * Input: s = "??yw?ipkj?"
 * Output: "acywaipkja"
 *
 */
public class ReplaceAll {
    public static void main(String[] args) {
        ReplaceAll replaceAll = new ReplaceAll();
        String res = replaceAll.modifyString("?");
        System.out.println(res);
    }

    public String modifyString(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;

        if(length == 1 ){
            if(chars[0] == '?')
                chars[0] = 'a';
        } else {
            for (int i = 0; i < length; i++) {
                if (chars[i] == '?') {
                    if (i > 0 && i < length - 1) {
                        for (int j = 0; j < 26; j++) {
                            if ('a' + j != chars[i - 1] && 'a' + j != chars[i + 1]) {
                                chars[i] = (char) ('a' + j);
                                break;
                            }
                        }
                    } else if (i == 0) {
                        for (int j = 0; j < 26; j++) {
                            if ('a' + j != chars[i + 1]) {
                                chars[i] = (char) ('a' + j);
                                break;
                            }
                        }
                    } else {
                        for (int j = 0; j < 26; j++) {
                            if ('a' + j != chars[i - 1]) {
                                chars[i] = (char) ('a' + j);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return String.valueOf(chars);
    }


    /**
     * better solution
     */
    char[] alpha = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    public String modifyStringV1(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (arr[i] == '?') {
                for (char c : alpha) {
                    if ((i == 0 || arr[i-1] != c) && (i == n-1 || arr[i+1] != c)) {
                        arr[i] = c;
                        break;
                    }
                }
            }
        }
        return new String(arr);
    }
}
