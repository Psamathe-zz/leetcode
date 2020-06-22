package com.example.leetcode.easy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings within the list (including duplicates).  For example, if a character occurs 3 times in all strings but not 4 times, you need to include that character three times in the final answer.
 *
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: ["bella","label","roller"]
 * Output: ["e","l","l"]
 * Example 2:
 *
 * Input: ["cool","lock","cook"]
 * Output: ["c","o"]
 *
 *
 * Note:
 *
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] is a lowercase letter
 */
public class FindCommonCharacters {
    public static void main(String[] args) {
        String[] A = new String[]{"bella","label","roller"};
        FindCommonCharacters findCommonCharacters = new FindCommonCharacters();
        findCommonCharacters.commonChars(A);
    }

    public List<String> commonChars(String[] A) {
        Map<Character,Integer> count = new HashMap<>();
        for (char c: A[0].toCharArray()){
            count.put(c,count.getOrDefault(c,0)+1);
        }
        for (int i = 1; i < A.length; i++) {
            for (Character c : count.keySet()){
                int index = 0;
                int countC = 0;
                int nextIndex = A[i].indexOf(c,index);
                while (nextIndex>=0){
                    countC++;
                    index = nextIndex + 1;
                    nextIndex = A[i].indexOf(c,index);
                }
                count.put(c,Math.min(countC,count.get(c)));
            }
        }
        List<String> result = new ArrayList<>();
        for (Map.Entry<Character,Integer> entry:count.entrySet()){
            for (int i = 0; i < entry.getValue(); i++) {
                result.add(String.valueOf(entry.getKey()));
            }
        }
        return result;
    }


    /**
     * faster solution
     * @param A
     * @return
     */
    public List<String> commonCharsV1(String[] A) {
        int[][] map = new int[A.length][26];
        for (int i = 0; i < A.length; i++) {
            for (char c : A[i].toCharArray()) {
                map[i][c - 'a']++;
            }
        }
        List<String> list = new ArrayList<>();
        for (int j = 0; j < 26; j++) {
            int currentCount = map[0][j];
            for (int i = 1; i < A.length; i++) {
                currentCount = Math.min(currentCount, map[i][j]);
            }
            while (currentCount != 0) {
                list.add(Character.toString('a' + j));
                currentCount--;
            }
        }
        return list;
    }
}
