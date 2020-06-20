package com.example.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

public class ElementAppearingMoreThan25 {
    public static void main(String[] args) {

    }

    public int findSpecialInteger(int[] arr) {
        int length = arr.length;
        int temp;
        int result = arr[0];
        int max = Integer.MIN_VALUE;
        Map<Integer,Integer> count = new HashMap<>();
        for (int i = 0; i < length; i++) {
            temp = count.getOrDefault(arr[i],0) + 1;
            count.put(arr[i],temp);
            if( max < temp){
                max = temp;
                result = arr[i];
            }
            if(max > (double)length/4){
                break;
            }
        }
        return  result;
    }

    /**
     * faster  solution
     * @param arr
     * @return
     */
    public int findSpecialIntegerV1(int[] arr) {

        int neededOccurr = arr.length / 4;
        int tmpNumber = -1;
        int counter = 0;

        for(int number: arr){

            if(tmpNumber == number){
                counter++;
            } else {
                counter = 0;
                tmpNumber = number;
            }

            if(counter >= neededOccurr)
                return number;
        }


        return -1;
    }
}
