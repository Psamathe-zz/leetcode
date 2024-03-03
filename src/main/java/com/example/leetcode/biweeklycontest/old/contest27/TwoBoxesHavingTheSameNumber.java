package com.example.leetcode.biweeklycontest.old.contest27;

import java.util.Arrays;

/**
 * Given 2n balls of k distinct colors. You will be given an integer array balls of size k where balls[i] is the number of balls of color i.
 *
 * All the balls will be shuffled uniformly at random, then we will distribute the first n balls to the first box and the remaining n balls to the other box (Please read the explanation of the second example carefully).
 *
 * Please note that the two boxes are considered different. For example, if we have two balls of colors a and b, and two boxes [] and (), then the distribution [a] (b) is considered different than the distribution [b] (a) (Please read the explanation of the first example carefully).
 *
 * We want to calculate the probability that the two boxes have the same number of distinct balls.
 *
 *
 *
 * Example 1:
 *
 * Input: balls = [1,1]
 * Output: 1.00000
 * Explanation: Only 2 ways to divide the balls equally:
 * - A ball of color 1 to box 1 and a ball of color 2 to box 2
 * - A ball of color 2 to box 1 and a ball of color 1 to box 2
 * In both ways, the number of distinct colors in each box is equal. The probability is 2/2 = 1
 * Example 2:
 *
 * Input: balls = [2,1,1]
 * Output: 0.66667
 * Explanation: We have the set of balls [1, 1, 2, 3]
 * This set of balls will be shuffled randomly and we may have one of the 12 distinct shuffles with equale probability (i.e. 1/12):
 * [1,1 / 2,3], [1,1 / 3,2], [1,2 / 1,3], [1,2 / 3,1], [1,3 / 1,2], [1,3 / 2,1], [2,1 / 1,3], [2,1 / 3,1], [2,3 / 1,1], [3,1 / 1,2], [3,1 / 2,1], [3,2 / 1,1]
 * After that we add the first two balls to the first box and the second two balls to the second box.
 * We can see that 8 of these 12 possible random distributions have the same number of distinct colors of balls in each box.
 * Probability is 8/12 = 0.66667
 * Example 3:
 *
 * Input: balls = [1,2,1,2]
 * Output: 0.60000
 * Explanation: The set of balls is [1, 2, 2, 3, 4, 4]. It is hard to display all the 180 possible random shuffles of this set but it is easy to check that 108 of them will have the same number of distinct colors in each box.
 * Probability = 108 / 180 = 0.6
 * Example 4:
 *
 * Input: balls = [3,2,1]
 * Output: 0.30000
 * Explanation: The set of balls is [1, 1, 1, 2, 2, 3]. It is hard to display all the 60 possible random shuffles of this set but it is easy to check that 18 of them will have the same number of distinct colors in each box.
 * Probability = 18 / 60 = 0.3
 * Example 5:
 *
 * Input: balls = [6,6,6,6,6,6]
 * Output: 0.90327
 *
 *
 * Constraints:
 *
 * 1 <= balls.length <= 8
 * 1 <= balls[i] <= 6
 * sum(balls) is even.
 * Answers within 10^-5 of the actual value will be accepted as correct.
 */
public class TwoBoxesHavingTheSameNumber {
    public static void main(String[] args) {
        int[] balls = new int[]{6,6,6,6,6,6};
        TwoBoxesHavingTheSameNumber twoBoxesHavingTheSameNumber = new TwoBoxesHavingTheSameNumber();
        double result = twoBoxesHavingTheSameNumber.getProbability(balls);
        System.out.println(result);
    }

    /**
     * https://www.acwing.com/file_system/file/content/whole/index/content/555127/
     */
    int totalNumber;
    int halfNumber;
    double[][] map;
    int[] boxA;
    int[] boxB;
    public double getProbability(int[] balls) {
        totalNumber = Arrays.stream(balls).sum();
        if(totalNumber == 0)
            return 0;
        halfNumber = totalNumber >> 1;

        int n = balls.length;

        boxA = new int[n];
        boxB = new int[n];
        map = new double[49][49];
        for (int i = 0; i < 49; i++) {
            Arrays.fill(map[i],1);
            for (int j = 1; j < i; j++) {
                map[i][j] = map[i - 1][j - 1] + map[i - 1][j];
            }
        }
        double up = help(balls, 0, 0, 0);
        double down = combination(balls);

        return  up / down;
    }

    public double combination( int[] nums)
    {
        double res = 1;
        int sum = Arrays.stream(nums).sum();
        for (int value : nums) {
            if (value != 0) {
                res *= map[sum][value];
                sum -= value;
            }
        }
        return res;
    }


    public double help(int[] balls, int pos, int leftSum, int rightSum)
    {
        if (leftSum > halfNumber || rightSum > halfNumber)
            return 0;
        if (pos == balls.length) {
            // vector<int> tmpA = firstbox, tmpB = secondBox;
            // sort(tmpA.begin(), tmpA.end());
            // sort(tmpB.begin(), tmpB.end());

            //if (tmpA != tmpB) return 0;
            int cntA = 0, cntB = 0;
            for (int value : boxA)
                if(value != 0)
                    ++cntA;
            for (int value: boxB)
                if (value != 0)
                    ++cntB;
            if (cntA != cntB)
                return 0;

            return combination(boxA) * combination(boxB);
        }

        double res = 0;
        for (int i = 0; i <= balls[pos]; ++i) {
            boxA[pos] = i;
            boxB[pos] = balls[pos] - i;
            res += help(balls, pos + 1, leftSum + boxA[pos], rightSum + boxB[pos]);
        }

        return res;
    }


    /**
     * faster solution
     * @param balls
     * @return
     */
    public double getProbabilityV1(int[] balls) {
        int numColors = balls.length;
        int n = 0;
        for (int b : balls)
            n += b;
        binom = getBinom(n, n/2);
        long ways = backtrack(0, n/2, balls, new int[balls.length]);
        return (double) ways / binom[n][n/2];
    }

    long[][] binom;

    long backtrack(int i, int toPick, int[] balls, int[] picked) {
        if (i == balls.length) {
            if (toPick != 0) return 0l;
            int countA = 0;
            int countB = 0;
            long ways = 1;
            for (int j = 0; j < balls.length; j++) {
                if (picked[j] == 0) countB++;
                else if (picked[j] == balls[j]) countA++;
                ways *= binom[balls[j]][picked[j]];
            }
            return countA == countB ? ways : 0l;
        }
        long ways = 0;
        for (int pick = 0; pick <= Math.min(toPick, balls[i]); pick++) {
            picked[i] = pick;
            ways += backtrack(i+1, toPick-pick, balls, picked);
        }
        picked[i] = 0;
        return ways;
    }

    long[][] getBinom(int n, int k) {
        long[][] dp = new long[n+1][k+1];
        for (int i = 0; i <= n; i++) dp[i][0] = 1;
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= Math.min(i, k); j++)
                dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
        return dp;
    }
}
