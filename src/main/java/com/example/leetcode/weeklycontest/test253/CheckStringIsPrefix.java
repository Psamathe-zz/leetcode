package com.example.leetcode.weeklycontest.test253;


/**
 * Given a string s and an array of strings words, determine whether s is a prefix string of words.
 *
 * A string s is a prefix string of words if s can be made by concatenating the first k strings in words for some positive k no larger than words.length.
 *
 * Return true if s is a prefix string of words, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "iloveleetcode", words = ["i","love","leetcode","apples"]
 * Output: true
 * Explanation:
 * s can be made by concatenating "i", "love", and "leetcode" together.
 * Example 2:
 *
 * Input: s = "iloveleetcode", words = ["apples","i","love","leetcode"]
 * Output: false
 * Explanation:
 * It is impossible to make s using a prefix of arr.
 * "ileetcode"
 * ["i","love","leetcode","apples"]
 * "aaa"
 * ["aa","aaa","fjaklfj"]
 */
public class CheckStringIsPrefix {
    public static void main(String[] args) {
        CheckStringIsPrefix checkStringIsPrefix = new CheckStringIsPrefix();
        boolean res = checkStringIsPrefix.isPrefixString("iloveleetcode", new String[]{"aa","aaa","fjaklfj"});
        System.out.println(res);
    }

    public boolean isPrefixString(String s, String[] words) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String word: words){
            stringBuilder.append(word);
            if(s.equals(stringBuilder.toString()))
                return true;
        }
        return false;
    }
}
