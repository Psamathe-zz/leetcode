package com.example.leetcode.easy;

/**
 * Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.
 *
 *
 *
 * Example 1:
 *
 * Input: A = "ab", B = "ba"
 * Output: true
 * Example 2:
 *
 * Input: A = "ab", B = "ab"
 * Output: false
 * Example 3:
 *
 * Input: A = "aa", B = "aa"
 * Output: true
 * Example 4:
 *
 * Input: A = "aaaaaaabc", B = "aaaaaaacb"
 * Output: true
 * Example 5:
 *
 * Input: A = "", B = "aa"
 * Output: false
 */
public class BuddyStrings {

    public static void main(String[] args) {
        String a = "aab";
        String b = "aab";
        BuddyStrings buddyStrings = new BuddyStrings();
        boolean result = buddyStrings.buddyStrings(a,b);
        System.out.println(result);
    }

    public boolean buddyStrings(String A, String B) {
        if(A.length() != B.length())
            return false;
        int temp = 0;
        int index = 0;
        int[] indexCara = new int[26];
        for(int i = 0; i < A.length();i++){
            if(A.charAt(i) != B.charAt(i)){
                index++;
                temp += A.charAt(i) - B.charAt(i);
            } else {
                indexCara[A.charAt(i) - 'a']++;
            }
        }
        if(index == 2 && temp == 0 )
            return true;
        else{
            for(int val : indexCara){
                if(val>1){
                    return index == 0 && temp == 0;
                }
            }
        }
        return false;
    }
}
