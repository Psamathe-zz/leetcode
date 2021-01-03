package com.example.leetcode.challenge.June.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:
 *
 * Si % Sj = 0 or Sj % Si = 0.
 *
 * If there are multiple solutions, return any subset is fine.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: [1,2] (of course, [1,3] will also be ok)
 * Example 2:
 *
 * Input: [1,2,4,8]
 * Output: [1,2,4,8]
 */
public class LargestDivisibleSubset {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        LargestDivisibleSubset largestDivisibleSubset = new LargestDivisibleSubset();
        List<Integer> list = largestDivisibleSubset.largestDivisibleSubset(nums);
        list.toString();
    }

    /**
     * https://www.hrwhisper.me/leetcode-largest-divisible-subset/
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> ans = new ArrayList<Integer>();
        if (nums.length == 0)
            return ans;
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n], index = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(index, -1);
        int max_index = 0, max_dp = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1 ; j >= 0 ; j--) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    index[i] = j;
                }
            }
            if (max_dp < dp[i]) {
                max_dp = dp[i];
                max_index = i;
            }
        }
        for (int i = max_index; i != -1; i = index[i])
            ans.add(nums[i]);
        return ans;
    }

    /**
     * faster solution
     * https://leetcode.com/submissions/detail/354386042/?from=/explore/challenge/card/june-leetcoding-challenge/540/week-2-june-8th-june-14th/3359/
     */


    int maxV;
    int maxL;
    int cs;
    int next[];
    int len[];
    public List<Integer> largestDivisibleSubsetV1(int[] nums) {

        if(nums.length==0)
            return new ArrayList<>();

        next=new int[nums.length];
        len=new int[nums.length];

        Arrays.sort(nums);
        maxV=nums[nums.length-1];


        for(int j=0;j<nums.length;j++){
            dp(1,j,nums);

        }

        List<Integer>list=new ArrayList<>();
        int i=cs;
        while(i!=-1){
            list.add(nums[i]);
            i=next[i];
        }
        return list;

    }



    private void dp(int cL,int start,int[] nums){

        if(len[start]==0){
            len[start]=1;
            next[start]=-1;
        }
        if(len[start]>maxL){
            maxL=len[start];
            cs=start;
        }



        int limit=maxV>>Math.max(maxL-cL,0);
        int max=0;
        for(int j=start+1;j<nums.length&&nums[j]<=limit;j++){

            if(nums[j]%nums[start]==0){

                if(len[j]==0){
                    dp(cL+1,j,nums);
                }

                if(len[j]>max){
                    max=len[j];
                    next[start]=j;
                    len[start]=len[j]+1;
                    if(len[start]>maxL){
                        maxL=len[start];
                        cs=start;
                        limit=maxV>>Math.max(0,maxL-cL);
                    }


                }


            }


        }



    }
    public List<Integer> largestDivisibleSubset1(int[] nums) {
        if(nums.length == 0)
            return new ArrayList<>();
        Arrays.sort(nums);

        List<Integer>[] dp = new ArrayList[nums.length];
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            List<Integer> possible = new ArrayList<>();
            for(int j=0; j<nums.length; j++){
                if(nums[i] % nums[j] == 0 && dp[j] != null && possible.size() < dp[j].size()){
                    possible = dp[j];
                }
            }
            dp[i] = new ArrayList<>(possible);
            dp[i].add(nums[i]);
            if(result.size() < dp[i].size()){
                result = dp[i];
            }
        }
        return result;
    }

    public List<Integer> largestDivisibleSubsetV2(int[] nums) {
        int n = nums.length, max = 0, last = 0;
        List<Integer> ans = new LinkedList();
        if (n == 0) return ans;
        int[] pre = new int[n], len = new int[n];
        Arrays.sort(nums);
        Arrays.fill(pre, -1);
        Arrays.fill(len, 1);

        for (int i = 0; nums[i] <= nums[n - 1] / 2; i++) {
            for (int j = i + 1, f = 2; nums[n - 1] >= nums[i] * f; f = (nums[j] + nums[i] - 1) / nums[i]) {
                int idx = Arrays.binarySearch(nums, j, n, f * nums[i]);
                if (idx >= 0 && len[i] >= len[idx]) {
                    len[idx] = len[i] + 1;
                    pre[idx] = i;
                    if (max < len[idx]) {
                        max = len[idx];
                        last = idx;
                    }
                }
                j = idx >= 0 ? idx + 1 : -(idx + 1);
                if (j >= n) break;
            }
        }

        while (last != -1) {
            ans.add(0, nums[last]);
            last = pre[last];
        }
        return ans;
    }
}
