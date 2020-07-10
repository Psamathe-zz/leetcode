package com.example.leetcode.medium;

/**
 * Given a string s consisting only of characters a, b and c.
 *
 * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabc"
 * Output: 10
 * Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
 * Example 2:
 *
 * Input: s = "aaacb"
 * Output: 3
 * Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
 * Example 3:
 *
 * Input: s = "abc"
 * Output: 1
 *
 *
 * Constraints:
 *
 * 3 <= s.length <= 5 x 10^4
 * s only consists of a, b or c characters.
 */
public class NumberofSubstringsContainingAllThreeCharacters {
    public static void main(String[] args) {
        String s = "aaacb";
        NumberofSubstringsContainingAllThreeCharacters numberofSubstringsContainingAllThreeCharacters = new NumberofSubstringsContainingAllThreeCharacters();
        numberofSubstringsContainingAllThreeCharacters.numberOfSubstrings(s);
    }
    int[] count;
    public int numberOfSubstrings(String s) {
        int res = 0;
        count = new int[3];
        int length = s.length();
        int left = 0;
        int right = 0;
        char[] chars = s.toCharArray();
        for ( right = 0; right < length; right++) {
            count[chars[right] - 'a']++;
            while (check()){
                res += length - right;
                count[chars[left] - 'a']--;
                left++;
            }
        }
        return res;
    }

    public boolean check(){
        for (int i = 0; i <= 2; i++) {
            if(count[i]==0)
                return false;
        }
        return true;
    }


    /**
     * faster solution
     * @param s
     * @return
     */
    public int numberOfSubstringsV1(String s) {
        int ret = 0;

        int n = s.length();

        int[] counts = new int[] { -1, -1, -1 };

        char[] chars = s.toCharArray();

        for (int i = 0; i < n; i ++)
        {
            counts[chars[i] - 'a'] = i;

            ret += 1 + Math.min(counts[0], Math.min(counts[1], counts[2]));
        }

        return ret;
    }
}
