package com.example.leetcode.biweeklycontest.contest49;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * There is a donuts shop that bakes donuts in batches of batchSize. They have a rule where they must serve all of the donuts of a batch before serving any donuts of the next batch. You are given an integer batchSize and an integer array groups, where groups[i] denotes that there is a group of groups[i] customers that will visit the shop. Each customer will get exactly one donut.
 *
 * When a group visits the shop, all customers of the group must be served before serving any of the following groups. A group will be happy if they all get fresh donuts. That is, the first customer of the group does not receive a donut that was left over from the previous group.
 *
 * You can freely rearrange the ordering of the groups. Return the maximum possible number of happy groups after rearranging the groups.
 *
 *
 *
 * Example 1:
 *
 * Input: batchSize = 3, groups = [1,2,3,4,5,6]
 * Output: 4
 * Explanation: You can arrange the groups as [6,2,4,5,1,3]. Then the 1st, 2nd, 4th, and 6th groups will be happy.
 * Example 2:
 *
 * Input: batchSize = 4, groups = [1,3,2,5,2,2,1,6]
 * Output: 4
 */
public class MaximumNumberGroups {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/maximum-number-of-groups-getting-fresh-donuts/solution/can-kao-httpsleetcodecomproblemsmaximum-00o6t/
     */
    Map<String, Integer> map = new HashMap<>();
    public int maxHappyGroups(int batchSize, int[] groups) {
        // 记录不同余数的数组
        int[] countArr = new int[batchSize];
        // 记录还没有使用的数目
        int remainCount = 0;
        for (int group : groups) {
            countArr[group % batchSize]++;
            if (group % batchSize != 0) {
                remainCount++;
            }
        }
        // 第一次进入肯定开心，所以第一次进入是0，可以+1
        return countArr[0] + dfs(0, countArr, remainCount);
    }

    private int dfs(int remainValue, int[] countArr, int remainCount) {
        if (remainCount == 0) {
            return 0;
        }
        int batchSize = countArr.length;
        String key = Arrays.toString(countArr) + remainValue;
        if (map.get(key) != null) {
            return map.get(key);
        }
        int result = 0;
        // 为0则+1,小于0时候就添加batchSize
        if (remainValue == 0) {
            result++;
        } else if (remainValue < 0) {
            remainValue += batchSize;
        }
        // 遍历数组中每个有值的余数
        for (int i = 1; i < batchSize; i++) {
            if (countArr[i] != 0) {
                countArr[i]--;
                int dfs = dfs(remainValue - i, countArr, remainCount - 1);
                countArr[i]++;
                if (remainValue == i) {
                    // 如果加入这个就可以直接得到余数，就直接返回
                    return dfs + 1;
                } else {
                    result = Math.max(dfs, result);
                }
            }
        }
        map.put(key, result);
        return result;
    }


    /**
     * faster solution
     */
    boolean[] used;
    public int maxHappyGroupsV0(int batchSize, int[] groups) {
        int n = groups.length;
        used = new boolean[n];
        int count = 0;
        for (int s = 1; s <= batchSize; s++) {
            while (solve(s, 0, 0, batchSize, groups)) {
                // System.out.println(s + " " + Arrays.toString(used));
                count++;
            }
        }
        // System.out.println();
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                count++;
                break;
            }
        }
        return count;
    }

    private boolean solve(int s, int r, int i, int batchSize, int[] groups) {
        int n = groups.length;
        if (i == n || s == 0) {
            return s == 0 && r == 0;
        }
        if (used[i]) {
            return solve(s, r, i+1, batchSize, groups);
        }
        for (int j = i; j < n; j++) {
            if (used[j]) {
                continue;
            }
            used[j] = true;
            if (solve(s-1, (r+groups[j])%batchSize, j+1, batchSize, groups)) {
                return true;
            }
            used[j] = false;
        }
        return false;
    }

}
