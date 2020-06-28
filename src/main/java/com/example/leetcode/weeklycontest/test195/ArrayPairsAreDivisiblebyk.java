package com.example.leetcode.weeklycontest.test195;

/**
 * Given an array of integers arr of even length n and an integer k.
 *
 * We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.
 *
 * Return True If you can find a way to do that or False otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
 * Output: true
 * Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).
 * Example 2:
 *
 * Input: arr = [1,2,3,4,5,6], k = 7
 * Output: true
 * Explanation: Pairs are (1,6),(2,5) and(3,4).
 * Example 3:
 *
 * Input: arr = [1,2,3,4,5,6], k = 10
 * Output: false
 * Explanation: You can try all possible pairs to see that there is no way to divide arr into 3 pairs each with sum divisible by 10.
 * Example 4:
 *
 * Input: arr = [-10,10], k = 2
 * Output: true
 * Example 5:
 *
 * Input: arr = [-1,1,-2,2,-3,3,-4,4], k = 3
 * Output: true
 *
 *
 * Constraints:
 *
 * arr.length == n
 * 1 <= n <= 10^5
 * n is even.
 * -10^9 <= arr[i] <= 10^9
 * 1 <= k <= 10^5
 */
public class ArrayPairsAreDivisiblebyk {
    public static void main(String[] args) {
        int[] arr = new int[]{10,-10};
        int k = 4;
        ArrayPairsAreDivisiblebyk arrayPairsAreDivisiblebyk = new ArrayPairsAreDivisiblebyk();
        arrayPairsAreDivisiblebyk.canArrangeV1(arr,k);
   }

    /**
     * https://www.acwing.com/file_system/file/content/whole/index/content/1063235/
     *
     * 开一个长度为k的数组cnt，来统计每个数对k取模后结果相同的个数。对k取模后为0的需要单独考虑，只能取模后为0的数字相互组合，所以个数必须是偶数。
     * 对于取模后大于0的需要考虑k的奇偶性，
     * 如果k是奇数，那么k-1是偶数，也就是取模后值为1的和k-1组合，2和k - 2组合，依此类推，所以只需要检查每队对应的个数是否相同；
     * 如果k是偶数，那么最后会剩下k / 2的自己配对，所以需要检查k/2的个数必须是偶数。
     *
     * @param arr
     * @param k
     * @return
     */
    public boolean canArrange(int[] arr, int k) {
        int[] cnt = new int[k];
        for (int e : arr)
            ++cnt[(e % k + k) % k];

        if (cnt[0] % 2 == 1)
            return false;
        int half = k >> 1;
        if (k % 2 == 1) {
            for (int i = 1; i <= half; ++i) {
                if (cnt[i] != cnt[k - i])
                    return false;
            }
        }
        else {
            for (int i = 1; i < half; ++i) {
                if (cnt[i] != cnt[k - i])
                    return false;
            }
            if (cnt[half] % 2  == 1)
                return false;
        }

        return true;
    }

    /**
     * faster solution
     * @param arr
     * @param k
     * @return
     */
    public boolean canArrangeV1(int[] arr, int k) {
        int[] mod = new int[k];
        for (int x : arr)
            mod[Math.floorMod(x, k)]++;
        if (mod[0] % 2 == 1)
            return false;
        for (int i = 1; i < k; i++) {
            if (mod[i] != mod[k - i])
                return false;
        }
        return true;
    }


}
