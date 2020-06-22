package com.example.leetcode.challenge.may.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 *
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 *
 * The order of output does not matter.
 *
 * Example 1:
 *
 * Input:
 * s: "cbaebabacd" p: "abc"
 *
 * Output:
 * [0, 6]
 *
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input:
 * s: "abab" p: "ab"
 *
 * Output:
 * [0, 1, 2]
 *
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class FindAllAnagramsString {
    public static void main(String[] args) {
        String s ="cbaebabacd";
        String g ="abc";
        FindAllAnagramsString findAllAnagramsString = new FindAllAnagramsString();
        List<Integer> reult = findAllAnagramsString.findAnagramsV2(s,g);
        System.out.println(reult);
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int[] map = new int[26];
        int[] mapTemp = new int[26];

        int length1 = s.length();
        int length2 = p.length();
        for(int i = 0; i < length2; i++){
            map[p.charAt(i) - 'a']++;
        }
        for (int i = 0; i <= length1 - length2; i++){
            for(int j = 0; j < length2; j++){
                mapTemp[s.charAt(i+j) - 'a']++;
            }
            if(help(map,mapTemp))
                result.add(i);
            Arrays.fill(mapTemp,0);
        }
        return result;
    }

    boolean help(int[] map1, int[] map2){
        for(int i = 0; i<26;i++){
            if(map1[i] != map2[i])
                return false;
        }
        return true;
    }


    /**
     * faster solution
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagramsV2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() == 0 || p.length() == 0 || s.length() < p.length()) {
            return res;
        }

        int count[] = new int[26];
        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            count[ch-'a']++;
        }

        int cnt[] = new int[26];
        for (int i = 0; i < p.length(); i++) {
            int pos = s.charAt(i) - 'a';
            cnt[pos]++;
        }

        boolean flag = true;
        for (int i = 0; i < 26; i++) {
            if (count[i] != cnt[i]) {
                flag = false;
                break;
            }
        }
        if (flag) {
            res.add(0);
        }
        for (int i = p.length(); i < s.length(); i++) {
            int pos = s.charAt(i-p.length()) - 'a';
            cnt[s.charAt(i)-'a']++;
            cnt[pos]--;
            flag = true;
            for (int j = 0; j < 26; j++) {
                if (count[j] != cnt[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res.add(i-p.length()+1);
            }
        }
        return res;
    }
}
