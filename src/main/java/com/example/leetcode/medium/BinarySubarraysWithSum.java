package com.example.leetcode.medium;

/**
 * In an array A of 0s and 1s, how many non-empty subarrays have sum S?
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1,0,1,0,1], S = 2
 * Output: 4
 * Explanation:
 * The 4 subarrays are bolded below:
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 *
 *
 * Note:
 *
 * A.length <= 30000
 * 0 <= S <= A.length
 * A[i] is either 0 or 1.
 *
 [0,0,0,0,0]
 0
 */
public class BinarySubarraysWithSum {
    public static void main(String[] args) {
        int[] A = new int[]{1,0,1,0,1};
        int S = 2;
        BinarySubarraysWithSum binarySubarraysWithSum = new BinarySubarraysWithSum();
        binarySubarraysWithSum.numSubarraysWithSumV1(A,S);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/12245317.html
     * @param A
     * @param S
     * @return
     */
    public int numSubarraysWithSum(int[] A, int S) {
        int left = 0;
        int result = 0;
        int sum = 0;
        for (int right = 0; right < A.length; right++) {
            sum = sum + A[right];
            while(sum > S && left < right) {
                sum = sum - A[left];
                left++;
            }
            if (sum == S) {
                result++;
                for (int mid = left; mid < right && A[mid] ==0; mid++) {
                    result++;
                }
            }
        }
        return result;
    }


    /**
     * faster solution
     * @param nums
     * @param S
     * @return
     */
    public int numSubarraysWithSumV1(int[] nums, int S) {
        return atMost(nums, S) - atMost(nums, S-1);
    }
    // 0 0 1 1 0 1
    public int atMost(int[] nums, int k) {
        if (k < 0) return 0;
        int res = 0, i = 0, j = 0;
        int sum = 0;
        while (j < nums.length) {
            sum += nums[j];
            while (sum > k) {
                sum -= nums[i++];
            }
            res += j-i+1;
            j++;
        }

        return res;
    }
}
