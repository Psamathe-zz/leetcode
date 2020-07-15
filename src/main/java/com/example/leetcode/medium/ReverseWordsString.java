package com.example.leetcode.medium;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an input string, reverse the string word by word.
 *
 *
 *
 * Example 1:
 *
 * Input: "Toeplitz Matrixthe sky is blue"
 * Output: "blue is sky the"
 * Example 2:
 *
 * Input: "  hello world!  "
 * Output: "world! hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * Example 3:
 *
 * Input: "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 *
 * Note:
 *
 * A word is defined as a sequence of non-space characters.
 * Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
 * You need to reduce multiple spaces between two words to a single space in the reversed string.
 */
public class ReverseWordsString {

    public static void main(String[] args) {
        String s = "  hello world!  ";
        ReverseWordsString reverseWordsString = new ReverseWordsString();
        String result = reverseWordsString.reverseWords(s);
        System.out.println(result);
    }

    public String reverseWords(String s) {
        String[] list = s.split(" ");
        List<String> tempList = Arrays.stream(list).filter(e->!e.isEmpty()).collect(Collectors.toList());
        StringBuffer stringBuffer = new StringBuffer();
        int i = 1;
        for(String s1 : tempList){
            stringBuffer.insert(0,i!=tempList.size()?" "+s1:s1);
            i++;
        }
        return stringBuffer.toString();
    }

    /**
     * faster solution
     * @param s
     * @return
     */
    public String reverseWordsV2(String s) {
        if(s.length()==0)
            return "";
        StringBuilder sb=new StringBuilder();
        int r=s.length()-1;
        int l=0;
        while(r>=0){
            if(s.charAt(r)!=' '){
                l=s.lastIndexOf(' ',r);
                sb.append(s,l+1,r+1).append(' ');
                r=l;
            }
            else
                r--;
        }
        int si=sb.length();
        return si>0?sb.deleteCharAt(si-1).toString():"";
    }
}
