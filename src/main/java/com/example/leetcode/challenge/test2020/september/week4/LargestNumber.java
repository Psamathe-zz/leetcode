package com.example.leetcode.challenge.test2020.september.week4;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 *
 * Example 1:
 *
 * Input: [10,2]
 * Output: "210"
 * Example 2:
 *
 * Input: [3,30,34,5,9]
 * Output: "9534330"
 * Note: The result may be very large, so you need to return a string instead of an integer.
 *
 * 34
 * 341
 */
public class LargestNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{0,0};
        LargestNumber largestNumber = new LargestNumber();
        largestNumber.largestNumber(nums);
    }

    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int val:nums){
            list.add(String.valueOf(val));
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2+o1).compareTo(o1+o2);
            }
        });
        String res =  list.stream().collect(Collectors.joining());
        if(res.charAt(0) == '0')
            res = "0";
        return res;
    }


    /**
     * faster solution
     * @param nums
     * @return
     */
    public String largestNumberV1(int[] nums) {
        if(nums.length == 0 || nums == null){
            return null;
        }
        String[] arr = new String[nums.length];
        for(int i = 0; i < nums.length; i++){
            arr[i] = String.valueOf(nums[i]);
        }
        Comparator<String> comp = new Comparator<String>(){
            public int compare(String str1, String str2){
                String s1 = str1 + str2;
                String s2 = str2 + str1;
                return s2.compareTo(s1);
            }
        };
        Arrays.sort(arr, comp);
        if(arr[0].equals("0")){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for(String str: arr){
            sb.append(str);
        }
        return sb.toString();
    }
}
