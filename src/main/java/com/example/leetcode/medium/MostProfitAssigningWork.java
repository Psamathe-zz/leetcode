package com.example.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i] is the profit of the ith job.
 *
 * Now we have some workers. worker[i] is the ability of the ith worker, which means that this worker can only complete a job with difficulty at most worker[i].
 *
 * Every worker can be assigned at most one job, but one job can be completed multiple times.
 *
 * For example, if 3 people attempt the same job that pays $1, then the total profit will be $3.  If a worker cannot complete any job, his profit is $0.
 *
 * What is the most profit we can make?
 *
 * Example 1:
 *
 * Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 * Output: 100
 * Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get profit of [20,20,30,30] seperately.
 * Notes:
 *
 * 1 <= difficulty.length = profit.length <= 10000
 * 1 <= worker.length <= 10000
 * difficulty[i], profit[i], worker[i]  are in range [1, 10^5]
 * [5,50,92,21,24,70,17,63,30,53]
 * [68,100,3,99,56,43,26,93,55,25]
 * [96,3,55,30,11,58,68,36,26,1]
 */
public class MostProfitAssigningWork {
    public static void main(String[] args) {
        int[] difficulty = new int[]{5,50,92,21,24,70,17,63,30,53};
        int[] profit = new int[]{68,100,3,99,56,43,26,93,55,25};
        int[] worker = new int[]{96,3,55,30,11,58,68,36,26,1};
        MostProfitAssigningWork mostProfitAssigningWork = new MostProfitAssigningWork();
        int result = mostProfitAssigningWork.maxProfitAssignmentV1(difficulty, profit, worker);
        System.out.println(result);
    }

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int length = difficulty.length;
        int workers = worker.length;
        int result = 0;
        int temp;
        for (int i = 0; i < workers; i++) {
            temp = 0;
            for (int j = 0; j < length; j++) {
                if(temp < profit[j] && difficulty[j] <= worker[i]){
                    temp = profit[j];
                }
            }
            result += temp;
        }

        return result;
    }

    public int maxProfitAssignmentV1(int[] difficulty, int[] profit, int[] worker) {
        int lenTask = difficulty.length;
        int maxAbility = 0;
        for (int w : worker)
            if (w > maxAbility)
                maxAbility = w;

        int acc[] = new int[maxAbility + 1];
        for (int i = 0; i < lenTask; i++)
        {
            if (difficulty[i] > maxAbility)
                continue;
            if (acc[difficulty[i]] < profit[i])
                acc[difficulty[i]] = profit[i];
        }
        for (int i = 1; i <= maxAbility; i++)
            if (acc[i] < acc[i - 1])
                acc[i] = acc[i - 1];
        int sum = 0;
        for (int w : worker)
            sum += acc[w];
        return sum;
    }
}
