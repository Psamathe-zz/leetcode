package com.example.leetcode.challenge.July.week5;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * Example 2:
 *
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 */
public class WordBreakII {
    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList(new String[]{"cat", "cats", "and", "sand", "dog"});
        WordBreakII wordBreakII = new WordBreakII();
        List<String> res = wordBreakII.wordBreak(s,wordDict);
        System.out.println(res);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4576240.html
     */
    Map<String,List<String>> map;
    public List<String> wordBreak(String s, List<String> wordDict) {
        map = new HashMap<>();
        return helper(s,wordDict);
    }

    public List<String> helper(String s,List<String> wordDict){
        List<String> res = new ArrayList<>();
        if (map.containsKey(s))
            return map.get(s);
        if (s.isEmpty()) {
            res.add("");
            return res;
        }
        List<String> temp;
        for (String word : wordDict) {
            if ((word.length() <= s.length() && !s.substring(0, word.length()).equals(word)) || word.length() > s.length())
                continue;
            if(word.length() == s.length())
                res.add(word);
            else {
                temp = helper(s.substring(word.length()), wordDict);
                for (String str : temp) {
                    res.add(word + (str.isEmpty() ? "" : " ") + str);
                }
            }
        }
        map.put(s,res);
        return res;
    }


    //recursive

    /**
     * faster solution
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreakv2(String s, List<String> wordDict) {
        return helperv2(s, wordDict);
    }

    private List<String> helperv2(String s, List<String> wordDict) {
        if(map.containsKey(s)) {
            return map.get(s);
        }
        List<String> res = new ArrayList<>();

        if(s.isEmpty()) {
            res.add(""); // as when no match, it will be empty list
            return res;
        }
        List<String> tmp;
        for(String w: wordDict){
            if(s.startsWith(w)){
                tmp = helperv2(s.substring(w.length()), wordDict);
                for(String l: tmp) {
                    res.add(w+ (l.isEmpty()? "" :(" " + l)));
                }
            }
        }
        map.put(s, res);
        return res;
    }
}
