package com.example.leetcode.challenge.test2021.april.week4;


/**
 * Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.
 *
 * Substrings that occur multiple times are counted the number of times they occur.
 *
 * Example 1:
 * Input: "00110011"
 * Output: 6
 * Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
 *
 * Notice that some of these substrings repeat and are counted the number of times they occur.
 *
 * Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
 * Example 2:
 * Input: "10101"
 * Output: 4
 * Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
 * Note:
 *
 * s.length will be between 1 and 50,000.
 * s will only consist of "0" or "1" characters.
 * "00100"
 */
public class CountBinarySubstrings {
    public static void main(String[] args) {
        CountBinarySubstrings countBinarySubstrings = new CountBinarySubstrings();
        countBinarySubstrings.countBinarySubstrings("00100");
    }

    public int countBinarySubstrings(String s) {
        char[] chars = s.toCharArray();
        int length = s.length();
        int res = 0;
        int count0 = 0;
        int count1 = 0;
        for (int i = 0; i < length; i++) {
            if(chars[i] == '0'){
                if(count1 > 0 && chars[i-1] == '1') {
                    res += Math.min(count0,count1);
                    count0 = 0;
                }
                count0++;
            } else {
                if( count0 > 0 && chars[i-1] == '0') {
                    res += Math.min(count0,count1);
                    count1 = 0;
                }
                count1++;
            }
        }
        res += Math.min(count0,count1);
        return res;
    }


    /**
     * faster solution
     * @param s
     * @return
     */
    public int countBinarySubstringsV0(String s) {
        int res = 0;
        int prev = 0;
        int curr = 0;
        char c = s.charAt(0);

        for(char ch : s.toCharArray()){
            if(ch == c){
                curr++;
            }else{
                res += Math.min(prev, curr);
                prev = curr;
                curr = 1;
                c = ch;
            }
        }
        return res+Math.min(curr, prev);
    }
}
