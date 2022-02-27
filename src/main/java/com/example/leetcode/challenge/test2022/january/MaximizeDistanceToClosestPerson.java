package com.example.leetcode.challenge.test2022.january;

/**
 * You are given an array representing a row of seats where seats[i] = 1 represents a person sitting in the ith seat, and seats[i] = 0 represents that the ith seat is empty (0-indexed).
 *
 * There is at least one empty seat, and at least one person sitting.
 *
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 *
 * Return that maximum distance to the closest person.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: seats = [1,0,0,0,1,0,1]
 * Output: 2
 * Explanation:
 * If Alex sits in the second open seat (i.e. seats[2]), then the closest person has distance 2.
 * If Alex sits in any other open seat, the closest person has distance 1.
 * Thus, the maximum distance to the closest person is 2.
 * Example 2:
 *
 * Input: seats = [1,0,0,0]
 * Output: 3
 * Explanation:
 * If Alex sits in the last seat (i.e. seats[3]), the closest person is 3 seats away.
 * This is the maximum distance possible, so the answer is 3.
 * Example 3:
 *
 * Input: seats = [0,1]
 * Output: 1
 */
public class MaximizeDistanceToClosestPerson {
    public static void main(String[] args) {
        MaximizeDistanceToClosestPerson maximizeDistanceToClosestPerson = new MaximizeDistanceToClosestPerson();
        maximizeDistanceToClosestPerson.maxDistToClosest(new int[]{0,1});
    }

    public int maxDistToClosest(int[] seats) {
        int length = seats.length;
        int[] left = new int[length];
        int[] right = new int[length];

        int val = length;
        for (int i = 0; i < length; i++) {
            if(seats[i] == 1){
                val = 0;
            } else
                val++;
            left[i] = val;
        }
        val = length;
        for (int i = length - 1; i >= 0; i--) {
            if(seats[i] == 1){
                val = 0;
            } else
                val++;
            right[i] = val;
        }
        int res = 0;
        for (int i = 0; i < length; i++) {
            res = Math.max(res, Math.min(left[i], right[i]));
        }
        return res;
    }
}
