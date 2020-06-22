package com.example.leetcode.easy;

import java.util.*;

/**
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 *
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.
 * Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
 *
 *
 *
 * Example 1:
 *
 * Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * Output: [2,2,2,1,4,3,3,9,6,7,19]
 */
public class RelativeSortArray {
    public static void main(String[] args) {
        int[] arr1 = new int[]{2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = new int[]{2,1,4,3,9,6};
        RelativeSortArray relativeSortArray = new RelativeSortArray();
        int[] result = relativeSortArray.relativeSortArray(arr1,arr2);
        System.out.println(result);
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        List<Integer> canFind = new ArrayList<>();
        List<Integer> resultList = new ArrayList<>();
        Map<Integer, List<Integer>> myMap = new LinkedHashMap<>();
        for(int val : arr2){
            myMap.put(val,new ArrayList<>());
        }

        for(int val : arr1){
            if(myMap.containsKey(val)){
                myMap.get(val).add(val);
            } else {
                canFind.add(val);
            }
        }

        for(List<Integer> list:myMap.values()){
            resultList.addAll(list);
        }
        Collections.sort(canFind);
        resultList.addAll(canFind);
        return resultList.stream().mapToInt(e->e).toArray();
    }


    /**
     * faster solution
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArrayV2(int[] arr1, int[] arr2) {
        int[] holder = new int[1001];
        for(int a : arr1){
            holder[a]++;
        }
        int i = 0;
        for(int a : arr2){
            for(int j = 0; j < holder[a]; j++){
                arr1[i++] = a;
            }
            holder[a] = 0;
        }
        for(int j = 0; j < holder.length; j++){
            if(holder[j] > 0){
                for(int k = 0; k < holder[j]; k++)
                    arr1[i++] = j;
            }
        }
        return arr1;
    }
}
