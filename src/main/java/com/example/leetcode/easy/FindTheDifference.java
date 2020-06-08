package com.example.leetcode.easy;

public class FindTheDifference {
    public static void main(String[] args) {

    }

    public char findTheDifference(String s, String t) {
        int[] cpt = new int[26];
        for (char c : s.toCharArray()) {
            cpt[c-'a']++;
        }
        for (char c : t.toCharArray()) {
            cpt[c-'a']--;
            if(cpt[c-'a'] < 0){
                return c;
            }
        }
        return 'a';
    }

    /**
     * faster solution
     * @param s
     * @param t
     * @return
     */
    public char findTheDifferenceV1(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        int accumulator = 0;
        for(int i = 0; i < sChars.length; i++){
            accumulator ^= sChars[i];
            accumulator ^= tChars[i];
        }

        accumulator ^= tChars[tChars.length-1];
        return (char)accumulator;
    }
}
