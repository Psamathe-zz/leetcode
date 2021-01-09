package com.example.leetcode.challenge.test2020.October.week3;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * All DNA is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T', for example: "ACGAATTCCG".
 * When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 *
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC","CCCCCAAAAA"]
 * Example 2:
 *
 * Input: s = "AAAAAAAAAAAAA"
 * Output: ["AAAAAAAAAA"]
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 105
 * s[i] is 'A', 'C', 'G', or 'T'.
 */
public class RepeatedDNASequences {
    public static void main(String[] args) {

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
     * faster solution
     * @param c
     * @return
     */
    private final static int translate(final Character c) {
        switch (c) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
            default:
                return -1;
        }
    }

    public List<String> findRepeatedDnaSequencesV1(String s) {
        List<String> ans = new ArrayList<>(44);
        if (s.length() < 10) return ans;
        char[] chars = s.toCharArray();
        boolean[] hashCodeMap = new boolean[1024*1024];
        boolean[] added = new boolean[1024*1024];

        int hashCode = 0;
        int i = 0;
        for (; i < 10; i++) {
            hashCode = (hashCode << 2) + translate(chars[i]);
        }
        hashCodeMap[hashCode] = true;
        for (; i < chars.length; i++) {
            /**
             * 取得前十位
             */
            hashCode = ((hashCode << 2) & 0xFFFFF);
            hashCode += translate(chars[i]);


            if (hashCodeMap[hashCode] && !added[hashCode]) {
                ans.add(new String(chars,i-9,10));
                added[hashCode] = true;
            }
            hashCodeMap[hashCode] = true;
        }

        return ans;
    }
}
