package com.example.leetcode.weeklycontest.old.test222;


import java.util.*;

/**
 * A good meal is a meal that contains exactly two different food items with a sum of deliciousness equal to a power of two.
 *
 * You can pick any two different foods to make a good meal.
 *
 * Given an array of integers deliciousness where deliciousness[i] is the deliciousness of the i​​​​​​th​​​​​​​​ item of food, return the number of different good meals you can make from this list modulo 109 + 7.
 *
 * Note that items with different indices are considered different even if they have the same deliciousness value.
 *
 *
 *
 * Example 1:
 *
 * Input: deliciousness = [1,3,5,7,9]
 * Output: 4
 * Explanation: The good meals are (1,3), (1,7), (3,5) and, (7,9).
 * Their respective sums are 4, 8, 8, and 16, all of which are powers of 2.
 * Example 2:
 *
 * Input: deliciousness = [1,1,1,3,3,3,7]
 * Output: 15
 * Explanation: The good meals are (1,1) with 3 ways, (1,3) with 9 ways, and (1,7) with 3 ways.
 */
public class CountGoodMeals {
    public static void main(String[] args) {
        int[] deliciousness = new int[]{1,1,1,3,3,3,7};
        CountGoodMeals countGoodMeals = new CountGoodMeals();
        int res = countGoodMeals.countPairs(deliciousness);
        System.out.println(res);
    }

    public int countPairsV0(int[] deliciousness) {
        int res = 0;
        TreeMap<Integer,Integer> map = new TreeMap<>();
        int length = deliciousness.length;
        int cur;
        int sum;
        double temp;
        for (int i = 0; i < length; i++) {
            map.put(deliciousness[i],map.getOrDefault(deliciousness[i],0)+1);
        }

        List<Integer> keys = new ArrayList<>(map.keySet());

        for (int i = 0; i < keys.size() ; i++) {
            cur = 0;
            for (int j = i; j < keys.size(); j++) {
                sum = keys.get(i) + keys.get(j);
                while (sum >= (temp = Math.pow(2,cur)) ){
                    cur++;
                    if(sum == temp ){
                        if(i == j)
                            res += map.get(keys.get(i)) * (map.get(keys.get(i)) - 1) / 2 ;
                        else
                            res += map.get(keys.get(i)) * map.get(keys.get(j));
                        break;
                    }
                }
            }
        }

        return res;
    }

    public int countPairs(int[] deliciousness) {
        Map<Integer, Integer> map = new HashMap<>();
        int mod = 1000000007;
        int answer = 0;
        for (int num : deliciousness) {
            int powerOfTwo = 1;
            // 为什么是21？ 因为数字最大为2^20, 2^20 + 2^20 = 2^21为可能的最大值，不可能再大啦！
            for (int i = 0; i <= 21; i++) {
                if (powerOfTwo >= num && map.containsKey(powerOfTwo - num)) {
                    answer += map.get(powerOfTwo - num);
                    answer %= mod;
                }
                powerOfTwo *= 2;
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return (int)answer;
    }
}
