package com.example.leetcode.medium;

import java.util.Arrays;
import java.util.Stack;

/**Given an array arr of positive integers, consider all binary trees such that:

 Each node has either 0 or 2 children;
 The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
 The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
 Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.



 Example 1:

 Input: arr = [6,2,4]
 Output: 32
 Explanation:
 There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.

 24            24
 /  \          /  \
 12   4        6    8
 /  \               / \
 6    2             2   4


 Constraints:

 2 <= arr.length <= 40
 1 <= arr[i] <= 15
 It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).
 [7,12,8,10]
 *
 */
public class MinimumCostTreeFromLeafValues {
    public static void main(String[] args) {
        int[] arr = new int[]{6,2,4};
        MinimumCostTreeFromLeafValues minimumCostTreeFromLeafValues = new MinimumCostTreeFromLeafValues();
        int result = minimumCostTreeFromLeafValues.mctFromLeafValuesStack(arr);
        System.out.println(result);
    }

    public int mctFromLeafValues(int[] arr) {
        int length = arr.length;
        int[][] map = new int[length][length];
        int[][] max_v = new int[length][length];
        for(int i = 0; i < length ; i ++)
            max_v[i][i] = arr[i];
        for(int i = 1 ; i < length ; i ++)
        {
            for(int j = 0 ; j + i < length ; j++)
            {
                int k = i + j;
                max_v[j][k] = Math.max(max_v[j][k - 1],arr[k]);
            }
        }
        for(int i = 0 ; i < length - 1 ; i ++)
            map[i][i + 1] = arr[i] * arr[i + 1];
        for(int i = 2 ; i < length ; i ++)
        {
            for(int j = 0 ; j + i < length ; j ++)
            {
                int k = j + i;
                int cur_min = Integer.MAX_VALUE;
                for(int l = j ; l < k ; l ++)
                    cur_min = Math.min(cur_min,map[j][l] + map[l + 1][k] + max_v[j][l]*max_v[l + 1][k]);

                map[j][k] = cur_min;
            }
        }

        return map[0][length -1];

    }
    public int mctFromLeafValuesStack(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        stack.add(Integer.MAX_VALUE);
        int temp;
        for(int val: arr){
            while(stack.peek() < val){
                temp = stack.pop();
                result += Math.min(stack.peek(),val) * temp;
            }
            stack.push(val);
        }
        if(stack.size() == 1)
            return result;
        int top = stack.pop();
        while(stack.peek() < Integer.MAX_VALUE){
            temp = stack.pop();
            result += top * temp;
            top = temp;
        }
        return result;
    }

    public int mctFromLeafValuesOld(int[] arr) {
        int result = 0;
        int length = arr.length;
        Arrays.sort(arr);
        int pre = arr[0];

        for(int i = 1; i < length; i++){
            result += pre * arr[i];
            pre = arr[i];
        }

        return result;
    }
}
