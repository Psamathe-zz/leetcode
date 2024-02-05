package com.example.leetcode.weeklycontest.old.test333;

/**
 * You are given a positive integer 0-indexed array nums.
 *
 * A subset of the array nums is square-free if the product of its elements is a square-free integer.
 *
 * A square-free integer is an integer that is divisible by no square number other than 1.
 *
 * Return the number of square-free non-empty subsets of the array nums. Since the answer may be too large, return it modulo 109 + 7.
 *
 * A non-empty subset of nums is an array that can be obtained by deleting some (possibly none but not all) elements from nums. Two subsets are different if and only if the chosen indices to delete are different.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,4,4,5]
 * Output: 3
 * Explanation: There are 3 square-free subsets in this example:
 * - The subset consisting of the 0th element [3]. The product of its elements is 3, which is a square-free integer.
 * - The subset consisting of the 3rd element [5]. The product of its elements is 5, which is a square-free integer.
 * - The subset consisting of 0th and 3rd elements [3,5]. The product of its elements is 15, which is a square-free integer.
 * It can be proven that there are no more than 3 square-free subsets in the given array.
 * Example 2:
 *
 * Input: nums = [1]
 * Output: 1
 * Explanation: There is 1 square-free subset in this example:
 * - The subset consisting of the 0th element [1]. The product of its elements is 1, which is a square-free integer.
 * It can be proven that there is no more than 1 square-free subset in the given array.
 */
public class CountNumberSquareFreeSubsets {
    public static void main(String[] args) {

    }

    /**
     *

     作者：xie-bin-o
     链接：https://leetcode.cn/problems/count-the-number-of-square-free-subsets/solution/java-zhuang-tai-ya-suo-dponkong-jian-by-necqk/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int squareFreeSubsets(int[] nums) {
        int[] d = new int[]{2,3,5,7,11,13,17,19,23,29};
        long[] dp = new long[1<<10];
        dp[0] = 1;
        int mod = 1000000007;
        for(int x : nums){
            if(x == 1){
                dp[0] *= 2;
                dp[0] %= mod;
            }
        }
        for(int x : nums){
            if(x == 1 || x % 4 == 0 || x % 9 == 0 || x % 25 == 0){
                continue;
            }
            int cur = 0;
            for(int i = 0; i < 10; i++){
                if(x % d[i] == 0){
                    cur |= (1 << i);
                }
            }
            for(int i = (1<<10)-1; i >= 0; i--){
                if((i&cur) == 0){
                    dp[i|cur] += dp[i];
                    dp[i|cur] %= mod;
                }
            }
        }
        long ret = 0;
        for(int i = 0; i <(1<<10); i++){
            if(i == 0){
                ret += dp[i]- 1;
            }else{
                ret += dp[i];
            }
            ret %= mod;
        }
        return (int)ret;
    }
}
