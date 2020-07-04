package com.example.leetcode.challenge.July.week1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Write a program to find the n-th ugly number.
 *
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 *
 * Example:
 *
 * Input: n = 10
 * Output: 12
 * Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * Note:
 *
 * 1 is typically treated as an ugly number.
 * n does not exceed 1690.
 */
public class UglyNumberII {
    public static void main(String[] args) {

    }

    /**
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        int i2 = 0, i3 = 0, i5 = 0;
        while (res.size() < n) {
            int m2 = res.get(i2) * 2;
            int m3 = res.get(i3) * 3;
            int m5 = res.get(i5) * 5;
            int mn = Math.min(m2, Math.min(m3, m5));
            if (mn == m2)
                ++i2;
            if (mn == m3)
                ++i3;
            if (mn == m5)
                ++i5;
            res.add(mn);
        }
        return res.get(n - 1);
    }


    int nthUglyNumberV1(int n) {
        PriorityQueue<Long> pq = new PriorityQueue<>(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return o2.compareTo(o1);
            }
        });
        pq.add(1L);
        for (long i = 1; i < n; ++i) {
            long t = pq.poll();
            while (!pq.isEmpty() && pq.peek() == t) {
                t = pq.poll();
            }
            pq.add(t * 2);
            pq.add(t * 3);
            pq.add(t * 5);
        }
        long result = pq.peek();
        return (int)result;
    }

}
