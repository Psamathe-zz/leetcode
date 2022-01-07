package com.example.leetcode.challenge.test2022.january;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
 *
 * You are given the integer capacity and an array trips where trip[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.
 *
 * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 * Example 2:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 */
public class CarPooling {
    public static void main(String[] args) {

    }

    public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, (t1, t2) -> {
            if (t1[1] == t2[1])
                return t1[0] - t2[0];
            else
                return t1[1] - t2[1];
        });

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(t -> t[2]));
        int cap = 0;
        int start;
        int end;
        for (int[] trip : trips) {
            start = trip[1];
            while (!queue.isEmpty()) {
                int[] temp = queue.peek();
                if(temp[2] <= start) {
                    queue.poll();
                    cap -= temp[0];
                } else {
                    break;
                }
            }
            cap += trip[0];
            if(cap > capacity)
                 return false;
            queue.add(trip);
        }
        return true;
    }
}
