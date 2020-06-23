package com.example.leetcode.easy;

/**
 * Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.
 *
 * Please note that the string does not contain any non-printable characters.
 *
 * Example:
 *
 * Input: "Hello, my name is John"
 * Output: 5
 */
public class NumberSegmentsInString {
    public static void main(String[] args) {
        String s  = "Hello, my name is John";
        NumberSegmentsInString numberSegmentsInString = new NumberSegmentsInString();
        int result = numberSegmentsInString.countSegments(s);
        System.out.println(result);
    }

    public int countSegments(String s) {
        int count = 0;
        boolean isNew = true;
        for(char c : s.toCharArray()){
            if( c!= ' '){
                if(isNew) {
                    count++;
                    isNew = false;
                }
            } else {
                isNew =true;
            }
        }
        return count;
    }

    public int countSegmentsV1(String s) {

        int count = 0;

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) != ' ' && (i==0 || s.charAt(i-1) == ' '))
                count++;
        }
        return count;

    }
}
