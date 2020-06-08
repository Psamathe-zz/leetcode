package com.example.leetcode.challenge.June;

import java.util.Arrays;
import java.util.Random;

/**
 * Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.
 *
 * Note:
 *
 * 1 <= w.length <= 10000
 * 1 <= w[i] <= 10^5
 * pickIndex will be called at most 10000 times.
 * Example 1:
 *
 * Input:
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output: [null,0]
 * Example 2:
 *
 * Input:
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output: [null,0,1,1,1,0]
 * Explanation of Input Syntax:
 *
 * The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.
 */
public class RandomPickWithWeight {
    public static void main(String[] args) {

    }

    /**
     * https://www.jianshu.com/p/86f672fc2555
     * https://www.cnblogs.com/grandyang/p/9784690.html
     */
    int[] weight;
    Random rand = new Random();
    public RandomPickWithWeight(int[] w) {
        weight = w;
        for (int i = 1; i < w.length; ++i) {
            weight[i] = weight[i - 1] + w[i];
        }
    }

    public int pickIndex() {
        int left = 0, right = weight.length;
        int last = weight[weight.length - 1];
        int target = rand.nextInt(last) + 1;

        // We want to find the index into cumsum array, such that target falls into

        while(left < right) {
            int mid = (left + right) / 2;
            int mid_val = weight[mid];

            if(mid_val >= target) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * faster solution
     */

    Random r;  //from [0,n-1]
    int[] buckets;

    public void Solution(int[] w)
    {
        int wsum=0;
        buckets=new int[w.length];
        for(int i=0;i<buckets.length;i++)
        {
            wsum+=w[i];
            buckets[i]=wsum-1;   //important
        }
        r=new Random();
    }
    public int pickIndexV1()
    {
        int sum=r.nextInt(buckets[buckets.length-1]+1);
        int tmp=Arrays.binarySearch(buckets, sum);

        if(tmp<0)
            tmp=-tmp-1;

        return tmp;

    }

}
