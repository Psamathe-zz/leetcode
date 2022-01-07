package com.example.leetcode.challenge.test2022.january;

import java.util.Map;
import java.util.TreeMap;

/**
 * You are given a list of songs where the ith song has a duration of time[i] seconds.
 *
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
 *
 *
 *
 * Example 1:
 *
 * Input: time = [30,20,150,100,40]
 * Output: 3
 * Explanation: Three pairs have a total duration divisible by 60:
 * (time[0] = 30, time[2] = 150): total duration 180
 * (time[1] = 20, time[3] = 100): total duration 120
 * (time[1] = 20, time[4] = 40): total duration 60
 * Example 2:
 *
 * Input: time = [60,60,60]
 * Output: 3
 * Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 */
public class PairsOfSongs {
    public static void main(String[] args) {
        PairsOfSongs pairsOfSongs = new PairsOfSongs();
        pairsOfSongs.numPairsDivisibleBy60(new int[]{30,20,150,100,40});
    }

    public int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> count = new TreeMap<>();
        for(int val : time) {
            val %= 60;
            count.compute(val, (k,v) -> {
               if(v == null) {
                   return 1;
               } else
                   return v + 1;
            });
        }
        int res = count.getOrDefault(0,0) * (count.getOrDefault(0,0) - 1) / 2;
        res += count.getOrDefault(30,0) * (count.getOrDefault(30,0) - 1) / 2;
        for (Integer key : count.keySet()) {
            if(key >= 1 && key <= 29)
                res += count.getOrDefault(key,0) * count.getOrDefault(60 - key,0);
            if(key > 29)
                break;
        }
        return res;
    }
}
