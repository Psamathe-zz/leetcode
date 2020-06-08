package com.example.leetcode.hard;

import java.util.*;

/**
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example:
 *
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 */
public class CountSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        int[] nums = new int[]{5,2,6,1};
        CountSmallerNumbersAfterSelf countSmallerNumbersAfterSelf = new CountSmallerNumbersAfterSelf();
        List<Integer> result = countSmallerNumbersAfterSelf.countSmallerV2(nums);
        System.out.println(result);
    }

    public List<Integer> countSmaller(int[] nums) {
        int temp;
        List<Integer> result = new LinkedList<>();
        for (int i = nums.length - 1; i >= 0 ; i--) {
            temp = 0;
            for (int j = nums.length - 1; j >i ; j--) {
                if(nums[j] < nums[i])
                    temp++;
            }
            result.add(0,temp);
        }
        return new ArrayList<>(result);
    }

    /**
     * faster solution
     *
     */

    class Node{
        int val, leftSum = 0, count = 0;
        Node left, right;
        public Node(int val){
            this.val = val;
        }
    }
    public List<Integer> countSmallerV1(int[] nums) {
        Integer[] count = new Integer[nums.length];
        if(nums.length == 0){
            return Arrays.asList(count);
        }
        Node root = new Node(nums[nums.length - 1]);
        for(int i = nums.length - 1; i >= 0; i--){
            count[i] = insert(root, nums[i]);
        }
        return Arrays.asList(count);
    }
    private int insert(Node node, int num){
        int sum = 0;
        while(node.val != num){
            if(node.val > num){
                if(node.left == null)
                    node.left = new Node(num);
                node.leftSum++;
                node = node.left;
            }else{
                sum += node.leftSum + node.count;
                if(node.right == null)
                    node.right = new Node(num);
                node = node.right;
            }
        }
        node.count++;
        return sum + node.leftSum;
    }


    /**
     * faster solution
     * @param nums
     * @return
     */
    public List<Integer> countSmallerV2(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return Collections.emptyList();
        int[] count = new int[n];
        int[] indexes = new int[n];
        int[] buffer = new int[n];
        for (int i = 0; i < n; ++ i)
            indexes[i] = i;
        mergeSort(nums, indexes, buffer, 0, n, count);
        List<Integer> result = new ArrayList<>();
        for (int c : count)
            result.add(c);
        return result;
    }

    void mergeSort(int[] nums, int[] indexes, int[] buffer, int l, int r, int[] count) {
        if (l + 1 == r) {
            return;
        }
        int mid = (l + r) / 2;
        mergeSort(nums, indexes, buffer, l, mid, count);
        mergeSort(nums, indexes, buffer, mid, r, count);
        int idx = l;
        for (int i = l, j = mid; i < mid || j < r; ) {
            if (i == mid || (j < r && nums[indexes[i]] > nums[indexes[j]])) {
                buffer[idx ++] = indexes[j ++];
            } else {
                count[indexes[i]] += j - mid;
                buffer[idx ++] = indexes[i ++];
            }
        }
        System.arraycopy(buffer, l, indexes, l, r - l);
    }
}
