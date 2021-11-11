package com.example.leetcode.challenge.test2021.november;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
 * word contains the first letter of puzzle.
 * For each letter in word, that letter is in puzzle.
 * For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage", while
 * invalid words are "beefed" (does not include 'a') and "based" (includes 's' which is not in the puzzle).
 * Return an array answer, where answer[i] is the number of words in the given word list words that is valid with respect to the puzzle puzzles[i].
 *
 *
 * Example 1:
 *
 * Input: words = ["aaaa","asas","able","ability","actt","actor","access"], puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * Output: [1,1,3,2,4,0]
 * Explanation:
 * 1 valid word for "aboveyz" : "aaaa"
 * 1 valid word for "abrodyz" : "aaaa"
 * 3 valid words for "abslute" : "aaaa", "asas", "able"
 * 2 valid words for "absoryz" : "aaaa", "asas"
 * 4 valid words for "actresz" : "aaaa", "asas", "actt", "access"
 * There are no valid words for "gaswxyz" cause none of the words in the list contains letter 'g'.
 * Example 2:
 *
 * Input: words = ["apple","pleas","please"], puzzles = ["aelwxyz","aelpxyz","aelpsxy","saelpxy","xaelpsy"]
 * Output: [0,1,3,2,0]
 *
 */
public class NumberValidWords {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.jp/leetcode-1178-number-of-valid-words-for-each-puzzle-%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF%E5%88%86%E6%9E%90/
     * @param words
     * @param puzzles
     * @return
     */
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Map<Integer, Integer> map = new HashMap<>();
        // 将每个单词转化为2进制形式
        for(String word : words){
            int key=0;
            for(int i=0;i<word.length();i++){
                // 利用位操作转化二进制数
                key |= (1<<(word.charAt(i)-'a'));
            }
            int count = map.getOrDefault(key, 0);
            map.put(key, count+1);
        }
        // 返回结果
        List<Integer> res = new ArrayList<>();
        // 循环每个谜面
        for(int i=0;i<puzzles.length;i++){
            // 当前谜面
            String puzzle = puzzles[i];
            // 将谜面首字符转为二进制作为key
            int firstKey= (1<<(puzzle.charAt(0)-'a'));
            // 查看map中该key的值
            int count=map.getOrDefault(firstKey, 0);
            // 以下操作为谜面中遍历每种字母组合
            List<Integer> keyList =new ArrayList<>();
            for(int j=1;j<7;j++){
                List<Integer> list =new ArrayList<>();
                int current = ((1<<(puzzle.charAt(j)-'a')) | firstKey);
                count+=map.getOrDefault(current, 0);
                list.add(current);
                for(int key : keyList){
                    int newKey = key | current;
                    count+=map.getOrDefault(newKey, 0);
                    list.add(newKey);
                }
                keyList.addAll(list);
            }
            res.add(count);
        }
        return res;
    }
}
