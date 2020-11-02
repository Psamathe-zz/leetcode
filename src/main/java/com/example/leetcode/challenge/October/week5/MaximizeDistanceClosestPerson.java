package com.example.leetcode.challenge.October.week5;


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
 *
 *
 * Constraints:
 *
 * 2 <= seats.length <= 2 * 104
 * seats[i] is 0 or 1.
 * At least one seat is empty.
 * At least one seat is occupied.
 * [1,0,0,0]
 */
public class MaximizeDistanceClosestPerson {
    public static void main(String[] args) {
        int[] seats = new int[]{1,0,0,0};
        MaximizeDistanceClosestPerson maximizeDistanceClosestPerson = new MaximizeDistanceClosestPerson();
        maximizeDistanceClosestPerson.maxDistToClosest(seats);
    }

    public int maxDistToClosest(int[] seats) {
        int length = seats.length;

        int[] left = new int[length];
        int[] right = new int[length];
        int[] distance = new int[length];

        int dis = 0;
        boolean findSomeone = false;
        for (int i = 0; i < length; i++) {
            if(seats[i] == 1) {
                dis = 0;
                findSomeone = true;
            } else if(findSomeone) {
                dis++;
            }
            if(findSomeone) {
                left[i] = dis;
            } else {
                left[i] = Integer.MAX_VALUE;
            }
        }

        dis = 0;
        findSomeone = false;
        for (int i = length - 1; i >= 0; i--) {
            if(seats[i] == 1) {
                dis = 0;
                findSomeone = true;
            } else if(findSomeone) {
                dis++;
            }
            if(findSomeone) {
                right[i] = dis;
            } else {
                right[i] = Integer.MAX_VALUE;
            }
        }

        int res = 0;
        for (int i = 0; i < length; i++) {
            distance[i] = Math.min(left[i],right[i]);
            res = Math.max(res,distance[i]);
        }

        return res;
    }


    /**
     * faster solution
     * @param seats
     * @return
     */
    public int maxDistToClosestV1(int[] seats) {
        if (seats == null || seats.length == 0) {
            return 0;
        }

        int ans = 0; int k = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                k = 0;
            } else {
                k++;
                ans = Math.max(ans, (k + 1) / 2);
            }
        }

        // left
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                ans = Math.max(ans, i);
                break;
            }
        }

        // right
        for (int i = seats.length - 1; i >= 0; i--) {
            if (seats[i] == 1) {
                ans = Math.max(ans, seats.length - 1 - i);
                break;
            }
        }

        return ans;
    }
}
