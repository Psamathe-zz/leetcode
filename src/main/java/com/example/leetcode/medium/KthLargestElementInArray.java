package com.example.leetcode.medium;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 */
public class KthLargestElementInArray {

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
        int k = 4;
        KthLargestElementInArray kthLargestElementInArray = new KthLargestElementInArray();
        int result = kthLargestElementInArray.findKthLargest(nums,k);
        System.out.println(result);
    }

    public int findKthLargest(int[] nums, int k) {
        List<Integer> list = Arrays.stream(nums).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        int i;
        for(i = 0; i < list.size();i++ ){
            k--;
            if(k==0)
                break;
        }
        return k==0?list.get(i):-1;
    }

    /**
     * faster solution
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestV2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        quickSort(nums, 0, nums.length - 1, nums.length - k + 1);
        return nums[nums.length - k];
    }
    private void quickSort(int[] nums, int start, int end, int nth) {
        if (start >= end) {
            return;
        }
        int i = start;
        int j = end;
        int target = nums[start + (end - start) / 2];
        while (i <= j) {
            while (i <= j && nums[i] < target) {
                i++;
            }
            while (i <= j && nums[j] > target) {
                j--;
            }
            if (i <= j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }
        if (j - start + 1 >= nth) {
            quickSort(nums, start, j, nth);
        } else if (i - start + 1 <= nth) {
            quickSort(nums, i, end, nth - (i - start));
        } else {
            return;
        }
    }


    /**
     * faster solution
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestV3(int[] nums, int k) {
        return helper(nums, nums.length - k + 1, 0, nums.length - 1);
    }

    private int helper(int[] nums, int k, int start, int end) {
        int mid = (start + end)/2;
        int pivot = nums[mid];

        // 1. move pivot to end
        swap(nums, mid, end);
        int index = start;

        // 2. move all smaller elements to the left
        for(int i = start; i<=end;i++) {
            if(nums[i] < pivot) {
                swap(nums, index, i);
                index++;
            }
        }
        // 3. move pivot to its final place
        swap(nums, index, end);


        if (index - start + 1 == k) {
            return pivot;
        } else if (index - start + 1 > k) {
            return helper(nums, k, start, index  - 1);
        } else {
            return helper(nums, (k - (index - start + 1)), index + 1, end);
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i!=j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    /**
     * less memory
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestV4(int[] nums, int k) {


        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);

        for(int number : nums){
            heap.add(number);

            if(heap.size() > k){
                heap.poll();
            }

        }

        return heap.peek();
    }
}
