package com.example.leetcode.biweeklycontest.old.contest43;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an integer n, find a sequence that satisfies all of the following:
 *
 * The integer 1 occurs once in the sequence.
 * Each integer between 2 and n occurs twice in the sequence.
 * For every integer i between 2 and n, the distance between the two occurrences of i is exactly i.
 * The distance between two numbers on the sequence, a[i] and a[j], is the absolute difference of their indices, |j - i|.
 *
 * Return the lexicographically largest sequence. It is guaranteed that under the given constraints, there is always a solution.
 *
 * A sequence a is lexicographically larger than a sequence b (of the same length) if in the first position where a and b differ, sequence a has a number greater than the corresponding number in b. For example, [0,1,9,0] is lexicographically larger than [0,1,5,6] because the first position they differ is at the third number, and 9 is greater than 5.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: [3,1,2,3,2]
 * Explanation: [2,3,2,1,3] is also a valid sequence, but [3,1,2,3,2] is the lexicographically largest valid sequence.
 * Example 2:
 *
 * Input: n = 5
 * Output: [5,3,1,4,3,5,2,4,2]
 *
 */
public class ConstructLexicographically {
    public static void main(String[] args) {

    }
    /**
     * https://leetcode-cn.com/problems/construct-the-lexicographically-largest-valid-sequence/solution/tan-xin-hui-su-by-chenchuxin-jcy5/
     * @param n
     * @return
     */

    private int[] ans;
    private Set<Integer> visited = new HashSet<Integer>();

    public int[] constructDistancedSequence(int n) {
        int lens = 2 * n - 1;
        ans = new int[lens];
        dfs(0, n, lens);
        return ans;
    }

    public boolean dfs(int start, int n, int lens) {
        if (visited.size() == n) {
            return true;
        }

        // 当前位置已经填了，那就进行下一个吧
        if (ans[start] > 0) {
            return dfs(start + 1, n, lens);
        }

        // 贪心：数字从大到小填
        for (int i = n; i > 0; i--) {
            if (visited.contains(i)) {
                continue;
            }
            int sec = start + i;
            if (i == 1 || (sec < lens && ans[sec] == 0)) {
                visited.add(i);
                ans[start] = i;
                if (i > 1) {
                    ans[sec] = i;
                }
                if (dfs(start + 1, n, lens)) {
                    return true;
                }
                visited.remove(i);
                ans[start] = 0;
                if (i > 1) {
                    ans[sec] = 0;
                }
            }
        }
        return false;
    }

}
