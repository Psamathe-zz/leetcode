package com.example.leetcode.weeklycontest.old.test197;

/**
 *
 User Accepted:5152
 User Tried:6021
 Total Accepted:5276
 Total Submissions:14643
 Difficulty:Medium
 Given a binary string s (a string consisting only of '0' and '1's).

 Return the number of substrings with all characters 1's.

 Since the answer may be too large, return it modulo 10^9 + 7.



 Example 1:

 Input: s = "0110111"
 Output: 9
 Explanation: There are 9 substring in total with only 1's characters.
 "1" -> 5 times.
 "11" -> 3 times.
 "111" -> 1 time.
 Example 2:

 Input: s = "101"
 Output: 2
 Explanation: Substring "1" is shown 2 times in s.
 Example 3:

 Input: s = "111111"
 Output: 21
 Explanation: Each substring contains only 1's characters.
 Example 4:

 Input: s = "000"
 Output: 0


 Constraints:

 s[i] == '0' or s[i] == '1'
 1 <= s.length <= 10^5


 */
public class NumberSubstringsWithOnly1s {
    public static void main(String[] args) {
    }

    static int MODULE = 1000000007;
    public int numSub(String s) {
        int res = 0;

        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '1'){
                count++;
            } else {
                if(count != 0){
                    res += count(count);
                    count = 0;
                }
            }
        }
        if(count != 0) {
            res += count(count);
        }
        return res;
    }

    public int count(int n){
        int res = 0;
        for (int i = n; i >= 1 ; i--) {
            res += i;
            res = res % MODULE;
        }
        return res;
    }
}
