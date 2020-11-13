package com.example.leetcode.sometest;

import java.util.HashMap;
import java.util.Map;

public class Algorithm {
    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        int res = algorithm.getPositionAt(4);
        System.out.println(res);
    }

    static Map<Integer,Integer> count = new HashMap<>();
    static int getPositionAt(int n) {
        count.put(0,0);
        count.put(1,1);
        count.put(2,-1);
        return helper(n);
    }

    static int helper(int n){
        if(count.containsKey(n))
            return count.get(n);
        else {
            int val = (2 * (helper(n - 1) - helper(n - 2)));
            count.put(n,val);
            return val;
        }
    }
}
