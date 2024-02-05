package com.example.leetcode.challenge.test2023.february.week1;

/**
 * In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 * Example 2:
 *
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 * Example 3:
 *
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 */
public class VerifyingAlienDictionary {
    public static void main(String[] args) {
        VerifyingAlienDictionary verifyingAlienDictionary = new VerifyingAlienDictionary();
        boolean worldabcefghijkmnpqstuvxyz =
                verifyingAlienDictionary.isAlienSorted(new String[]{"kxvyx","ts"}, "tiwjpbemkhalsqzdvucorfgxyn");
        System.out.println(worldabcefghijkmnpqstuvxyz);
    }

    int[] chars = new int[26];
    public boolean isAlienSorted(String[] words, String order) {
        if(words.length == 1)
            return true;
        populateChars(order);
        String pre = convert(words[0]);
        String cur;
        for (int i = 1; i < words.length; i++) {
            cur = convert(words[i]);
            if(pre.compareTo(cur) > 0) {
                return false;
            }
            pre = cur;
        }
        return true;
    }

    private void populateChars(String order) {
        for (int i = 0; i < order.length(); i++) {
            chars[order.charAt(i) - 'a'] = i;
        }
    }

    private String convert(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            stringBuilder.append((char)(chars[input.charAt(i) - 'a'] + 'a'));
        }
        return stringBuilder.toString();
    }
}
