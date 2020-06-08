package com.example.leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a string s. Return all the words vertically in the same order in which they appear in s.
 * Words are returned as a list of strings, complete with spaces when is necessary. (Trailing spaces are not allowed).
 * Each word would be put on only one column and that in one column there will be only one word.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "HOW ARE YOU"
 * Output: ["HAY","ORO","WEU"]
 * Explanation: Each word is printed vertically.
 *  "HAY"
 *  "ORO"
 *  "WEU"
 * Example 2:
 *
 * Input: s = "TO BE OR NOT TO BE"
 * Output: ["TBONTB","OEROOE","   T"]
 * Explanation: Trailing spaces is not allowed.
 * "TBONTB"
 * "OEROOE"
 * "   T"
 * Example 3:
 *
 * Input: s = "CONTEST IS COMING"
 * Output: ["CIC","OSO","N M","T I","E N","S G","T"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 200
 * s contains only upper case English letters.
 * It's guaranteed that there is only one space between 2 words.
 */
public class PrintWordsVertically {
    public static void main(String[] args) {
        String s = "CONTEST IS COMING";
        PrintWordsVertically printWordsVertically = new PrintWordsVertically();
        List<String> result = printWordsVertically.printVertically(s);
        for(String str:result){
            System.out.println(str);
            System.out.println(str.length());
        }
        System.out.println(1);
    }

    public List<String> printVertically(String s) {
        String[] strings = s.split(" ");
        int maxLength = 0;
        List<StringBuffer> result = new ArrayList<>();
        int index = 0;
        int length;
        for(String str : strings){
            for(int i = 0; i < str.length();i++){
                if(i < result.size()){
                    length = result.get(i).length();
                    for (int j = length; j < index; j++) {
                        result.get(i).append(" ");
                    }
                    result.get(i).append(str.charAt(i));
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int j = 0; j < index; j++) {
                        stringBuffer.append(" ");
                    }
                    stringBuffer.append(str.charAt(i));
                    result.add(stringBuffer);
                }

                if(maxLength < str.length())
                    maxLength = str.length();
            }
            index++;
        }
        return result.stream().map( e->e.toString()).collect(Collectors.toList());
    }

    /**
     * faster solution
     * @param s
     * @return
     */
    public List<String> printVerticallyV1(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        String[] strs = s.split(" ");
        int max = 0;
        for (String str: strs) {
            max = Math.max(max, str.length());
        }
        StringBuilder[] sbs = new StringBuilder[max];
        for (String str: strs) {
            for (int i = 0; i < max; i++) {
                if (sbs[i] == null) sbs[i] = new StringBuilder();
                char ch = i >= str.length()?' ':str.charAt(i);
                sbs[i].append(ch);
            }
        }
        for (StringBuilder sb: sbs) {

            while (sb.charAt(sb.length()-1) == ' ') sb.deleteCharAt(sb.length()-1);
            res.add(sb.toString());
        }
        return res;
    }
}
