package com.example.leetcode.challenge.Test2020.april.week2;

import java.util.Stack;

/**
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 *
 * Example 1:
 *
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 * Example 2:
 *
 * Input: S = "ab##", T = "c#d#"
 * Output: true
 * Explanation: Both S and T become "".
 * Example 3:
 *
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 * Example 4:
 *
 * Input: S = "a#c", T = "b"
 * Output: false
 * Explanation: S becomes "c" while T becomes "b".
 * Note:
 *
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S and T only contain lowercase letters and '#' characters.
 * Follow up:
 *
 * Can you solve it in O(N) time and O(1) space?
 */
public class BackspaceStringCompare {
    public static void main(String[] args) {
        String S = "a##c";
        String T = "#a#c";
        BackspaceStringCompare backspaceStringCompare = new BackspaceStringCompare();
        boolean result = backspaceStringCompare.backspaceCompare(S,T);
        System.out.println(result);
    }

    public boolean backspaceCompare(String S, String T) {
        return backsprceString(S).equals(backsprceString(T));
    }


    public String backsprceString(String input){
        char[] chars = input.toCharArray();
        char[] result = new char[input.length()];
        int index = 0;
        for(char value: chars){
            if(value == '#' && index > 0){
                index--;
            }
            if(value != '#'){
                result[index++] = value;
            }
        }
        return String.valueOf(result,0,index);
    }

    /**
     * java stack
     * @param S
     * @param T
     * @return
     */

    public boolean backspaceCompareV2(String S, String T) {
        return build(S).equals(build(T));
    }

    public String build(String S) {
        Stack<Character> ans = new Stack();
        for (char c: S.toCharArray()) {
            if (c != '#')
                ans.push(c);
            else if (!ans.empty())
                ans.pop();
        }
        return String.valueOf(ans);
    }

    /**
     *  less memory
     * @param S
     * @param T
     * @return
     */

    public boolean backspaceCompareV3(String S, String T) {
        int sp=S.length()-1;
        int tp=T.length()-1;
        int tb=0;
        int sb=0;
        while(true){
            while(sp>=0&&(sb>0||S.charAt(sp)=='#')){
                if(S.charAt(sp)=='#')
                    sb++;
                else
                    sb--;
                sp--;
            }
            while(tp>=0&&(tb>0||T.charAt(tp)=='#')){
                if(T.charAt(tp)=='#')
                    tb++;
                else
                    tb--;
                tp--;
            }
            if(tp<0&&sp<0)
                return true;
            if(tp<0||sp<0||S.charAt(sp)!=T.charAt(tp))
                return false;
            sp--;
            tp--;
        }
    }
}
