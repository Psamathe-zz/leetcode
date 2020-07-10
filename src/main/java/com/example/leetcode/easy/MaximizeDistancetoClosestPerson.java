package com.example.leetcode.easy;

/**
 * In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.
 *
 * There is at least one empty seat, and at least one person sitting.
 *
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 *
 * Return that maximum distance to closest person.
 *
 * Example 1:
 *
 * Input: [1,0,0,0,1,0,1]
 * Output: 2
 * Explanation:
 * If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
 * If Alex sits in any other open seat, the closest person has distance 1.
 * Thus, the maximum distance to the closest person is 2.
 * Example 2:
 *
 * Input: [1,0,0,0]
 * Output: 3
 * Explanation:
 * If Alex sits in the last seat, the closest person is 3 seats away.
 * This is the maximum distance possible, so the answer is 3.
 *
 *
 * Constraints:
 *
 * 2 <= seats.length <= 20000
 * seats contains only 0s or 1s, at least one 0, and at least one 1.
 */
public class MaximizeDistancetoClosestPerson {
    public static void main(String[] args) {
        MaximizeDistancetoClosestPerson maximizeDistancetoClosestPerson = new MaximizeDistancetoClosestPerson();
        maximizeDistancetoClosestPerson.maxDistToClosest(new int[]{1,0,0,0,1,0,1});
    }
    public int maxDistToClosest(int[] seats) {
        int length = seats.length;
        int[] left = new int[length];
        int[] right = new int[length];
        int dis =  30000;
        int res = 0;
        for (int i = 0; i < length; i++) {
            if(seats[i] == 1) {
                dis = 0;
                left[i] = 30000;
            }else{
                dis++;
                left[i] = dis;
            }
        }

        for (int i = length - 1; i >= 0; i--) {
            if(seats[i] == 1) {
                dis = 0;
                right[i] = 30000;
            }else{
                dis++;
                right[i] = dis;
            }
        }
        int t;
        for (int i = 0; i < length; i++) {
            t = Math.min(left[i],right[i]);
            if(t < 30000)
                res = Math.max(res,t);
        }
        return res;
    }
}
