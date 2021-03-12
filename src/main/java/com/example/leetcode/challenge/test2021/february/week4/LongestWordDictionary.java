package com.example.leetcode.challenge.test2021.february.week4;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.
 *
 * Example 1:
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 *
 * Output:
 * "apple"
 * Example 2:
 * Input:
 * s = "abpcplea", d = ["a","b","c"]
 *
 * Output:
 * "a"
 * Note:
 * All the strings in the input will only contain lower-case letters.
 * The size of the dictionary won't exceed 1,000.
 * The length of all the strings in the input won't exceed 1,000.
 */
public class LongestWordDictionary {
    public static void main(String[] args) {
        LongestWordDictionary longestWordDictionary = new LongestWordDictionary();
        List<String> d = new ArrayList<>();
        d.add("a");
        d.add("b");
        d.add("c");
        String res = longestWordDictionary.findLongestWord("abpcplea",d);
        System.out.println(res);
    }

    public String findLongestWord(String s, List<String> d) {
        StringBuilder stringBuilder = new StringBuilder() ;
        Collections.sort(d);
        for (String str : d){
            if(str.length() > stringBuilder.length() && helper(s,str)){
                stringBuilder.setLength(0);
                stringBuilder.append(str);
            }
        }
        return stringBuilder.toString();
    }

    public boolean helper(String s, String word){
        int index1 = 0;
        int index2 = 0;
        while (index1 < s.length() && index2 < word.length()){
            if(s.charAt(index1) == word.charAt(index2))
                index2++;
            if(index2 == word.length())
                return true;
            index1++;
        }
        return false;
    }


    /**
     * faster solution
     * @param s
     * @param d
     * @return
     */
    public String findLongestWordV0(String s, List<String> d) {

        String res="";
        for (String c: d)
            if ((c.length() > res.length() || c.length()==res.length() && c.compareTo(res)<0) && isSubseq(c, s)) res=c;
        return res;
    }
    public boolean isSubseq(String a, String b){
        int j = -1;
        for(int i = 0; i < a.length(); i++){
            j = b.indexOf(a.charAt(i), j + 1);
            if(j == -1) {
                return false;
            }
        }
        return true;
    }
}
