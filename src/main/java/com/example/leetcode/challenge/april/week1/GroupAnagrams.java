package com.example.leetcode.challenge.april.week1;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given an array of strings, group anagrams together.
 *
 * Example:
 *
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * Note:
 *
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        String[] input = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        List<List<String>> result = groupAnagrams.groupAnagramsV2(input);
        System.out.println(result);
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

    public List<List<String>> groupAnagramsV3(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) return res;
        Map<Integer, Integer> map = new HashMap<>();
        int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19,
                23, 29, 31, 37, 41, 43, 47, 53,
                59, 61, 67, 71, 73, 79, 83, 89,
                97, 101};
        for (String str : strs) {
            int p = 1;
            for (char c : str.toCharArray()) {
                p *= primes[c - 'a'];
            }
            List<String> list;
            if (map.containsKey(p)) {
                list = res.get(map.get(p));
            } else {
                list = new ArrayList<>();
                res.add(list);
                map.put(p, res.size() - 1);
            }
            list.add(str);
        }
        return res;
    }
}

