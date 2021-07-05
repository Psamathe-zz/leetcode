package com.example.leetcode.challenge.test2021.june.week4;

import java.util.Arrays;

/**
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 *
 *
 *
 * Example 1:
 *
 * Input: ratings = [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 *
 * Input: ratings = [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 *[1,3,2,2,1]
 */
public class Candy {
    public static void main(String[] args) {
        Candy candy = new Candy();
        candy.candy(new int[]{1,3,2,2,1});
    }

    public int candy(int[] ratings) {
        int length = ratings.length;
        int[] candys = new int[length];
        int res = length;
        Arrays.fill(candys, 1);

        for (int i = 1; i < length; i++) {
            if(ratings[i] > ratings[i - 1] && candys[i] <= candys[i - 1]){
                res += candys[i - 1] + 1 - candys[i];
                candys[i] = candys[i - 1] + 1;
            }
        }
        for (int i = length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1] && candys[i] <= candys[i + 1] ) {
                res += candys[i + 1] + 1 - candys[i];
                candys[i] = candys[i + 1] + 1;
            }
        }
        return res;
    }
}
