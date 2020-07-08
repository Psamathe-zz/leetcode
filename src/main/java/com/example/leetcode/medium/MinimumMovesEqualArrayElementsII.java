package com.example.leetcode.medium;

import java.util.Arrays;

/**
 * Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.
 *
 * You may assume the array's length is at most 10,000.
 *
 * Example:
 *
 * Input:
 * [1,2,3]
 *
 * Output:
 * 2
 *
 * Explanation:
 * Only two moves are needed (remember each move increments or decrements one element):
 *
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 * [1,0,0,8,6]
 */
public class MinimumMovesEqualArrayElementsII {
    public static void main(String[] args) {
        int[] nums = new int[]{1,0,0,8,6};
        MinimumMovesEqualArrayElementsII minimumMovesEqualArrayElementsII = new MinimumMovesEqualArrayElementsII();
        minimumMovesEqualArrayElementsII.minMoves2(nums);
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


    /**
     * 快排
     * @param nums
     * @return
     */
    public int minMoves2V2(int[] nums) {
        int sum = 0;
        int median = select(nums, 0, nums.length - 1, nums.length / 2);

        for (int num : nums) {
            sum += Math.abs(median - num);
        }
        return sum;
    }
    public void swap(int[] list, int i, int pivot_index) {
        int temp = list[i];
        list[i] = list[pivot_index];
        list[pivot_index] = temp;
    }
    public int partition(int[] list, int left, int right) {
        int pivotValue = list[right];
        int i = left;
        for (int j = left; j <= right; j++) {
            if (list[j] < pivotValue) {
                swap(list, i, j);
                i++;
            }
        }
        swap(list, right, i);
        return i;
    }
    int select(int[] list, int left, int right, int k) {
        if (left == right) {
            return list[left];
        }
        int pivotIndex = partition(list, left, right);
        if (k == pivotIndex) {
            return list[k];
        } else if (k < pivotIndex) {
            return select(list, left, pivotIndex - 1, k);
        } else {
            return select(list, pivotIndex + 1, right, k);
        }
    }

}
