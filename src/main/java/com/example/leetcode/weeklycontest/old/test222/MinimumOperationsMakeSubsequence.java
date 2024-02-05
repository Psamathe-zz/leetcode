package com.example.leetcode.weeklycontest.old.test222;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given an array target that consists of distinct integers and another integer array arr that can have duplicates.
 *
 * In one operation, you can insert any integer at any position in arr. For example, if arr = [1,4,1,2], you can add 3 in the middle and make it [1,4,3,1,2]. Note that you can insert the integer at the very beginning or end of the array.
 *
 * Return the minimum number of operations needed to make target a subsequence of arr.
 *
 * A subsequence of an array is a new array generated from the original array by deleting some elements (possibly none) without changing the remaining elements' relative order. For example, [2,7,4] is a subsequence of [4,2,3,7,2,1,4] (the underlined elements), while [2,4,2] is not.
 *
 *
 *
 * Example 1:
 *
 * Input: target = [5,1,3], arr = [9,4,2,3,4]
 * Output: 2
 * Explanation: You can add 5 and 1 in such a way that makes arr = [5,9,4,1,2,3,4], then target will be a subsequence of arr.
 * Example 2:
 *
 * Input: target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= target.length, arr.length <= 105
 * 1 <= target[i], arr[i] <= 109
 * target contains no duplicates.
 */
public class MinimumOperationsMakeSubsequence {
    public static void main(String[] args) {
        int[] target = new int[]{6,4,8,1,3,2};
        int[] arr = new int[]{4,7,6,2,3,8,6,1};
        MinimumOperationsMakeSubsequence minimumOperationsMakeSubsequence = new MinimumOperationsMakeSubsequence();
        minimumOperationsMakeSubsequence.minOperations(target,arr);
    }

    /**
     * https://leetcode-cn.com/problems/minimum-operations-to-make-a-subsequence/solution/de-dao-zi-xu-lie-de-zui-shao-cao-zuo-ci-5qeep/
     * @param target
     * @param arr
     * @return
     */
    public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> map = new HashMap();
        int n = target.length, m = arr.length;
        //步骤1. 映射target的下标
        for(int i = 0; i < n; i++)
            map.put(target[i], i + 1);

        //步骤2. 把arr的数字标记上在target上的下标
        int[] arr2 = new int[m + 1];
        for(int i = 0; i < m; i++){
            if(map.containsKey(arr[i]))
                arr2[i + 1] = map.get(arr[i]); //这里下标从1开始
        }

        //步骤3. 贪心法求最长上升子序列
        List<Integer> list = new ArrayList();
        for(int i = 1; i <= m; i++){
            if(arr2[i] == 0)
                continue;   //如果arr上的数字在target上没出现过
            if(list.isEmpty() || arr2[i] > list.get(list.size() - 1)){
                list.add(arr2[i]);
            }else{
                int l = 0, r = list.size() - 1;
                while(l < r){
                    int mid = l + r >> 1;
                    if(list.get(mid) >= arr2[i]) r = mid;
                    else l = mid + 1;
                }
                list.set(l, arr2[i]);
            }
        }
        return n - list.size();
    }
}
