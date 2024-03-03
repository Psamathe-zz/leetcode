package com.example.leetcode.biweeklycontest.old.contest46;


import java.util.HashSet;

/**
 * A string s is nice if, for every letter of the alphabet that s contains, it appears both in uppercase and lowercase. For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not because 'b' appears, but 'B' does not.
 *
 * Given a string s, return the longest substring of s that is nice. If there are multiple, return the substring of the earliest occurrence. If there are none, return an empty string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "YazaAay"
 * Output: "aAa"
 * Explanation: "aAa" is a nice string because 'A/a' is the only letter of the alphabet in s, and both 'A' and 'a' appear.
 * "aAa" is the longest nice substring.
 * Example 2:
 *
 * Input: s = "Bb"
 * Output: "Bb"
 * Explanation: "Bb" is a nice string because both 'B' and 'b' appear. The whole string is a substring.
 * Example 3:
 *
 * Input: s = "c"
 * Output: ""
 * Explanation: There are no nice substrings.
 * Example 4:
 *
 * Input: s = "dDzeE"
 * Output: "dD"
 * Explanation: Both "dD" and "eE" are the longest nice substrings.
 * As there are multiple longest nice substrings, return "dD" since it occurs earlier.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s consists of uppercase and lowercase English letters.
 */
public class LongestNiceSubstring {
    public static void main(String[] args) {

    }

    /**
     *
     作者：hu-li-hu-wai
     链接：https://leetcode-cn.com/problems/longest-nice-substring/solution/setpan-duan-bian-li-by-hu-li-hu-wai-lcx3/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public String longestNiceSubstring(String s) {
        char[] cs = s.toCharArray();
        String res = "";
        for (int i = 0; i < cs.length; i++) {
            for (int j = cs.length - 1; j > i; j--) {
                boolean flag = true;
                HashSet<Character> set = new HashSet<>();
                for (int k = i; k <= j; k++) {
                    set.add(cs[k]);
                }
                if(set.size()%2!=0)
                    continue;
                for (char c : set){
                    if(c < 'a' && !set.contains((char)(c+32)))
                        flag = false;
                    if(c >='a' && !set.contains((char)(c-32)))
                        flag = false;
                    if(!flag)
                        break;
                }
                if(flag && j-i+1>res.length())
                    res = s.substring(i,j+1);
            }
        }
        return res;
    }
}
