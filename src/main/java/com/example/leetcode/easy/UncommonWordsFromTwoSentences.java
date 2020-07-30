package com.example.leetcode.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)
 *
 * A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
 *
 * Return a list of all uncommon words.
 *
 * You may return the list in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: A = "this apple is sweet", B = "this apple is sour"
 * Output: ["sweet","sour"]
 * Example 2:
 *
 * Input: A = "apple apple", B = "banana"
 * Output: ["banana"]
 *
 *
 * Note:
 *
 * 0 <= A.length <= 200
 * 0 <= B.length <= 200
 * A and B both contain only spaces and lowercase letters.
 */
public class UncommonWordsFromTwoSentences {
    public static void main(String[] args) {

    }

    public String[] uncommonFromSentences(String A, String B) {
        Map<String,Integer> count = new HashMap<>();

        for(String s : A.split(" ")){
            count.put(s,count.getOrDefault(s,0)+1);
        }
        for(String s : B.split(" ")){
            count.put(s,count.getOrDefault(s,0)+1);
        }

        return  count.entrySet().stream().filter(e->e.getValue() == 1).map(e->e.getKey()).collect(Collectors.toList()).toArray(new String[0]);
    }
}
