package com.example.leetcode.sometest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leftrotate {

    public static void main(String[] args) {
        Leftrotate leftrotate = new Leftrotate();
        Integer[] ars = new Integer[]{41,73,89,7,10,1,59,58,84,77,77,97,58,1,86,58,26,10,86,51};
        leftrotate.rotLeft(new ArrayList<>(Arrays.asList(ars)),10);
    }
    public List<Integer> rotLeft(List<Integer> a, int d) {
        // Write your code here
        int length = a.size();
        Integer temp = null;
        int count = 0;
        d = d % length;
        Integer pre = null;
        int index = 0;
        int value;
        int next;
        while(count < length) {
            value = a.get(index);
            if(temp == null)
                a.set(index, a.get((index + d) % length));
            else
                a.set(index, temp);
            temp = value;
            next = (index - d + length) % length;
            if(pre != null && next == pre) {
                pre = index;
                index = next + 1;
                temp = null;
            } else {
                pre = index;
                index = next;
            }
            count++;
        }
        return a;

    }
}
