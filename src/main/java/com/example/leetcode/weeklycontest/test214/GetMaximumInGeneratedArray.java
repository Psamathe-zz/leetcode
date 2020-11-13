package com.example.leetcode.weeklycontest.test214;


import java.util.HashMap;
import java.util.Map;

/**
 * You are given an integer n. An array nums of length n + 1 is generated in the following way:
 *
 * nums[0] = 0
 * nums[1] = 1
 * nums[2 * i] = nums[i] when 2 <= 2 * i <= n
 * nums[2 * i + 1] = nums[i] + nums[i + 1] when 2 <= 2 * i + 1 <= n
 * Return the maximum integer in the array nums​​​.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 7
 * Output: 3
 * Explanation: According to the given rules:
 *   nums[0] = 0
 *   nums[1] = 1
 *   nums[(1 * 2) = 2] = nums[1] = 1
 *   nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
 *   nums[(2 * 2) = 4] = nums[2] = 1
 *   nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
 *   nums[(3 * 2) = 6] = nums[3] = 2
 *   nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
 * Hence, nums = [0,1,1,2,1,3,2,3], and the maximum is 3.
 * Example 2:
 *
 * Input: n = 2
 * Output: 1
 * Explanation: According to the given rules, the maximum between nums[0], nums[1], and nums[2] is 1.
 * Example 3:
 *
 * Input: n = 3
 * Output: 2
 * Explanation: According to the given rules, the maximum between nums[0], nums[1], nums[2], and nums[3] is 2.
 *
 *
 * Constraints:
 *
 * 0 <= n <= 100
 */
public class GetMaximumInGeneratedArray {
    public static void main(String[] args) {
        GetMaximumInGeneratedArray getMaximumInGeneratedArray = new GetMaximumInGeneratedArray();
        getMaximumInGeneratedArray.getMaximumGenerated(15);
    }
    int max = 0;
    Map<Integer,Integer> map = new HashMap<>();
    public int getMaximumGenerated(int n) {
        map.put(0,0);
        map.put(1,1);
        for (int i = 0; i <= n; i++) {
            help(i);
        }

        return max;
    }
    public int help(int n) {
        int val;
        if(map.containsKey(n)) {
            val = map.get(n);
        } else if(n % 2 == 0) {
            val = help(n / 2);
            map.put(n,val);
        } else {
            val = help(n / 2) + help(n / 2 + 1);
            map.put(n,val);
        }

        max = Math.max(max,val);
        return val ;
    }

    /**
     * faster
     * @param n
     * @return
     */
    public int getMaximumGeneratedV1(int n) {
        int[] result = new int[n+1];
        int max = 1;
        if(n == 0)
            return 0;
        result[1] = 1;
        for(int i=1; i<= n/2; i++){
            if(i*2 <= n)
                result[i*2] = result[i];
            if(i*2 + 1 <= n) {
                result[i*2+1] = result[i] + result[i+1];
                max =  Math.max(max,result[i*2+1]);
            }
        }

        return max;
    }
}
