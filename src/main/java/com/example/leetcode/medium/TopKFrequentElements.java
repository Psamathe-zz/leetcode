package com.example.leetcode.medium;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 * Note:
 *
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
 * You can return the answer in any order.
 */
public class TopKFrequentElements {
    public static void main(String[] args) {

    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> count = new HashMap<>();
        for(int value : nums){
            count.put(value, count.getOrDefault(value,0) + 1);
        }
        return count.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).map(e -> e.getKey()).limit(k).mapToInt(i->i).toArray();

    }

    /**
     * faster solution
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequentV1(int[] nums, int k) {
        if (nums == null || nums.length <= k) {
            return nums;
        }

        Arrays.sort(nums);

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        int left = 0, right = 1;

        while (right < nums.length) {
            if (nums[right] != nums[right - 1]) {
                pq.add(new int[] {nums[left], right - left});
                left = right;
            }
            right++;
        }
        pq.add(new int[] {nums[left], right - left});

        int[] result = new int[k];
        int count = 0;

        while (count < k) {
            result[count++] = pq.poll()[0];
        }

        return result;
    }


    public int[] topKFrequentV2(int[] nums, int k) {
        Arrays.sort(nums);
        Map<Integer, List<Integer>> map = new HashMap();
        int max = Integer.MIN_VALUE;
        for(int i = 0; i<nums.length; i++){
            int j = i;
            int count = 0;
            while(j < nums.length && nums[i] == nums[j]){
                count++;
                j++;
            }
            if(map.containsKey(count)){
                map.get(count).add(nums[i]);
            }else{
                map.put(count, new ArrayList<Integer>(Arrays.asList(nums[i])));
            }
            max = Math.max(max, count);
            i = j - 1;
        }

        int[] answer = new int[k];
        int i = 0;
        while(i < k && max >= 0){
            if(map.containsKey(max)){
                List<Integer> list = map.get(max);
                for(int num : list){
                    answer[i] = num;
                    i++;
                }
            }
            max--;
        }
        return answer;
    }
}
