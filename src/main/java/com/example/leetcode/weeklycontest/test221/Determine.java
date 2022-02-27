package com.example.leetcode.weeklycontest.test221;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the first half and b be the second half.
 *
 * Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'). Notice that s contains uppercase and lowercase letters.
 *
 * Return true if a and b are alike. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "book"
 * Output: true
 * Explanation: a = "bo" and b = "ok". a has 1 vowel and b has 1 vowel. Therefore, they are alike.
 * Example 2:
 *
 * Input: s = "textbook"
 * Output: false
 * Explanation: a = "text" and b = "book". a has 1 vowel whereas b has 2. Therefore, they are not alike.
 * Notice that the vowel o is counted twice.
 * Example 3:
 *
 * Input: s = "MerryChristmas"
 * Output: false
 * Example 4:
 *
 * Input: s = "AbCdEfGh"
 * Output: true
 *
 *
 * Constraints:
 *
 * 2 <= s.length <= 1000
 * s.length is even.
 * s consists of uppercase and lowercase letters.
 */
public class Determine {

    public static void main(String[] args) {
        Determine determine = new Determine();
        determine.halvesAreAlike("Uo");
    }

    public boolean halvesAreAlike(String s) {
        int length = s.length();

        int half = length/2;
        int count1 = 0;
        int count2 = 0;
        char[] chars = new char[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        List<Character> myListOfCharacters = IntStream
                .range(0, chars.length)
                .mapToObj(i -> chars[i])
                .collect(Collectors.toList());
        for (int i = 0; i < length; i++) {
            System.out.println(Arrays.binarySearch(chars, s.charAt(i)));
            if(i < half) {
                if(myListOfCharacters.contains(s.charAt(i))){
                    count1++;
                }
            } else {
                if(myListOfCharacters.contains(s.charAt(i))){
                    count2++;
                }
            }
        }
        return count1 == count2;
    }
}
