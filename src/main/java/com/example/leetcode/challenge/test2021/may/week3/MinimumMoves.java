package com.example.leetcode.challenge.test2021.may.week3;


import java.util.Arrays;
import java.util.Map;

/**
 * Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
 *
 * In one move, you can increment or decrement an element of the array by 1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: 2
 * Explanation:
 * Only two moves are needed (remember each move increments or decrements one element):
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 * Example 2:
 *
 * Input: nums = [1,10,2,9]
 * Output: 16
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class MinimumMoves {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/6089060.html
     * https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements-ii/solution/zui-shao-yi-dong-ci-shu-shi-shu-zu-yuan-su-xiang-2/
     * @param nums
     * @return
     *
     * 这是一个经典的数学问题，当 x 为这个 N 个数的中位数时，可以使得距离最小。具体地，若 N 为奇数，则 x 必须为这 N 个数中的唯一中位数；若 N 为偶数，中间的两个数为 p 和 q，中位数为 (p + q) / 2，此时 x 只要是区间 [p, q] 中的任意一个数即可。
     *
     * 因此，我们只需要找到这个 N 个数的中位数，并计算距离之和。我们可以直接将数组进行排序，这样就直接得到了中位数
     */

    int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int res = 0, mid = nums[nums.length / 2];
        for (int num : nums) {
            res += Math.abs(num - mid);
        }
        return res;
    }
}
