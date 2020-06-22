package com.example.leetcode.medium;

import java.util.*;

/**
 * Given a list of words, each word consists of English lowercase letters.
 *
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".
 *
 * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.
 *
 * Return the longest possible length of a word chain with words chosen from the given list of words.
 *
 *
 *
 * Example 1:
 *
 * Input: ["a","b","ba","bca","bda","bdca"]
 * Output: 4
 * Explanation: one of the longest word chain is "a","ba","bda","bdca".
 *
 * ["ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"]
 */
public class LongestStringChain {
    public static void main(String[] args) {
        String[] words = new String[]{"ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"};
        LongestStringChain longestStringChain = new LongestStringChain();
        int result = longestStringChain.longestStrChain(words);
        System.out.println(result);
    }

    public int longestStrChain(String[] words) {
        int result = 0;
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        Map<String,List<String>> map = new HashMap<>();

        for(String word : words){
            for(Map.Entry<String,List<String>> entry : map.entrySet()){
                boolean test = isPredecessor(entry.getKey(),word);
                if(test){
                    entry.getValue().add(word);
                }
            }
            if(!map.containsKey(word)){
                map.put(word,new ArrayList<>());
            }
        }
        for(String word: words){
            int temp = findLength(word,map);
            if(result < temp){
                result = temp;
            }
        }

        return result;
    }

    public int findLength(String word, Map<String,List<String>> map){
        int result = 1;
        for(String next : map.get(word)){
            int temp = findLength(next,map) + 1;
            if(result < temp){
                result = temp;
            }
        }
        return result;
    }

    public boolean isPredecessor(String target, String toPredecessor){
        if(target.length() + 1 != toPredecessor.length()){
            return false;
        }
        int indexTarget = 0;
        int index;
        for (index = 0; index < toPredecessor.length(); index++) {
            if(indexTarget < target.length() && target.charAt(indexTarget) == toPredecessor.charAt(index) ){
                indexTarget++;
            }
        }
        return index-indexTarget==1;
    }


    /**
     * faster solution
     * @param words
     * @return
     */
    public int longestStrChainV2(String[] words) {
        if (words.length < 2) {
            return words.length;
        }
        int maxLen = 0;
        List<String>[] map = new List[17];
        for (int i = 0; i < 17; i++) {
            map[i] = new ArrayList<>();
        }
        // O(N); N = num of words
        for (String word : words) {
            int len = word.length();
            map[len].add(word);
            maxLen = Math.max(len, maxLen);
        }
        int longestChain = 0;
        for (int j = maxLen; j > 1 && j > longestChain; j--) {
            longestChain = Math.max(longestChain, calculateMaxDepth(map, j, null));
        }
        return longestChain;
    }

    private int calculateMaxDepth(List<String>[] map, int len, String prev) {
        List<String> words = map[len];
        if (words.size() == 0) {
            return 0;
        }
        int max = 0;
        for (String s : words) {
            if (isPredecessorV2(s, prev)) {
                int depth = calculateMaxDepth(map, len - 1, s);
                max = Math.max(max, depth + 1);
            }
        }
        return max;
    }

    private boolean isPredecessorV2(String word, String prev) {
        if (prev == null) return true;
        int i = 0, j = 0;
        while (i < prev.length() && j < word.length()) {
            if (prev.charAt(i) == word.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
                if (i - j > 1) return false;
            }
        }
        return i - j <= 1;
    }
}
