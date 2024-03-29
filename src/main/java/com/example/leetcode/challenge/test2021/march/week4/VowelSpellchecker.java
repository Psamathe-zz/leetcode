package com.example.leetcode.challenge.test2021.march.week4;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
 *
 * For a given query word, the spell checker handles two categories of spelling mistakes:
 *
 * Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.
 * Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
 * Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
 * Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
 * Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
 * Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
 * Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
 * Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
 * In addition, the spell checker operates under the following precedence rules:
 *
 * When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
 * When the query matches a word up to capitlization, you should return the first such match in the wordlist.
 * When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
 * If the query has no matches in the wordlist, you should return the empty string.
 * Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].
 *
 *
 *
 * Example 1:
 *
 * Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
 * Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
 *
 *
 * Note:
 *
 * 1 <= wordlist.length <= 5000
 * 1 <= queries.length <= 5000
 * 1 <= wordlist[i].length <= 7
 * 1 <= queries[i].length <= 7
 * All strings in wordlist and queries consist only of english letters.
 */
public class VowelSpellchecker {
    public static void main(String[] args) {
        VowelSpellchecker vowelSpellchecker = new VowelSpellchecker();
        vowelSpellchecker.spellchecker(new String[]{"KiTe","kite","hare","Hare"}, new String[]{"kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"});
    }

    public String[] spellchecker(String[] wordlist, String[] queries) {
        int length = wordlist.length;
        String[] res = new String[queries.length];
        Set<String> set = Arrays.stream(wordlist).collect(Collectors.toSet());
        HashMap<String, String> map1 = new HashMap<String, String>();
        HashMap<String, String> map2 = new HashMap<String, String>();
        for (int i = 0; i < length; i++) {
            String word = wordlist[i];
            String wordLowerCase = word.toLowerCase();
            String wordReplaceVowel = wordLowerCase.replaceAll("[a,o,e,i,u]", "*");
            map1.compute(wordLowerCase, (k,v) -> {
                if(v == null){
                    v = word;
                }
                return v;
            });
            map2.compute(wordReplaceVowel, (k,v) -> {
                if(v == null){
                    v = word;
                }
                return v;
            });
        }
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            String queryLowerCase = query.toLowerCase();
            String queryReplaceVowel = queryLowerCase.replaceAll("[a,o,e,i,u]", "*");
            if(set.contains(query)){
                res[i] = query;
            } else if(map1.containsKey(queryLowerCase)){
                res[i] = map1.get(queryLowerCase);
            } else if(map2.containsKey(queryReplaceVowel)){
                res[i] = map2.get(queryReplaceVowel);
            } else {
                res[i] = "";
            }
        }
        return res;
    }


    Set<String> words_perfect;
    Map<String, String> words_cap;
    Map<String, String> words_vow;

    public String[] spellcheckerV1(String[] wordlist, String[] queries) {
        words_perfect = new HashSet();
        words_cap = new HashMap();
        words_vow = new HashMap();

        for (String word: wordlist) {
            words_perfect.add(word);

            String wordlow = word.toLowerCase();
            words_cap.putIfAbsent(wordlow, word);

            String wordlowDV = devowel(wordlow);
            words_vow.putIfAbsent(wordlowDV, word);
        }

        String[] ans = new String[queries.length];
        int t = 0;
        for (String query: queries)
            ans[t++] = solve(query);
        return ans;
    }

    public String solve(String query) {
        if (words_perfect.contains(query))
            return query;

        String queryL = query.toLowerCase();
        if (words_cap.containsKey(queryL))
            return words_cap.get(queryL);

        String queryLV = devowel(queryL);
        if (words_vow.containsKey(queryLV))
            return words_vow.get(queryLV);

        return "";
    }

    public String devowel(String word) {
        StringBuilder ans = new StringBuilder();
        for (char c: word.toCharArray())
            ans.append(isVowel(c) ? '*' : c);
        return ans.toString();
    }

    public boolean isVowel(char c) {
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }
}
