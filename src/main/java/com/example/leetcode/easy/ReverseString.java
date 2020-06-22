package com.example.leetcode.easy;

public class ReverseString {
    public static void main(String[] args) {
        char[] s = new char[]{'h','e','l','l','o'};
        ReverseString reverseString = new ReverseString();
        reverseString.reverseString(s);
        System.out.println(s);
    }
    public void reverseString(char[] s) {
        String ss = String.valueOf(s);
        for(int i = 0; i< s.length;i++){
            s[i] = ss.charAt(s.length - 1 - i);
        }
    }
}
