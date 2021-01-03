package com.example.leetcode.challenge.may.week1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 * Example 1:
 *
 * Input: [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 */
public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,3};
        MajorityElement majorityElement = new MajorityElement();
        int result = majorityElement.majorityElementV3(nums);
        System.out.println(result);
    }

    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();

        for(int value : nums){
            int times = map.getOrDefault(value,0) + 1;
            if(times > nums.length / 2)
                return value;
            else
                map.put(value,times);
        }
        return -1;
    }


    /**
     * faster solution
     * @param nums
     * @return
     */
    public int majorityElementV2(int[] nums) {
        int leader=nums[0],leader_count=1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]==leader) leader_count++;
            else{
                leader_count--;
                if(leader_count==0){
                    if(i<nums.length-1){
                        leader=nums[i+1];
                        leader_count=1;
                        i++;
                    }
                }
            }
        }
        return leader;

    }


    /**
     * less memory
     * @param nums
     * @return
     */
    public int majorityElementV3(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i]))
                map.put(nums[i], map.get(nums[i]) + 1);
            else
                map.put(nums[i],1);
        }
        //System.out.println(map);
        Map<Integer, Integer> map1 = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e2, e1) -> e2, LinkedHashMap::new));

        //System.out.println(map1);
        Iterator itr = map1.entrySet().iterator();
        Map.Entry<Integer,Integer> xx=null;
        while(itr.hasNext()){
            xx = ( Map.Entry<Integer,Integer>)itr.next();
        }
        int key = xx.getKey();
        int value = xx.getValue();
        //System.out.println(key + " " + value);
        if(value>nums.length/2)
            return key;
        else
            return 0;
    }
}
