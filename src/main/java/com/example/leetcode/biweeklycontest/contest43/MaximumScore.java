package com.example.leetcode.biweeklycontest.contest43;


import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a string s and two integers x and y. You can perform two types of operations any number of times.
 *
 * Remove substring "ab" and gain x points.
 * For example, when removing "ab" from "cabxbae" it becomes "cxbae".
 * Remove substring "ba" and gain y points.
 * For example, when removing "ba" from "cabxbae" it becomes "cabxe".
 * Return the maximum points you can gain after applying the above operations on s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "cdbcbbaaabab", x = 4, y = 5
 * Output: 19
 * Explanation:
 * - Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5 points are added to the score.
 * - Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4 points are added to the score.
 * - Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points are added to the score.
 * - Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are added to the score.
 * Total score = 5 + 4 + 5 + 5 = 19.
 * Example 2:
 *
 * Input: s = "aabbaaxybbaabb", x = 5, y = 4
 * Output: 20
 */
public class MaximumScore {
    public static void main(String[] args) {
        MaximumScore maximumScore = new MaximumScore();
        int res = maximumScore.maximumGain("cdbcbbaaabab",4,5);
        System.out.println(res);
    }

    public int maximumGain(String s, int x, int y) {
        int score1= 0,score2 = 0;
        int total = 0;
        char first,second;
        if (x>y) {
            first = 'a';
            second = 'b';
        } else {
            first = 'b';
            second = 'a';
        }
        for (int i = 0; i < s.length(); i ++) {
            char ch = s.charAt(i);

            if (ch == first) {
                score1++;
            } else if (ch == second) {
                if (score1 > 0) {
                    score1 --;
                    if (first == 'a') {
                        total += x;
                    } else {
                        total += y;
                    }

                } else {
                    score2 ++;
                }
            }

            if ((ch != first && ch != second) || i+1 == s.length()) {
                int min = Math.min(score2,score1);
                if (second == 'a') {
                    total += x*min;
                } else {
                    total += y*min;
                }
                score1 = 0;
                score2 = 0;
            }
        }
        return total;
    }

    public int maximumGainV1(String s, int x, int y) {
        Deque<Character> queue = new LinkedList<>();
        int res = 0;
        boolean getA = x>=y;
        for (int i = 0; i < s.length(); i++) {
            queue.addLast(s.charAt(i));
        }
        int size;
        boolean findNextA;
        boolean findNextB;
        char a;
        char b;
        int index;
        do {

            do {
                findNextA = false;
                size = queue.size();
                index = size;
                while (index >= 0) {
                    a = queue.pollFirst();
                    b = queue.peekLast();
                    if (index != size) {
                        if (getA && a == 'a' && b == 'b') {
                            res += x;
                            queue.pollLast();
                            findNextA = true;
                        } else if (!getA && a == 'b' && b == 'a') {
                            res += y;
                            queue.pollLast();
                            findNextA = true;
                        } else {
                            queue.addLast(a);
                        }
                    } else
                        queue.addLast(a);
                    index--;
                }
            } while (findNextA);
            findNextB = false;
            size = queue.size();
            index = size;
            while (index >= 0) {
                a = queue.pollFirst();
                b = queue.peekLast();
                if (index != size) {
                    if (getA && a == 'b' && b == 'a') {
                        res += y;
                        queue.pollLast();
                        findNextB = true;
                        break;
                    } else if (!getA && a == 'a' && b == 'b') {
                        res += x;
                        queue.pollLast();
                        findNextB = true;
                        break;
                    } else {
                        queue.addLast(a);
                    }
                } else
                    queue.addLast(a);
                index--;
            }
        } while (findNextA || findNextB);
        return res;
    }
}
