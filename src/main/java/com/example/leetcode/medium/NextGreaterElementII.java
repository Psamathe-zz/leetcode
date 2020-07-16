package com.example.leetcode.medium;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.
 *
 * Example 1:
 * Input: [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number;
 * The second 1's next greater number needs to search circularly, which is also 2.
 * Note: The length of given array won't exceed 10000.
 */
public class NextGreaterElementII {
    public static void main(String[] args) {
        int nums[] = new int[]{10,5,4,11};
        NextGreaterElementII nextGreaterElementII = new NextGreaterElementII();
        nextGreaterElementII.nextGreaterElements(nums);
    }
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] circle = new int[n*2];
        Stack<Integer> st = new Stack();
        System.arraycopy(nums, 0, circle, 0, n);
        System.arraycopy(nums, 0, circle, n, n);
        Arrays.fill(nums, -1);

        for (int i=0; i<n*2; ) {
            if (!st.isEmpty() && circle[i] > circle[st.peek()]) {
                int top = st.pop();
                if (top < n) {
                    nums[top] = circle[i];
                }
            } else {
                st.push(i++);
            }
        }
        return nums;
    }
    public int[] nextGreaterElementsOld(int[] nums) {
        int length = nums.length;
        int[] newNums = new int[length * 2];
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            newNums[i] = nums[i];
            newNums[i + length] = nums[i];
        }
        int t;
        for (int i = 0; i < length; i++) {
            t = -1;
            for (int j = i + 1; j < length * 2; j++) {
                if(newNums[j] > newNums[i]){
                    t = newNums[j];
                    break;
                }
            }
            res[i] = t;
        }
        return res;
    }
}
