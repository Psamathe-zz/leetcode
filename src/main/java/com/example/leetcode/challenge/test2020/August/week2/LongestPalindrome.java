package com.example.leetcode.challenge.test2020.August.week2;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
 *
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 *
 * Note:
 * Assume the length of given string will not exceed 1,010.
 *
 * Example:
 *
 * Input:
 * "abccccdd"
 *
 * Output:
 * 7
 *
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        String s = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        int res = longestPalindrome.longestPalindrome(s);
        System.out.println(res);
    }

    public int longestPalindrome(String s) {
        System.out.println(s.length());
        int res = 0;
        boolean existSingle = false;
        Map<Character,Integer> map = new HashMap<>();

        for (char c : s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }

        for (Integer val : map.values()){
            if(val % 2 == 0)
                res += val;
            else {
                existSingle = true;
                if(val > 1)
                    res += val - 1;
            }
        }
        return existSingle?res + 1:res;
    }
}
