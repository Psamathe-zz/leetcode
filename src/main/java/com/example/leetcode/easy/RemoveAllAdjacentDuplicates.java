package com.example.leetcode.easy;

import java.util.Stack;

/**
 * Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.
 *
 * We repeatedly make duplicate removals on S until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: "abbaca"
 * Output: "ca"
 * Explanation:
 * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 *
 *
 * Note:
 *
 * 1 <= S.length <= 20000
 * S consists only of English lowercase letters.
 */
public class RemoveAllAdjacentDuplicates {
    public static void main(String[] args) {
        String S = "abbaca";
        RemoveAllAdjacentDuplicates removeAllAdjacentDuplicates = new RemoveAllAdjacentDuplicates();
        String result = removeAllAdjacentDuplicates.removeDuplicatesV1(S);
        System.out.println(result);
    }

    public String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<>();
        for (char c: S.toCharArray()){
            if(!stack.isEmpty() && stack.peek() == c){
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()){
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.reverse().toString();
    }


    /**
     * faster solution
     * @param S
     * @return
     */
    public String removeDuplicatesV1(String S) {
        int i=0;
        int n=S.length();
        char[] res=S.toCharArray();
        for(int j=0; j<n; j++,i++){
            res[i]=res[j];
            if(i>0 && res[i-1]==res[i])
                i-=2;
        }
        return new String(res,0,i);
    }
}
