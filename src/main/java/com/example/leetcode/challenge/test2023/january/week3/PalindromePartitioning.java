package com.example.leetcode.challenge.test2023.january.week3;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every
 * substring
 *  of the partition is a
 * palindrome
 * . Return all possible palindrome partitioning of s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * Example 2:
 *
 * Input: s = "a"
 * Output: [["a"]]
 */
public class PalindromePartitioning {

    public static void main(String[] args) {

    }

    /**
     * http://www.noteanddata.com/leetcode-131-Palindrome-Partitioning-java-solution-note.html
     * https://www.cnblogs.com/grandyang/p/4270008.html
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> allList = new ArrayList<>();
        helper(new ArrayList<>(), s, allList);
        return allList;
    }

    public void helper(List<String> cur, String remain, List<List<String>> allList) {
        if(remain.length() == 0) {
            if(cur.size() > 0) {
                allList.add(new ArrayList<String>(cur));
            }
            return;
        }
        for(int i = 0; i < remain.length(); ++i) {
            if(isPalindrome(remain, 0, i)) {
                String part = remain.substring(0, i+1);
                cur.add(part);
                helper(cur, remain.substring(i+1), allList);
                cur.remove(cur.size()-1);
            }
        }
    }
    public boolean isPalindrome(String s, int i, int j) {
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
