package com.example.leetcode.biweeklycontest.contest23;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Given an integer n. Each number from 1 to n is grouped according to the sum of its digits.
 * Return how many groups have the largest size.
 *
 * Example 1:
 *
 * Input: n = 13
 * Output: 4
 * Explanation: There are 9 groups in total, they are grouped according sum of its digits of numbers from 1 to 13:
 * [1,10], [2,11], [3,12], [4,13], [5], [6], [7], [8], [9]. There are 4 groups with largest size.
 * Example 2:
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are 2 groups [1], [2] of size 1.
 * Example 3:
 *
 * Input: n = 15
 * Output: 6
 * Example 4:
 *
 * Input: n = 24
 * Output: 5
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^4
 */

public class CountLargestGroup {

    public static void main(String[] args) {

        int input = 15;
        CountLargestGroup countLargestGroup = new CountLargestGroup();
        int result = countLargestGroup.countLargestGroup(input);
        System.out.println(result);

    }

    public int countLargestGroup(int n) {
        Map<Integer,Integer> myMap = new HashMap<>();
        IntStream.range(1,n+1)
                .forEach( e-> {
                    if(myMap.containsKey(help(e))){
                        myMap.put(help(e), myMap.get(help(e)) + 1);
                    } else {
                        myMap.put(help(e), 1);
                    }
                });
        int max = myMap.values()
                .stream()
                .max(Comparator.naturalOrder()).orElse(0);
        long result = myMap.entrySet().stream().filter(e-> e.getValue() == max).count();
        return (int)result;

    }

    public int help(int number){
        int temp = number;
        int result = 0;
        do {
            result += temp % 10;
            temp = temp / 10;
        } while (temp  > 0);
        return result;
    }


    /**
     *
     * @param n
     * @return
     *
     * faster solution
     */
    public int countLargestGroupV2(int n) {
        final int N = 100;
        int [] counter = new int [N];
        for(int num = 1; num <= n; ++num) {
            int val = num;
            int cnt = 0;
            while(val != 0) {
                cnt += val % 10;
                val /= 10;
            }
            ++counter[cnt];
        }
        int mx = 0;
        for(int i = 0; i < N; ++i) mx = Math.max(mx, counter[i]);
        int ans = 0;
        for(int i = 0; i < N; ++i) {
            if(counter[i] == mx) ++ans;
        }
        return ans;
    }



}
