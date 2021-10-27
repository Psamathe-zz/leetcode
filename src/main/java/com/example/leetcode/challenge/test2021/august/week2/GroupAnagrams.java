package com.example.leetcode.challenge.test2021.august.week2;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * Example 2:
 *
 * Input: strs = [""]
 * Output: [[""]]
 * Example 3:
 *
 * Input: strs = ["a"]
 * Output: [["a"]]
 */
public class GroupAnagrams {
    public static void main(String[] args) {

    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> tempMap = Arrays.stream(strs).collect(Collectors.groupingBy(e ->{
            return  e.chars().mapToObj(i->(char)i).sorted().map(String::valueOf).collect(Collectors.joining());
        }));
        return tempMap.values().stream().collect(Collectors.toList());
    }


    /**
     * small memory
     * @param strs
     * @return
     */


    public List<List<String>> groupAnagramsV2(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> anagramGroup = new HashMap<>();
        for (String str : strs) {
            String key = buildKey(str);
            if (!anagramGroup.containsKey(key)) {
                anagramGroup.put(key, new ArrayList<String>());
            }
            anagramGroup.get(key).add(str);
        }
        return anagramGroup.values().stream().collect(Collectors.toList());
    }

    private String buildKey(String s) {
        char[] count = new char[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        return new String(count);
    }

    /**
     *  fastest
     * @param strs
     * @return
     */

    int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};

    public  List<List<String>> groupAnagramsV3(String[] strs) {

        if(strs==null || strs.length==0) return new ArrayList<>();

        Map<Long, List<String>> map = new HashMap<>();
        for(String s : strs){
            long key = getPrimeProduct(s);
            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }

    private long getPrimeProduct(String s) {
        long result=1;
        for(int i =0 ; i<s.length();i++){
            char c = s.charAt(i);
            int n = c - 'a';
            result = result*primes[n];
        }
        return result;
    }
}
