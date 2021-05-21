package com.example.leetcode.challenge.test2021.may.week3;


import java.util.*;

/**
 * Given a list of strings words and a string pattern, return a list of words[i] that match pattern. You may return the answer in any order.
 *
 * A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.
 *
 * Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * Output: ["mee","aqq"]
 * Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
 * "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation, since a and b map to the same letter.
 * Example 2:
 *
 * Input: words = ["a","b","c"], pattern = "a"
 * Output: ["a","b","c"]
 *
 *
 * Constraints:
 *
 * 1 <= pattern.length <= 20
 * 1 <= words.length <= 50
 * words[i].length == pattern.length
 * pattern and words[i] are lowercase English letters.
 */
public class FindReplacePattern {
    public static void main(String[] args) {
        FindReplacePattern findReplacePattern = new FindReplacePattern();
        findReplacePattern.findAndReplacePattern(new String[]{"abc","deq","mee","aqq","dkd","ccc"}, "abb");
    }

    int[] patternCount;
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        patternCount = convert(pattern);
        List<String> res = new ArrayList<>();
        for (String word : words){
            if(word.length() == pattern.length() && match(word, pattern)){
                res.add(word);
            }
        }
        return res;
    }

    private int[] convert(String word){
        int[] count = new int[26];
        for (char c : word.toCharArray()){
            count[c - 'a']++;
        }
        return count;
    }

    private boolean match(String word, String pattern){
        int[] count = Arrays.copyOf(patternCount,26);
        Map<Character,Character> map = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            char c1 = pattern.charAt(i);
            if(map.containsKey(c)){
                if(count[map.get(c) - 'a'] == 0)
                    return false;
                count[map.get(c) - 'a']--;
            } else {
                if(map.values().contains(c1))
                    return false;
                map.put(c, c1);
                if(count[map.get(c) - 'a'] == 0)
                    return false;
                count[map.get(c) - 'a']--;
            }
        }
        return true;
    }


    /**
     * faster solution
     * @param words
     * @param pattern
     * @return
     */
    public List<String> findAndReplacePatternV1(String[] words, String pattern) {
        List<String> list = new ArrayList();
        for(String s: words){
            if(s.length()!=pattern.length())continue;
            if(check(s,pattern))list.add(s);
        }
        return list;
    }

    public boolean check(String word, String pattern){
        Map<Character, Character> M = new HashMap();
        for (int i = 0; i < word.length(); ++i) {
            char w = word.charAt(i);
            char p = pattern.charAt(i);
            if (!M.containsKey(w)) M.put(w, p);
            if (M.get(w) != p) return false;
        }

        boolean[] seen = new boolean[26];
        for (char p: M.values()) {
            if (seen[p - 'a'])
                return false;
            seen[p - 'a'] = true;
        }
        return true;
    }
}
