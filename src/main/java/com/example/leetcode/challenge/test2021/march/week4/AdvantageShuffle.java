package com.example.leetcode.challenge.test2021.march.week4;


import com.example.leetcode.sometest.A;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].
 *
 * Return any permutation of A that maximizes its advantage with respect to B.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [2,7,11,15], B = [1,10,4,11]
 * Output: [2,11,7,15]
 * Example 2:
 *
 * Input: A = [12,24,8,32], B = [13,25,32,11]
 * Output: [24,32,8,12]
 *
 *
 * Note:
 *
 * 1 <= A.length = B.length <= 10000
 * 0 <= A[i] <= 10^9
 * 0 <= B[i] <= 10^9
 */
public class AdvantageShuffle {
    public static void main(String[] args) {
        AdvantageShuffle advantageShuffle = new AdvantageShuffle();
        advantageShuffle.advantageCount(new int[]{2,7,11,15},new int[]{1,10,4,11});
    }

    public int[] advantageCount(int[] a, int[] b) {
        int n = b.length;
        Arrays.sort(a);
        int[][] pair = new int[n][2];
        for (int i = 0; i < n; i ++)
            pair[i] = new int[]{b[i], i};//把下标保存下来
        Arrays.sort(pair, (x, y)->x[0] - y[0]);

        int[] res = new int[n];
        for (int i = 0, r = n - 1, l = 0; i < n; i ++)//r最大值，l标明最小值；
        {
            if (a[i] > pair[l][0])
                res[pair[l ++][1]] = a[i];////要放到原数组对应的位置上
            else
                res[pair[r --][1]] = a[i];//要放到原数组对应的位置上

        }
        return res;
    }
    void quicksort(int[] a,int left,int right) {
        int i,j,t,temp;
        if(left>right)
            return;

        temp=a[left]; //temp中存的就是基准数
        i=left;
        j=right;
        while(i!=j) {
            //顺序很重要，要先从右边开始找
            while(a[j]>=temp && i<j)
                j--;
            //再找右边的
            while(a[i]<=temp && i<j)
                i++;
            //交换两个数在数组中的位置
            if(i<j)
            {
                t=a[i];
                a[i]=a[j];
                a[j]=t;
            }
        }
        //最终将基准数归位
        a[left]=a[i];
        a[i]=temp;

        quicksort(a,left,i-1);//继续处理左边的，这里是一个递归的过程
        quicksort(a,i+1,right);//继续处理右边的 ，这里是一个递归的过程
    }

}
