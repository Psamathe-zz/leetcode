package com.example.leetcode.challenge.test2021.July.week2;


/**
 * order and str are strings composed of lowercase letters. In order, no letter occurs more than once.
 *
 * order was sorted in some custom order previously. We want to permute the characters of str so that they match the order that order was sorted. More specifically, if x occurs before y in order, then x should occur before y in the returned string.
 *
 * Return any permutation of str (as a string) that satisfies this property.
 *
 * Example:
 * Input:
 * order = "cba"
 * str = "abcd"
 * Output: "cbad"
 * Explanation:
 * "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".
 * Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.
 *
 *
 * Note:
 *
 * order has length at most 26, and no character is repeated in order.
 * str has length at most 200.
 * order and str consist of lowercase letters only.
 */
public class CustomSortString {
    public static void main(String[] args) {
        CustomSortString customSortString = new CustomSortString();
        String str = customSortString.customSortString("cba","abcd");
        System.out.println(str);
    }

    public String customSortString(String order, String str) {
        int[] count = new int[26];
        boolean[] done = new boolean[26];
        int val;
        char c ;
        StringBuilder stringBuilder = new StringBuilder();
        for (char cc: str.toCharArray()){
            count[cc - 'a']++;
        }

        for (char cc: order.toCharArray()){
            val = count[cc - 'a'];
            for (int i = 0; i < val; i++) {
                stringBuilder.append(cc);
            }
            done[cc - 'a'] = true;
        }

        for (int i = 0; i < 26; i++) {
            if(!done[i]){
                val = count[i];
                c = (char)( 'a' + i);
                for (int j = 0; j < val; j++) {
                    stringBuilder.append(c);
                }
            }
        }
        return stringBuilder.toString();
    }
}
