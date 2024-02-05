package com.example.leetcode.challenge.test2023.april.week2;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class SimplifyPath {
    public static void main(String[] args) {

    }

    public String simplifyPath(String path) {
        String[] split = path.split("/");
        List<String> list = Arrays.stream(split).filter(e->!e.isEmpty() && !e.equals(".")).collect(Collectors.toList());
        Deque<String> deque = new LinkedList<>();
        for (String str : list){
            if( str.equals("..") ){
                if(!deque.isEmpty())
                    deque.pollLast();
            } else {
                deque.addLast(str);
            }
        }
        if(deque.isEmpty())
            return "/";
        StringBuilder stringBuilder = new StringBuilder();
        while (!deque.isEmpty()){
            stringBuilder.append("/").append(deque.pollFirst());
        }
        return stringBuilder.toString();
    }
}
