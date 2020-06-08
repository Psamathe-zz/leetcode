package com.example.leetcode.medium;

import java.util.*;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 *
 *
 * Example:
 *
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 *
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class LetterCombinationsPhoneNumber {
    public static void main(String[] args) {
        String digits =  "23";
        LetterCombinationsPhoneNumber letterCombinationsPhoneNumber = new LetterCombinationsPhoneNumber();
        List<String> result = letterCombinationsPhoneNumber.letterCombinations(digits);
        System.out.println(result);
    }

    public List<String> letterCombinations(String digits) {
        Map<Character,char[]> map = new HashMap<>();
        map.put('2',new char[]{'a','b','c'});
        map.put('3',new char[]{'d','e','f'});
        map.put('4',new char[]{'g','h','i'});
        map.put('5',new char[]{'j','k','l'});
        map.put('6',new char[]{'m','n','o'});
        map.put('7',new char[]{'p','q','r','s'});
        map.put('8',new char[]{'t','u','v'});
        map.put('9',new char[]{'w','x','y','z'});
        List<String> result = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            char c = digits.charAt(i);
            char[] temp = map.get(c);
            new StringBuffer();
            if(result.isEmpty()){
                for(char value : temp){
                    result.add(String.valueOf(value));
                }
            } else {
                List<String> resultTemp = new ArrayList<>(result);
                result.clear();
                for(String str:resultTemp){
                    for(char value : temp){
                        result.add(str + value);
                    }
                }
            }
        }
        return result;
    }


    /**
     * faster solution
     */
    private final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    private List<String> letterAns = new LinkedList<>();


    public List<String> letterCombinationsV1(String digits) {
        if (digits == null || digits.length() == 0){
            return letterAns;
        }
        StringBuilder ready = new StringBuilder();
        letterCombinations(digits, ready);
        return letterAns;
    }

    private void letterCombinations(String digits, StringBuilder ready) {
        if (ready.length() == digits.length()){
            letterAns.add(new String(ready));
            return;
        }

        int curDigit = digits.charAt(ready.length()) - '0';
        for (char c: KEYS[curDigit].toCharArray()){
            ready.append(c);
            letterCombinations(digits, ready);
            ready.deleteCharAt(ready.length() - 1);
        }
    }
}
