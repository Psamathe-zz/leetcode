package com.example.leetcode.medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest.
 * If two words have the same frequency, then the word with the lower alphabetical order comes first.
 *
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 *     Note that "i" comes before "love" due to a lower alphabetical order.
 * Example 2:
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 *     with the number of occurrence being 4, 3, 2 and 1 respectively.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Input words contain only lowercase letters.
 * Follow up:
 * Try to solve it in O(n log k) time and O(n) extra space.
 */
public class TopKFrequentWords {
    public static void main(String[] args) {
        String[] words = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        TopKFrequentWords topKFrequentWords = new TopKFrequentWords();
        List<String> result = topKFrequentWords.topKFrequent(words,k);
        System.out.println(result);
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String,Integer> map = new HashMap<>();
        for (String str : words){
            map.put(str,map.getOrDefault(str,0) +  1);
        }
        return map.entrySet().stream().sorted(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue() != o2.getValue()){
                    return o2.getValue() - o1.getValue();
                } else {
                    return o1.getKey().compareTo(o2.getKey());
                }
            }
        }).map(e -> e.getKey()).limit(k).collect(Collectors.toList());
    }

    /**
     *
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequentV2(String[] words, int k) {
        Map<String, Integer> frequency = new HashMap();
        for(int i=0; i< words.length; i++){
            frequency.merge(words[i], 1, Integer::sum);
        }
        List<String> result = new ArrayList(frequency.keySet());
        Collections.sort(result, (w1, w2) -> frequency.get(w1).equals(frequency.get(w2)) ?
                w1.compareTo(w2) : frequency.get(w2) - frequency.get(w1));

        return result.subList(0, k);
    }
}
