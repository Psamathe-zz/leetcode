package com.example.leetcode.challenge.October.week2;

import java.util.HashSet;
import java.util.Set;

/**
 * Given two strings A and B of lowercase letters, return true if you can swap two letters in A so the result is equal to B, otherwise, return false.
 *
 * Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at A[i] and A[j]. For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
 *
 *
 *
 * Example 1:
 *
 * Input: A = "ab", B = "ba"
 * Output: true
 * Explanation: You can swap A[0] = 'a' and A[1] = 'b' to get "ba", which is equal to B.
 * Example 2:
 *
 * Input: A = "ab", B = "ab"
 * Output: false
 * Explanation: The only letters you can swap are A[0] = 'a' and A[1] = 'b', which results in "ba" != B.
 * Example 3:
 *
 * Input: A = "aa", B = "aa"
 * Output: true
 * Explanation: You can swap A[0] = 'a' and A[1] = 'a' to get "aa", which is equal to B.
 * Example 4:
 *
 * Input: A = "aaaaaaabc", B = "aaaaaaacb"
 * Output: true
 * Example 5:
 *
 * Input: A = "", B = "aa"
 * Output: false
 *
 *
 * Constraints:
 *
 * 0 <= A.length <= 20000
 * 0 <= B.length <= 20000
 * A and B consist of lowercase letters.
 */
public class BuddyStrings {
    public static void main(String[] args) {

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


    /**
     * faster solution
     * @param A
     * @param B
     * @return
     */
    public boolean buddyStringsV1(String A, String B) {
        int A_length = A.length();
        char Achar[] = A.toCharArray();
        if(A_length != B.length())
            return false;
        if(A.equals(B)){
            Set set1 = new HashSet();
            for(int i = 0 ; i<A_length;i++){
                if(set1.contains(Achar[i])){
                    return true;
                }else{
                    set1.add(Achar[i]);
                }
            }
            return false;
        }
        char Bchar[] = B.toCharArray();
        char A1 = 0;
        char B1 = 0;
        int j = 0;
        for(int i = 0; i < A_length && j < 3;i++){
            if(Achar[i] != Bchar[i]){
                j++;
                if(j == 1){
                    A1 = Achar[i];
                    B1 = Bchar[i];
                }
                if(j == 2 && (Achar[i] != B1 || Bchar[i] != A1)){
                    return false;
                }
            }
        }
        return j == 2;
    }
}
