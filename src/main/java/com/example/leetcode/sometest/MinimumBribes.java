package com.example.leetcode.sometest;

import java.util.List;

public class MinimumBribes {
    public static void main(String[] args) {

    }

    public void minimumBribes(List<Integer> q) {
        // Write your code here
        int length = q.size();
        int count = 0;
        for(int i= length - 1; i >= 0; i--){
            int expectedVal = i+1;
            if(q.get(i) != expectedVal){
                if(q.get(i - 1) == expectedVal){
                    count++;
                    swap(q, i, i-1);
                }else if (q.get(i - 2) == expectedVal){
                    count+=2;
                    swap(q, i-2, i-1);
                    swap(q, i-1, i);
                }else {
                    return;
                }
            }
        }
    }
    public void swap(List<Integer> q,int index1,int index2) {
        int temp = q.get(index1);
        q.set(index1, q.get(index2));
        q.set(index2, temp);
    }
}
