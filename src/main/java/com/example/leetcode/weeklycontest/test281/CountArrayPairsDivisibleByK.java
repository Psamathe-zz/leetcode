package com.example.leetcode.weeklycontest.test281;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a 0-indexed integer array nums of length n and an integer k, return the number of pairs (i, j) such that:
 *
 * 0 <= i < j <= n - 1 and
 * nums[i] * nums[j] is divisible by k.
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5], k = 2
 * Output: 7
 * Explanation:
 * The 7 pairs of indices whose corresponding products are divisible by 2 are
 * (0, 1), (0, 3), (1, 2), (1, 3), (1, 4), (2, 3), and (3, 4).
 * Their products are 2, 4, 6, 8, 10, 12, and 20 respectively.
 * Other pairs such as (0, 2) and (2, 4) have products 3 and 15 respectively, which are not divisible by 2.
 * Example 2:
 *
 * Input: nums = [1,2,3,4], k = 5
 * Output: 0
 * Explanation: There does not exist any pair of indices whose corresponding product is divisible by 5.
 *
 * [8,10,2,5,9,6,3,8,2]
 * 6
 */
public class CountArrayPairsDivisibleByK {
    public static void main(String[] args) {
        CountArrayPairsDivisibleByK countArrayPairsDivisibleByK = new CountArrayPairsDivisibleByK();
        countArrayPairsDivisibleByK.countPairs(new int[]{8,10,2,5,9,6,3,8,2}, 6);
    }

    /**
     * https://leetcode-cn.com/problems/count-array-pairs-divisible-by-k/solution/ban-yuan-xiu-dao-ji-suan-chu-shu-de-yin-sddz1/
     * @param nums
     * @param k
     * @return
     */
    public long countPairs(int[] nums, int k) {
        long ans = 0L;
        int n = nums.length;
        if(k == 1) return ((long)n * (long)(n - 1) / 2);
        Map<Integer, Integer> map = new HashMap<>();
        //统计每个数字对应的因子
        for(int x : nums){
            int y = gcd(x, k);
            map.put(y, map.getOrDefault(y, 0) + 1);
        }
        //枚举出现过的因子
        for(int x : map.keySet()){
            for(int y : map.keySet()){
                if(((long)x * y) % k != 0) continue;
                if(x == y){
                    ans += (long)map.get(x) * (map.get(x) - 1);
                }else{
                    ans += (long)map.get(x) * (map.get(y));
                }
            }
        }
        //由于重复计算，所以结果除以2
        return ans / 2;
    }

    public int gcd(int a, int b){
        if(a < b){
            int temp = b;
            b = a;
            a = temp;
        }
        return a % b == 0 ? b : gcd(b, a % b);
    }
}
