package com.example.leetcode.challenge.test2020.October.week2;

import java.util.Stack;

/**
 * Given a string s, remove duplicate letters so that every letter appears once and only once.
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 *
 *
 *
 * Example 1:
 *
 * Input: s = "bcabc"
 * Output: "abc"
 * Example 2:
 *
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of lowercase English letters.
 */
public class RemoveDuplicateLetters {
    public static void main(String[] args) {
        RemoveDuplicateLetters removeDuplicateLetters = new RemoveDuplicateLetters();
        removeDuplicateLetters.removeDuplicateLettersV2("cbacdcbc");
    }

    /**
     * https://blog.csdn.net/feifeiiong/article/details/77915509
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        // greedy solution
        if(s.length() == 0) return "";
        int[] greedy = new int[26];
        for(char v:s.toCharArray())
            greedy[v-'a']++;
        int pos = 0;
        for(int i = 0;i<s.length();++i) {
            if(s.charAt(i)<s.charAt(pos))
                pos = i;
            if(--greedy[s.charAt(i)-'a'] == 0)
                break;
        }
        char ret = s.charAt(pos);
        StringBuilder recusive = new StringBuilder();
        for(int i = pos+1;i<s.length(); ++i) {
            char val = s.charAt(i);
            if(val == ret)
                continue;
            recusive.append(val);
        }
        return ret+removeDuplicateLetters(recusive.toString());
    }


    /**
     * faster solution
     * @param s
     * @return
     */
    public String removeDuplicateLettersV1(String s) {
        StringBuilder sb = new StringBuilder();
        int[] count = new int[26];
        boolean[] used = new boolean[26];
        char[] chs = s.toCharArray();
        for(char ch:chs){
            count[ch-'a']++;
        }
        for(char c : chs){
            count[c-'a']--;
            if(used[c-'a']){
                continue;
            }
            while(sb.length()>0 && sb.charAt(sb.length()-1)>c && count[sb.charAt(sb.length()-1)-'a']>0){
                used[sb.charAt(sb.length()-1) - 'a'] = false;
                sb.deleteCharAt(sb.length()-1);
            }
            sb.append(c);
            used[c-'a']=true;
        }
        return sb.toString();
    }

    /**
     * faster v2
     * @param s
     * @return
     */
    public String removeDuplicateLettersV2(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        int[] f = new int[26];
        for (char c : s.toCharArray()) {
            f[c - 'a']++;
        }

        Stack<Character> stack = new Stack<>();
        boolean[] visited = new boolean[26];

        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            f[idx]--;
            if (visited[idx]) {
                continue;
            }

            while (!stack.isEmpty() && c < stack.peek() && f[stack.peek() - 'a'] != 0) {
                visited[stack.pop() - 'a'] = false;
            }
            stack.push(c);
            visited[idx] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }
}
