package com.example.leetcode.java.java16;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {

        //1时段支持
        //
        //
        //DateTimeFormatter的新增功能，可以表示一天中的时段，例如“上午”、“下午”
        String date1 = DateTimeFormatter.ofPattern("a").format(LocalTime.now());
        String date2 = DateTimeFormatter.ofPattern("B").format(LocalTime.now());
        String date3 = DateTimeFormatter.ofPattern("k").format(LocalTime.now());
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);

        //2 添加Stream.toList方法
        List<String> integersAsString = Arrays.asList("1", "2", "3");
        //之前这样写
        List<Integer> ints = integersAsString.stream().map(Integer::parseInt).collect(Collectors.toList());
        //现在可以这样写
        List<Integer> intsEquivalent = integersAsString.stream().map(Integer::parseInt).toList();
    }
}
