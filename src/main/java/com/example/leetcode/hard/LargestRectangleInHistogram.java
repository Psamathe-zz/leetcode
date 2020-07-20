package com.example.leetcode.hard;

import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 *
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 *
 * Example:
 *
 * Input: [2,1,5,6,2,3]
 * Output: 10
 */
public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] heights = new int[]{2,3};
        LargestRectangleInHistogram largestRectangleInHistogram = new LargestRectangleInHistogram();
        largestRectangleInHistogram.largestRectangleAreaV0(heights);
    }

    public int largestRectangleArea(int[] heights) {
        int length = heights.length;
        int res = 0;
        int temp;
        int height;
        for (int i = 0; i < length; i++) {
            height = heights[i];
            temp = 0;
            for (int j = 0; j < length; j++) {
                if(heights[j] < height) {
                    if(j < i) {
                        temp = 0;
                    } else {
                        break;
                    }
                } else
                    temp++;
            }
            res = Math.max(res,temp * height);
        }
        return res;
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4322653.html
     */

    public int largestRectangleAreaV0(int[] heights) {
        int res = 0;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < heights.length; ++i) {
            while (!st.empty() && heights[st.peek()] >= heights[i]) {
                int cur = st.pop();
                res = Math.max(res, heights[cur] * (st.empty() ? i : (i - st.peek() - 1)));
            }
            st.push(i);
        }
        while (!st.empty()){
            int cur = st.pop();
            res = Math.max(res, heights[cur] * (st.empty() ? heights.length : (heights.length - st.peek() - 1)));
        }
        return res;
    }
    /**
     * faster solution
     * https://leetcode.com/submissions/detail/369128906/
     * @param heights
     * @return
     */
    public int largestRectangleAreaV1(int[] heights) {
        if(heights.length==0) return 0;
        int len = heights.length;
        int left[] = new int[len];
        int right[] = new int[len];
        left[0] = 0;
        right[len-1] = len - 1;
        for(int i=1;i<len;i++) {
            if(heights[i]<=heights[i-1]) {
                left[i] = left[i-1];
                while(left[i]>=0&&heights[left[i]]>=heights[i]){
                    left[i]--;
                }
                left[i]++;
            }else {
                left[i] = i;
            }
        }
        for(int i = len-2;i>=0;i--) {
            if(heights[i]<=heights[i+1]) {
                right[i] = right[i+1];
                while(right[i]<=len-1&&heights[right[i]]>=heights[i]){
                    right[i]++;
                }
                right[i]--;
            }else {
                right[i] = i;
            }
        }
        int max = 0;
        for(int i=0;i<heights.length;i++) {
            max = Math.max(max, (right[i]-left[i]+1)*heights[i]);
        }
        return max;
    }
}
