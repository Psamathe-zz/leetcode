package com.example.leetcode.medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A string is called happy if it does not have any of the strings 'aaa', 'bbb' or 'ccc' as a substring.
 *
 * Given three integers a, b and c, return any string s, which satisfies following conditions:
 *
 * s is happy and longest possible.
 * s contains at most a occurrences of the letter 'a', at most b occurrences of the letter 'b' and at most c occurrences of the letter 'c'.
 * s will only contain 'a', 'b' and 'c' letters.
 * If there is no such string s return the empty string "".
 *
 *
 *
 * Example 1:
 *
 * Input: a = 1, b = 1, c = 7
 * Output: "ccaccbcc"
 * Explanation: "ccbccacc" would also be a correct answer.
 * Example 2:
 *
 * Input: a = 2, b = 2, c = 1
 * Output: "aabbc"
 * Example 3:
 *
 * Input: a = 7, b = 1, c = 0
 * Output: "aabaa"
 * Explanation: It's the only correct answer in this case.
 *
 *
 * Constraints:
 *
 * 0 <= a, b, c <= 100
 * a + b + c > 0
 */
public class LongestHappyString {
    public static void main(String[] args) {
        int a = 1;
        int b = 1;
        int c = 7;
        LongestHappyString longestHappyString = new LongestHappyString();
        String result = longestHappyString.longestDiverseString(a,b,c);
        System.out.println(result);

    }

    /**
     * 这其实是一道贪心算法的题目，所以只需要“贪心”就好了。怎么个贪心法呢，
     * 就是每次总是在a，b，c三者中找到剩余次数最多的那个，加入到结果字符串中去，同时将其次数减一，需要注意的是为了防止出现出现连续3个同样字符的情况，
     * 在末尾两个字符已经一样的情况下只能从另外两个字符中选一个剩余次数最多的字符。时间复杂度为 O(n) O ( n ) ，空间复杂度为 O(1) O ( 1 ) 。
     */
    private int[] char2num;

    private char nextChar(char exclude) {
        char next;
        if (exclude == 'a') {
            next = char2num[1] > char2num[2] ? 'b' : 'c';
        }
        else if (exclude == 'b') {
            next = char2num[0] > char2num[2] ? 'a' : 'c';
        }
        else if (exclude == 'c') {
            next = char2num[0] > char2num[1] ? 'a' : 'b';
        }
        else {
            next = char2num[0] > char2num[1] ? 'a' : 'b';
            next = char2num[next - 'a'] > char2num[2] ? next : 'c';
        }
        return next;
    }

    public String longestDiverseString(int a, int b, int c) {
        char2num = new int[] {a, b, c};

        char[] result = new char[a + b + c];
        int idx = 0;

        while (char2num[0] != 0 || char2num[1] != 0 || char2num[2] != 0) {
            char next;
            if (idx < 2 || result[idx - 1] != result[idx - 2]) {
                next = nextChar(' ');
            }
            else {
                next = nextChar(result[idx - 1]);
            }
            if (char2num[next - 'a'] <= 0) {
                break;
            }
            --char2num[next - 'a'];
            result[idx++] = next;
        }

        return new String(result, 0, idx);
    }
}
