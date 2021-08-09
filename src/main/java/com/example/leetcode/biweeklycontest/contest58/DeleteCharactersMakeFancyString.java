package com.example.leetcode.biweeklycontest.contest58;


/**
 * A fancy string is a string where no three consecutive characters are equal.
 *
 * Given a string s, delete the minimum possible number of characters from s to make it fancy.
 *
 * Return the final string after the deletion. It can be shown that the answer will always be unique.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leeetcode"
 * Output: "leetcode"
 * Explanation:
 * Remove an 'e' from the first group of 'e's to create "leetcode".
 * No three consecutive characters are equal, so return "leetcode".
 * Example 2:
 *
 * Input: s = "aaabaaaa"
 * Output: "aabaa"
 * Explanation:
 * Remove an 'a' from the first group of 'a's to create "aabaaaa".
 * Remove two 'a's from the second group of 'a's to create "aabaa".
 * No three consecutive characters are equal, so return "aabaa".
 * Example 3:
 *
 * Input: s = "aab"
 * Output: "aab"
 * Explanation: No three consecutive characters are equal, so return "aab".
 */
public class DeleteCharactersMakeFancyString {
    public static void main(String[] args) {
        DeleteCharactersMakeFancyString deleteCharactersMakeFancyString = new DeleteCharactersMakeFancyString();
        String s = deleteCharactersMakeFancyString.makeFancyString("aaabaaaa");
        System.out.println(s);
    }

    public String makeFancyString(String s) {
        int count = 1;
        char cPre = s.charAt(0);
        char c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cPre);
        for (int i = 1; i < s.length(); i++) {
            c = s.charAt(i);
            if(c == cPre){
                count++;
                if(count <= 2)
                    stringBuilder.append(c);
            }  else {
                cPre = c;
                count = 1;
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }
}
