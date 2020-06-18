package com.example.leetcode.medium;

/**
 * Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
 *
 * Return the length of the longest (contiguous) subarray that contains only 1s.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * Output: 6
 * Explanation:
 * [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
 * Example 2:
 *
 * Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * Output: 10
 * Explanation:
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 20000
 * 0 <= K <= A.length
 * A[i] is 0 or 1
  */
public class MaxConsecutiveOnesIII {
    public static void main(String[] args) {
        int[] A = new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int K = 3;
        MaxConsecutiveOnesIII maxConsecutiveOnesIII = new MaxConsecutiveOnesIII();
        int result = maxConsecutiveOnesIII.longestOnes(A,K);
        System.out.println(result);
    }

    public int longestOnes(int[] A, int K) {
        int length = A.length;
        int left = 0,right = 0;
        int init = K;
        int result = Integer.MIN_VALUE;
        while (right < length){

            if((init > 0 && A[right] == 0) || A[right] == 1){
                if(A[right] == 0){
                    init--;
                }
                right++;
                result = Math.max(result,right - left);
            } else {
                if(A[left] == 0){
                    init++;
                }
                left++;
            }
        }
        return result;
    }



    /*
        solution 0 (1.2)
        基于solution 1的思路window一旦扩大以后就不会变小 最大的尺寸会保留到最后
        所以zeros <= K时不用更新max max也不再需要
        也就不难理解 https://leetcode.com/problems/max-consecutive-ones-iii/discuss/247564/JavaC%2B%2BPython-Sliding-Window
        post里的代码有点难理解 可以重新写成
        for (j = 0; j < A.length; ++j) {
            if (A[j] == 0)
                K--;
            if (K < 0) {
                if(A[i] == 0)
                    K++;
                i++;
            }
        }
     */
    public int longestOnesV1(int[] A, int K) {
        int n = A.length;
        int l = 0;
        int r = 0;
        int count = 0; //window内1的个数

        while(r < n) {
            count += A[r++];
            if(r - l - count > K) {
                count -= A[l++];
            }
        }
        return r - l;
    }

    /*
        solution 1
        window内0的个数 zeros = r - l - count
        当zeros <= K时 只移动r window不断扩大
        当zeros > K时 l r同时移动window大小不变 zeros只有当移出移入元素不同时才变化 移出0移入1count变大 zeros变小
     */

     public int longestOnesV2(int[] A, int K) {
        int n = A.length;
        int l = 0;
        int r = 0;
        int max = 0;
        int count = 0;

        while(r < n) {
            count += A[r++];
            if(r - l - count <= K) { //0的个数 <= K
                max = Math.max(r - l, max);
            } else {
                count -= A[l++];
            }
        }
         return max;
     }



    /* solution 2
    // literally tranlated from
    // https://leetcode.com/problems/longest-repeating-character-replacement/
    */
    public int longestOnesV3(int[] A, int K) {
        int n = A.length;
        int l = 0;
        int r = 0;
        int max = 0;
        int count = 0;
        int maxCount = 0;

        while(r < n) {
            count += A[r];
            maxCount = Math.max(maxCount, count);
            r++;
            if(r - l - maxCount <= K) {
                max = Math.max(max, r - l);
            } else {
                count -= A[l++];
            }
        }
        return max;
    }
}
