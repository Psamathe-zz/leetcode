package com.example.leetcode.biweeklycontest.old.contest42;


import java.util.ArrayList;
import java.util.List;

/**
 * You are given an integer array, nums, and an integer k. nums comprises of only 0's and 1's. In one move, you can choose two adjacent indices and swap their values.
 *
 * Return the minimum number of moves required so that nums has k consecutive 1's.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,0,1,0,1], k = 2
 * Output: 1
 * Explanation: In 1 move, nums could be [1,0,0,0,1,1] and have 2 consecutive 1's.
 * Example 2:
 *
 * Input: nums = [1,0,0,0,0,0,1,1], k = 3
 * Output: 5
 * Explanation: In 5 moves, the leftmost 1 can be shifted right until nums = [0,0,0,0,0,1,1,1].
 * Example 3:
 *
 * Input: nums = [1,1,0,1], k = 2
 * Output: 0
 * Explanation: nums already has 2 consecutive 1's.
 */
public class MinimumAdjacentSwaps {
    public static void main(String[] args) {

    }

    /**
     *https://leetcode-cn.com/problems/minimum-adjacent-swaps-for-k-consecutive-ones/solution/de-dao-lian-xu-k-ge-1-de-zui-shao-xiang-fqbhp/
     * 题目求的是最少的交换次数，如果交换1和1或者0和0是没有任何意义的，只会增加交换次数
     所以我们只考虑1和0的交换
     也就是我们只需要交换其他的1到某一个位置，构成一段长度为k下标连续的1即可，
     这样就说明1的相对顺序一定是不变的
     我们只需记录下数字1的下标，假设为
     a1, a2, a3 ...  an

     答案就转换为： 需要把连续的k个ai, 交换到一起的最小交换次数
     假设k个a为：
     a1, a2 ... ak, 也就是需要把这k个a，交换变成公差为1的等差数列
     -->   假设移动到 x, x+1, ... , x+k-1
     那么步数就是-->   |a1-x| + |a2-(x+1)| + ... + |ak-(x+k-1)|       公式1
     也就是求x，使得上述绝对值的和最小
     也就是求上述点到一个公差为1等差数列的距离的最小值
     就可以联系到基础课的货仓选址(https://www.ac-w-ing.com/problem/content/106/, 把-去掉)，所有点到一个点的绝对值的和的最小值， x等于中位数
     但是这个是等差数列，需要做一下映射
     令ai` = ai - (i - 1)
     a1` = a1
     a2` = a2 - 1
     a3` = a3 - 2
     ...
     ak` = ak - (k - 1)
     代入公式1也就变成： |a1`-x| + |a2`-x| + ... + |ak`-x|                公式2
     公式2的最小值就是当x取a1`,a2`, ... ak`的中位数， 求出来的和就是最小值, 就是所求之和


     还有一个问题， 我们是每次求连续k个ai的和的最小值, 需要快速的求每个数与中位数的差的绝对值的和
     假设其他k个a为： a[l], a[l+1], ... a[r], 中位数是a[mid](mid = l + r >> 1);

     mid左边的和： a[mid] - a[l] + a[mid] - a[l+1] + ... + a[mid] - a[mid - 1]
     = a[mid] * (mid - l) - (a[l] + a[l + 1] + ... + a[mid - 1])
     = a[mid] * (mid - l) - (sum[mid - 1] - sum[l - 1])  前缀和

     mid右边的和： a[r] - a[mid] + a[r - 1] - a[mid] + ... + a[mid + 1] - a[mid]
     = sum[r] - sum[mid]  - (r - mid ) * a[mid]

     需要预处理下前缀和
     *
     * @param nums
     * @param k
     * @return
     */
    public int minMoves(int[] nums, int k) {
        int n = nums.length;
        List<Long> list = new ArrayList(n);
        for(int i = 0; i < n; i++){
            if(nums[i] == 1)
                list.add((long)(i - list.size() - 1));
        }
        long[] sum = new long[list.size() + 1];
        for(int i = 1; i <= list.size(); i++){
            sum[i] = sum[i - 1] + list.get(i - 1);
        }
        long min = Long.MAX_VALUE;
        for(int i = 0; i + k - 1 < list.size(); i++){
            int j = i + k - 1;
            int mid = i + j >> 1;
            long left = list.get(mid) * (mid - i) - (sum[mid] - sum[i]);
            long right = sum[j + 1] - sum[mid + 1] - (j - mid) * list.get(mid);
            min = Math.min(min, left + right);
        }
        return (int)min;
    }

}
