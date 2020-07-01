package com.example.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array A of integers, a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].  The width of such a ramp is j - i.
 *
 * Find the maximum width of a ramp in A.  If one doesn't exist, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: [6,0,8,2,1,5]
 * Output: 4
 * Explanation:
 * The maximum width ramp is achieved at (i, j) = (1, 5): A[1] = 0 and A[5] = 5.
 * Example 2:
 *
 * Input: [9,8,1,0,1,9,4,0,4,1]
 * Output: 7
 * Explanation:
 * The maximum width ramp is achieved at (i, j) = (2, 9): A[2] = 1 and A[9] = 1.
 *
 *
 * Note:
 *
 * 2 <= A.length <= 50000
 * 0 <= A[i] <= 50000
 *
 */
public class MaximumWidthRamp {
    public static void main(String[] args) {
        int[] A = new int[]{9,8,1,0,1,9,4,0,4,1};
        MaximumWidthRamp maximumWidthRamp = new MaximumWidthRamp();
        maximumWidthRamp.maxWidthRampV2(A);
    }

    public int maxWidthRamp(int[] A) {
        int length = A.length;
        int max = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < length - max; i++) {
            if(set.contains(A[i])){
                continue;
            }
            for (int j = length - 1; j > i ; j--) {
                if(A[j] >= A[i]){
                    max = Math.max(max,j-i);
                    set.add(A[i]);
                    break;
                }
            }
            set.add(A[i]);
        }
        return max;
    }


    /**
     * faster solution
     * @param A
     * @return
     */
    public int maxWidthRampV1(int[] A) {
        int count =0;
        int[] maxIndex= new int[A.length];
        maxIndex[A.length-1] = A[A.length-1];
        for(int i = A.length - 2;i>0;i--) {
            maxIndex[i] = Math.max(A[i],maxIndex[i+1]);
        }
        int start =0;
        int end = 0;
        while(end<A.length) {
            if(start<end&&A[start]>maxIndex[end]) {
                start++;
            }
            count = Math.max(count, end-start);
            end++;
        }
        return count;
    }

    /**
     * https://leetcode-cn.com/problems/maximum-width-ramp/solution/java-dan-diao-zhan-er-fen-jie-fa-chang-shi-jie-shi/
     * 首先把A数组中的以A[0]开头的递减序列抽取出来，我们最后要求的最大的宽度坡一定是以这个序列中的某一个i为坡底的，我们反证一下
     *
     * 假设存在某个元素位置k不存在于上面的递减序列中，且有最大宽度j-k，这也就说明k位置的元素一定是小于k前面所有的元素的，否则就会有更长的宽度，但是既然k小于前面所有的元素，那么k就一定会被加入到序列中，与假设矛盾，所以不存在k，解一定存在递减序列中
     *
     * 这样的话我们可以逆向遍历数组，每次遇到元素大于栈顶的就可以计算宽度，然后将栈顶弹出，因为是逆序遍历的，所以这个宽度一定是栈顶这个坡底i能形成的最大宽度了， 逆序遍历再往前的话即使大于这个栈顶，形成的宽度也只会减小，所以这个栈顶是可以直接pop出去的，我们遍历所有的坡底求最大值就行了，时间复杂度O(N)
     *
     *
     */
    public int maxWidthRampV2(int[] A) {
        Deque<Integer> stack=new ArrayDeque<>();
        int res=0;
        for(int i=0;i<A.length;i++){
            if(stack.isEmpty() || A[stack.peek()]>A[i]){
                stack.push(i);
            }
        }
        for(int i=A.length-1;i>=0;i--){
            while(!stack.isEmpty() && A[stack.peek()]<=A[i]){
                int cur=stack.pop();
                res=Math.max(res,i-cur);
            }
        }
        return res;
    }
}
