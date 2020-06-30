package com.example.leetcode.medium;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.
 *
 * Repeat the previous step again, but this time from right to left, remove the right most number and every other number from the remaining numbers.
 *
 * We keep repeating the steps again, alternating left to right and right to left, until a single number remains.
 *
 * Find the last number that remains starting with a list of length n.
 *
 * Example:
 *
 * Input:
 * n = 9,
 * 1 2 3 4 5 6 7 8 9
 * 2 4 6 8
 * 2 6
 * 6
 *
 * Output:
 * 6
 */
public class EliminationGame {
    public static void main(String[] args) {
        EliminationGame eliminationGame = new EliminationGame();
        eliminationGame.lastRemaining(9);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/5860706.html
     * @param n
     * @return
     */
    public int lastRemaining(int n) {
        return help(n, true);
    }
    public int help(int n, boolean left2right) {
        if (n == 1)
            return 1;
        if (left2right) {
            return 2 * help(n / 2, false);
        } else {
            return 2 * help(n / 2, true) - 1 + n % 2;
        }
    }



    int lastRemainingV2(int n) {
        return n == 1 ? 1 : 2 * (1 + n / 2 - lastRemainingV2(n / 2));
    }

    int lastRemainingV3(int n) {
        int step = 1, res = 1;
        while (step * 2 <= n) {
            res += step;
            step *= 2;
            if (step * 2 > n) break;
            if ((n / step) % 2 == 1) res += step;
            step *= 2;
        }
        return res;
    }


    int lastRemainingV4(int n) {
        boolean left2right = true;
        int res = 1, step = 1, remain = n;
        while (remain > 1) {
            if (left2right || remain % 2 == 1)
                res += step;
            remain /= 2;
            step *= 2;
            left2right = !left2right;
        }
        return res;
    }

    public int lastRemainingOld(int n) {
        Stack<Integer> stack1 = new Stack<>();
        for (int i = n; i >= 1 ; i--) {
            stack1.push(i);
        }
        Stack<Integer> stack2 = new Stack<>();
        Stack<Integer> a;
        Stack<Integer> b;
        boolean eliminated;
        boolean leftToRight = true;
        while (stack1.size() != 1 && stack2.size()!=1) {
            eliminated = true;
            if(leftToRight){
                a = stack1;
                b = stack2;
            } else {
                a = stack2;
                b = stack1;
            }
            leftToRight = !leftToRight;
            while (!a.isEmpty()) {
                if (eliminated) {
                    a.pop();
                } else {
                    b.push(a.pop());
                }
                eliminated = !eliminated;
            }
        }

        return stack1.size() == 1?stack1.peek():stack2.peek();
    }
}
