package com.example.leetcode.medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 *
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 *
 * Example:
 *
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 *
 * Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 */
public class RepeatedDNASequences {
    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        RepeatedDNASequences repeatedDNASequences = new RepeatedDNASequences();
        List<String> res = repeatedDNASequences.findRepeatedDnaSequences(s);
        System.out.println(res);
    }

    public List<String> findRepeatedDnaSequences(String s) {
        Map<String,Integer> count = new HashMap<>();
        int length = s.length();
        String key;
        for (int i = 0; i <= length - 10; i++) {
            key = s.substring(i,i+10);
            count.put(key,count.getOrDefault(key,0) + 1);
        }

        return count.entrySet().stream().filter(e->e.getValue()>1).map(e->e.getKey()).collect(Collectors.toList());
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4284205.html
     * faster solution
     */
    public List<String> findRepeatedDnaSequencesV0(String s) {
        List<String> ans = new ArrayList<>();
        if(s.length() < 10){
            return ans;
        }

        int a = 1<<20;
        a--;
        char str[] = s.toCharArray();
        byte hashset[] = new byte[a];
        byte map[] = new byte['T' + 1];
        map['A'] = 0;
        map['C'] = 1;
        map['G'] = 2;
        map['T'] = 3;

        int hash = 0;
        for(int i = 0;i < 10;i++)
            hash = hash<< 2 | map[str[i]];

        hashset[hash] = 1;

        for(int i = 10; i < s.length(); i++){
            hash = hash << 2 & a | map[str[i]];
            if(hashset[hash] == 0)
                hashset[hash]++;
            else if(hashset[hash] == 1){
                hashset[hash]++;
                ans.add(s.substring(i - 9, i + 1));
            }
        }
        return ans;
    }

    public List<String> findRepeatedDnaSequencesV1(String s) {
        Set<Integer> words = new HashSet<>();
        Set<Integer> doubleWords = new HashSet<>();
        List<String> rv = new ArrayList<>();
        char[] map = new char[26];
        //map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;

        for(int i = 0; i < s.length() - 9; i++) {
            int v = 0;
            for(int j = i; j < i + 10; j++) {
                v <<= 2;
                v |= map[s.charAt(j) - 'A'];
            }
            if(!words.add(v) && doubleWords.add(v)) {
                rv.add(s.substring(i, i + 10));
            }
        }
        return rv;
    }
}
