package com.example.leetcode.challenge.test2022.november;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Map<String, Integer> map1 = null;
        Map<String, Integer> map2 = new HashMap<>();
        Set<String> collect = Stream.concat(map1.keySet().stream(), map2.keySet().stream()).collect(Collectors.toSet());
        System.out.println(collect);
    }
}
