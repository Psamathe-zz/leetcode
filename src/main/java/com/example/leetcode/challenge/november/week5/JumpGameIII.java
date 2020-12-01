package com.example.leetcode.challenge.november.week5;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation:
 * All possible ways to reach at index 3 with value 0 are:
 * index 5 -> index 4 -> index 1 -> index 3
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3
 * Example 2:
 *
 * Input: arr = [4,2,3,0,3,1,2], start = 0
 * Output: true
 * Explanation:
 * One possible way to reach at index 3 with value 0 is:
 * index 0 -> index 4 -> index 1 -> index 3
 * Example 3:
 *
 * Input: arr = [3,0,2,1,2], start = 2
 * Output: false
 * Explanation: There is no way to reach at index 1 with value 0.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 5 * 10^4
 * 0 <= arr[i] < arr.length
 * 0 <= start < arr.length
 */
public class JumpGameIII {
    public static void main(String[] args) {
        int[] arr = new int[]{4,2,3,0,3,1,2};
        int k = 5;
        JumpGameIII jumpGameIII = new JumpGameIII();
        boolean res = jumpGameIII.canReach(arr,k);
        System.out.println(res);
    }

    public boolean canReach(int[] arr, int start) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> setTemp = new HashSet<>();
        set.add(start);
        int size;
        int length = arr.length;
        int v1;
        int v2;
        do {
            size = set.size();
            for (Integer val : set){
                v1 = val + arr[val];
                v2 = val - arr[val];
                if(v1 < length) {
                    setTemp.add(v1);
                    if(arr[v1] == 0)
                        return true;
                }
                if(v2 >= 0) {
                    setTemp.add(v2);
                    if(arr[v2] == 0)
                        return true;
                }
            }
            set.addAll(setTemp);
        } while (size != set.size());

        return false;
    }

    /**
     * faster solution
     * @param arr
     * @param start
     * @return
     */
    public boolean canReachV1(int[] arr, int start) {
        if(start>=0 && start<arr.length && arr[start] >=0){
            if(arr[start] == 0)
                return true;
            arr[start] = -arr[start];
            return canReachV1(arr, start+arr[start]) || canReachV1(arr,start-arr[start]);
        }
        return false;
    }
}
