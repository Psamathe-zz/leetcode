package com.example.leetcode.challenge.test2020.may.week4;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a string, sort it in decreasing order based on the frequency of characters.
 *
 * Example 1:
 *
 * Input:
 * "tree"
 *
 * Output:
 * "eert"
 *
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * Example 2:
 *
 * Input:
 * "cccaaa"
 *
 * Output:
 * "cccaaa"
 *
 * Explanation:
 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * Example 3:
 *
 * Input:
 * "Aabb"
 *
 * Output:
 * "bbAa"
 *
 * Explanation:
 * "bbaA" is also a valid answer, but "" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 */
public class SortCharactersByFrequency {
    public static void main(String[] args) {
        String input = "tree";
        SortCharactersByFrequency sortCharactersByFrequency = new SortCharactersByFrequency();
        String result = sortCharactersByFrequency.frequencySort(input);
        System.out.println(result);
    }

    public String frequencySort(String s) {
        Map<Character,Integer> map = new LinkedHashMap<>();
        for (Character c : s.toCharArray()){
            map.put(c,map.getOrDefault(c,0) + 1 );
        }

        String result = map.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).map(e-> {
                    char[] repeat = new char[e.getValue()];
                    Arrays.fill(repeat, e.getKey());
                    return new String(repeat);
                }).collect(Collectors.joining());

        return result;
    }

    /**
     * faster solutionum.
     * @param s
     * @return
     */
    public String frequencySortV2(String s) {

        if(s.isEmpty() || s.length()==0)
            return s;
        int[] map = new int[128];
        char[] cA = s.toCharArray();

        for(char c:cA)
            map[c]++;

        char[] result = new char[s.length()];
        for(int i=0;i<s.length();){
            int max = 0;
            int index = 0;
            for(int j = 0;j<128;j++){
                if(map[j]>max){
                    max = map[j];
                    index = j;
                }
            }
            while(map[index]>0){
                result[i++] = (char)index;
                map[index]--;
            }
        }
        return new String(result);
    }
}
