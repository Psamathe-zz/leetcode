package com.example.leetcode.challenge.test2021.april.week2;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.
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
 *
 * ["kuvp","q"]
 * "ngxlkthsjuoqcpavbfdermiywz"
 */
public class VerifyingAlienDictionary {
    public static void main(String[] args) {
        VerifyingAlienDictionary verifyingAlienDictionary = new VerifyingAlienDictionary();
        String[] words = new String[]{"kuvp","q"};
        String order = "ngxlkthsjuoqcpavbfdermiywz";
        boolean res = verifyingAlienDictionary.isAlienSorted(words, order);
        System.out.println(res);
    }

    public boolean isAlienSorted(String[] words, String order) {
        if(words.length == 1)
            return true;
        Queue<String> queue = new LinkedList<>();
        char[] chars = new char[26];
        for (int i = 0; i < 26; i++) {
            chars[order.charAt(i) - 'a'] = (char)(i + 'a');
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String word : words){
            for( char c : word.toCharArray()){
                stringBuilder.append(chars[c - 'a']);
            }
            queue.add(stringBuilder.toString());
            stringBuilder.setLength(0);
        }
        String cur = queue.poll();
        while (!queue.isEmpty()){
            if(cur.compareTo(queue.peek()) > 0){
                return false;
            }
            cur = queue.poll();
        }
        return true;
    }
}
