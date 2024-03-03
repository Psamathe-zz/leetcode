package com.example.leetcode.biweeklycontest.old.contest49;


import java.util.*;
import java.util.stream.Collectors;

/**
 * A sentence is a list of words that are separated by a single space with no leading or trailing spaces. For example, "Hello World", "HELLO", "hello world hello world" are all sentences. Words consist of only uppercase and lowercase English letters.
 *
 * Two sentences sentence1 and sentence2 are similar if it is possible to insert an arbitrary sentence (possibly empty) inside one of these sentences such that the two sentences become equal. For example, sentence1 = "Hello my name is Jane" and sentence2 = "Hello Jane" can be made equal by inserting "my name is" between "Hello" and "Jane" in sentence2.
 *
 * Given two sentences sentence1 and sentence2, return true if sentence1 and sentence2 are similar. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: sentence1 = "My name is Haley", sentence2 = "My Haley"
 * Output: true
 * Explanation: sentence2 can be turned to sentence1 by inserting "name is" between "My" and "Haley".
 * Example 2:
 *
 * Input: sentence1 = "of", sentence2 = "A lot of words"
 * Output: false
 * Explanation: No single sentence can be inserted inside one of the sentences to make it equal to the other.
 * Example 3:
 *
 * Input: sentence1 = "Eating right now", sentence2 = "Eating"
 * Output: true
 * Explanation: sentence2 can be turned to sentence1 by inserting "right now" at the end of the sentence.
 * Example 4:
 *
 * Input: sentence1 = "Luky", sentence2 = "Lucccky"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= sentence1.length, sentence2.length <= 100
 * sentence1 and sentence2 consist of lowercase and uppercase English letters and spaces.
 * The words in sentence1 and sentence2 are separated by a single space.
 * "Eating right now"
 * "Eating"
 * "CwFfRo regR"
 * "CwFfRo H regR"
 */
public class SentenceSimilarityIII {
    public static void main(String[] args) {
        SentenceSimilarityIII sentenceSimilarityIII = new SentenceSimilarityIII();
        boolean res = sentenceSimilarityIII.areSentencesSimilar("My name is Haley","My Haley");
        System.out.println(res);
    }

    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        if(sentence1.length() <= sentence2.length())
            return helper(sentence1,sentence2);
        else
            return helper(sentence2,sentence1);
    }

    public boolean helper(String sentence1, String sentence2){
        Deque<String> deque1 = new LinkedList<>();
        Deque<String> deque2 = new LinkedList<>();
        for(String str : sentence1.split(" ")){
            deque1.addLast(str);
        }
        for(String str : sentence2.split(" ")){
            deque2.addLast(str);
        }
        if(deque1.size() > deque2.size())
            return false;
        while (!deque1.isEmpty() && !deque2.isEmpty() && deque1.peekFirst().equals(deque2.peekFirst())){
            deque1.pollFirst();
            deque2.pollFirst();
        }

        while (!deque1.isEmpty() && !deque2.isEmpty() && deque1.peekLast().equals(deque2.peekLast())){
            deque1.pollLast();
            deque2.pollLast();
        }
        return deque1.isEmpty();
    }
}
