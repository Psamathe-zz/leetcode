package com.example.leetcode.biweeklycontest.contest24;

/**
 * A happy string is a string that:
 *
 * consists only of letters of the set ['a', 'b', 'c'].
 * s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
 * For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa", "baa" and "ababbc" are not happy strings.
 *
 * Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order.
 *
 * Return the kth string of this list or return an empty string if there are less than k happy strings of length n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1, k = 3
 * Output: "c"
 * Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".
 * Example 2:
 *
 * Input: n = 1, k = 4
 * Output: ""
 * Explanation: There are only 3 happy strings of length 1.
 * Example 3:
 *
 * Input: n = 3, k = 9
 * Output: "cab"
 * Explanation: There are 12 different happy string of length 3 ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"]. You will find the 9th string = "cab"
 * Example 4:
 *
 * Input: n = 2, k = 7
 * Output: ""
 * Example 5:
 *
 * Input: n = 10, k = 100
 * Output: "abacbabacb"
 * 3 * "
 */
public class HappyString {

    public static void main(String[] args) {
        int n = 10;
        int k = 100;
        HappyString happyString = new HappyString();
        String result = happyString.getHappyString(n,k);
        System.out.println(result);
    }

    public String getHappyString(int n, int k) {
        StringBuffer result = new StringBuffer();
        int m  = (int)Math.pow(2,n-1);
        if(k> m * 3){
            return result.toString();
        } else if(k <= m*3 && k> m*2){
            result.append("c").append(help(n-1, k - m*2 , 'c'));
            return result.toString();
        } else if(k <= m*2 && k> m){
            result.append("b").append(help(n-1, k - m,'b'));
            return result.toString();
        } else {
            result.append("a").append(help(n-1, k,'a'));
            return result.toString();

        }
    }

    public String help(int n,int k,char prefix){
        StringBuffer stringBuffer = new StringBuffer();
        int m  = (int)Math.pow(2,n-1);
        if(n==0)
            return stringBuffer.toString();
        if(prefix == 'a'){
            if(k<=m)
                return stringBuffer.append('b').append(help(n-1,k,'b')).toString();
            else
                return stringBuffer.append('c').append(help(n-1,k -m,'c')).toString();
        } else if(prefix == 'b'){
            if(k<=m)
                return stringBuffer.append('a').append(help(n-1,k,'a')).toString();
            else
                return stringBuffer.append('c').append(help(n-1,k -m,'c')).toString();
        }else{
            if(k<=m)
                return stringBuffer.append('a').append(help(n-1,k,'a')).toString();
            else
                return stringBuffer.append('b').append(help(n-1,k -m,'b')).toString();
        }
    }




    public String getHappyStringV2(int n, int k) {
        HappyStringV2 hs = new HappyStringV2(n);
        for (int i = 1; i < k && hs.valid; i++) {
            hs.increase();
        }
        return hs.toString();
    }

    private class HappyStringV2 {
        private final char[] chars;
        public boolean valid;
        public HappyStringV2(int length) {
            chars = new char[length];
            for (int i = 0; i < length; i++) {
                chars[i] = i % 2 == 0 ? 'a' : 'b';
            }
            valid = true;
        }

        public void increase() {
            int i = chars.length - 1;
            boolean updated = false;
            while (i > 0 && !updated) {
                if (chars[i] == 'c' || (chars[i] == 'b' && chars[i - 1] == 'c')) {
                    i--;
                } else if (chars[i - 1] - chars[i] == 1) {
                    chars[i] += 2;
                    updated = true;
                } else {
                    chars[i]++;
                    updated = true;
                }
            }
            if (!updated && i == 0) {
                chars[i]++;
                valid = chars[i] != 'd';
            }
            if (valid) {
                for (int j = i + 1; j < chars.length; j++) {
                    chars[j] = ((j - i) % 2 == 0 ^ (chars[i] == 'a')) ? 'b' : 'a';
                }
            }
        }

        @Override
        public String toString() {
            if (!valid) {
                return "";
            }
            StringBuilder result = new StringBuilder(chars.length);
            for (char c : chars) {
                result.append(c);
            }
            return result.toString();
        }
    }
}
