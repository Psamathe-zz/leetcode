package com.example.leetcode.challenge.test2020.april.week4;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstUnique {
    public static void main(String[] args) {
        int[] nums = new int[]{2,3,5};
        FirstUnique firstUnique = new FirstUnique(nums);
        System.out.println(firstUnique.showFirstUnique());
        firstUnique.add(5);
        System.out.println(firstUnique.showFirstUnique());
        firstUnique.add(2);
        System.out.println(firstUnique.showFirstUnique());
        firstUnique.add(3);
        System.out.println(firstUnique.showFirstUnique());
    }

    Map<Integer,Integer> map = new LinkedHashMap<>();

    public FirstUnique(int[] nums) {
        for(int value: nums){
            map.put(value,map.getOrDefault(value,0) + 1);
        }
    }

    public int showFirstUnique() {
        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            if(entry.getValue() == 1)
                return entry.getKey();
        }
        return -1;
    }

    public void add(int value) {
        map.put(value,map.getOrDefault(value,0) + 1);
    }
}
