package com.example.leetcode.easy;

import java.util.*;

/**
 * Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s.
 * For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.
 *
 * Now, given string arrays queries and words, return an integer array answer, where each answer[i] is the number of words such that f(queries[i]) < f(W),
 * where W is a word in words.
 *
 *
 *
 * Example 1:
 *
 * Input: queries = ["cbd"], words = ["zaaaz"]
 * Output: [1]
 * Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").
 * Example 2:
 *
 * Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
 * Output: [1,2]
 * Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are both > f("cc").
 * ["bba","abaaaaaa","aaaaaa","bbabbabaab","aba","aa","baab","bbbbbb","aab","bbabbaabb"]
 * ["aaabbb","aab","babbab","babbbb","b","bbbbbbbbab","a","bbbbbbbbbb","baaabbaab","aa"]
 */
public class CompareStringsFrequencySmallestCharacter {
    public static void main(String[] args) {
        String[] queries = new String[]{"bba","abaaaaaa","aaaaaa","bbabbabaab","aba","aa","baab","bbbbbb","aab","bbabbaabb"};
        String[] words = new String[]{"aaabbb","aab","babbab","babbbb","b","bbbbbbbbab","a","bbbbbbbbbb","baaabbaab","aa"};
        CompareStringsFrequencySmallestCharacter compareStringsFrequencySmallestCharacter = new CompareStringsFrequencySmallestCharacter();
        int[] result = compareStringsFrequencySmallestCharacter.numSmallerByFrequency(queries,words);
        System.out.println(result);
    }
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] result = new int[queries.length];
        int[] wordLength = new int[words.length];
        int index = 0;
        for(String word : words){
            wordLength[index] = f(word);
            index++;
        }
        Arrays.sort(wordLength);
        int temp;
        int f;
        index = 0;
        for(String query : queries){
            temp = f(query);
            f = 0;
            for(int value : wordLength){
                if(value <= temp){
                    f++;
                } else {
                    break;
                }
            }
            result[index] = words.length - f;
            index++;
        }
        return result;
    }
    int f(String word){
        int value;
        TreeMap<Character,Integer> map = new TreeMap<>();
        for(Character c: word.toCharArray()){
            value = map.getOrDefault(c,0)+1;
            map.put(c,value);
        }
        return map.get(map.firstKey());
    }
}
