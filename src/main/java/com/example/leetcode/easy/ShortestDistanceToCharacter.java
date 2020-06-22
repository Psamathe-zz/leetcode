package com.example.leetcode.easy;

/**
 * Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.
 *
 * Example 1:
 *
 * Input: S = "loveleetcode", C = 'e'
 * Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 */
public class ShortestDistanceToCharacter {
    public static void main(String[] args) {
        String S = "loveleetcode";
        char C = 'e';
        ShortestDistanceToCharacter shortestDistanceToCharacter = new ShortestDistanceToCharacter();
        shortestDistanceToCharacter.shortestToChar(S,C);
    }

    public int[] shortestToChar(String S, char C) {
        int left = 20000;
        int right = 20000;
        int[] result1 = new int[S.length()];
        int[] result2 = new int[S.length()];
        int[] result = new int[S.length()];
        for(int i = 0; i < S.length();i++){
            if(S.charAt(i) != C){
                left++;
            } else{
                left = 0;
            }
            result1[i] = left;
        }

        for(int i = S.length() - 1; i >= 0 ;i--){
            if(S.charAt(i) != C){
                right++;
            } else{
                right = 0;
            }
            result2[i] = right;
        }
        for(int i = 0; i < S.length();i++){
            result[i] = Math.min(result1[i],result2[i]);
        }
        return result;
    }
}
