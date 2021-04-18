package com.example.leetcode.challenge.test2021.april.week3;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.
 *
 * We repeatedly make k duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made.
 *
 * It is guaranteed that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcd", k = 2
 * Output: "abcd"
 * Explanation: There's nothing to delete.
 * Example 2:
 *
 * Input: s = "deeedbbcccbdaa", k = 3
 * Output: "aa"
 * Explanation:
 * First delete "eee" and "ccc", get "ddbbbdaa"
 * Then delete "bbb", get "dddaa"
 * Finally delete "ddd", get "aa"
 * Example 3:
 *
 * Input: s = "pbbcggttciiippooaais", k = 2
 * Output: "ps"
 *
 */
public class RemoveAllAdjacentDuplicates {
    public static void main(String[] args) {

    }


    public String removeDuplicates(String s, int k) {
        Stack<Character> stack = new Stack<>();
        Stack<Integer> stackLength = new Stack<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(stack.size()==0 || c != stack.peek()){
                stack.push(c);
                stackLength.push(1);
            }else{
                int sameLength = stackLength.pop()+1;
                if(sameLength == k){
                    for(int j=1;j<k;j++){
                        stack.pop();
                    }
                }else{
                    stackLength.push(sameLength);
                    stack.push(c);
                }
            }
        }
        StringBuilder res=new StringBuilder();
        while(stack.size()>0){
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }
}
