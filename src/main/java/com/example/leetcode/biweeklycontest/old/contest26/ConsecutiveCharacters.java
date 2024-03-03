package com.example.leetcode.biweeklycontest.old.contest26;

import java.util.Stack;

/**
 * Given a string s, the power of the string is the maximum length of a non-empty substring that contains only one unique character.
 *
 * Return the power of the string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode"
 * Output: 2
 * Explanation: The substring "ee" is of length 2 with the character 'e' only.
 * Example 2:
 *
 * Input: s = "abbcccddddeeeeedcba"
 * Output: 5
 * Explanation: The substring "eeeee" is of length 5 with the character 'e' only.
 * Example 3:
 *
 * Input: s = "triplepillooooow"
 * Output: 5
 * Example 4:
 *
 * Input: s = "hooraaaaaaaaaaay"
 * Output: 11
 * Example 5:
 *
 * Input: s = "tourist"
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s contains only lowercase English letters.
 */
public class ConsecutiveCharacters {
    public static void main(String[] args) {
        String s = "j";
        ConsecutiveCharacters consecutiveCharacters = new ConsecutiveCharacters();
        int result = consecutiveCharacters.maxPower(s);
        System.out.println(result);
    }

    public int maxPower(String s) {
        int result = 0;
        Stack<Character> stack = new Stack<>();
        for(Character c:s.toCharArray()){
            if(!stack.isEmpty() && c != stack.peek()){
                if(result < stack.size())
                    result = stack.size();
                stack.clear();
            }
            stack.push(c);
        }
        if(!stack.isEmpty()){
            if(result < stack.size())
                result = stack.size();
            stack.clear();
        }
        return result;
    }

    /**
     * faster solution
     * @param s
     * @return
     */
    public int maxPowerV1(String s) {
        int count =1, maxcount =1;
        for(int i =1; i < s.length();i++){
            count =s.charAt(i)==s.charAt(i-1)?++count:1;
            maxcount = count> maxcount? count:maxcount;
        }
        return maxcount;
    }
}
