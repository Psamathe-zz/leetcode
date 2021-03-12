package com.example.leetcode.challenge.test2021.february.week3;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
 *
 * Return a list of all possible strings we could create. You can return the output in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: S = "a1b2"
 * Output: ["a1b2","a1B2","A1b2","A1B2"]
 * Example 2:
 *
 * Input: S = "3z4"
 * Output: ["3z4","3Z4"]
 * Example 3:
 *
 * Input: S = "12345"
 * Output: ["12345"]
 * Example 4:
 *
 * Input: S = "0"
 * Output: ["0"]
 *
 *
 * Constraints:
 *
 * S will be a string with length between 1 and 12.
 * S will consist only of letters or digits.
 */
public class LetterCasePermutation {
    public static void main(String[] args) {
        LetterCasePermutation letterCasePermutation = new LetterCasePermutation();
        List<String> res = letterCasePermutation.letterCasePermutation("a1b2");
        System.out.println(res.size());
    }


    public List<String> letterCasePermutation(String S) {
        if(S.length() == 1){
            List<String> res = new ArrayList<>();
            res.add(S);
            if(S.charAt(0) <= 'Z' && S.charAt(0) >= 'A' ){
                char c = (char)( S.charAt(0) + 32);
                res.add(String.valueOf(c));
            }
            if(S.charAt(0) <= 'z' && S.charAt(0) >= 'a' ){
                char c = (char)( S.charAt(0) - 32);
                res.add(String.valueOf(c));
            }
            return res;
        } else {
            List<String> pre = letterCasePermutation(S.substring(0,S.length()-1));
            List<String> res = new ArrayList<>();
            StringBuilder sb;
            for (String str : pre){
                sb = new StringBuilder(str);
                sb.append(S.charAt(S.length() - 1));
                res.add(sb.toString());
                if(S.charAt(S.length() - 1) <= 'Z' && S.charAt(S.length() - 1) >= 'A' ){
                    sb.deleteCharAt(S.length() - 1);
                    char c = (char)( S.charAt(S.length() - 1) + 32);
                    sb.append(c);
                    res.add(sb.toString());
                }
                if(S.charAt(S.length() - 1) <= 'z' && S.charAt(S.length() - 1) >= 'a' ){
                    sb.deleteCharAt(S.length() - 1);
                    char c = (char)( S.charAt(S.length() - 1) - 32);
                    sb.append(c);
                    res.add(sb.toString());
                }
            }
            return res;
        }
    }

    /**
     * faster solution
     * @param S
     * @return
     */
    public List<String> letterCasePermutationV0(String S) {
        List<String> list = new LinkedList<>();
        permHelper( S.toCharArray(), 0, list);
        return list;
    }

    private void permHelper( char[] strArr, int pos, List<String> list ){
        if( pos == strArr.length ){
            list.add(new String(strArr));
            return;
        }

        if( strArr[pos] >='0' && strArr[pos] <= '9' ){
            permHelper( strArr, pos + 1, list);
            return;
        }

        strArr[pos] = Character.toUpperCase(strArr[pos]);
        permHelper( strArr, pos + 1, list);

        strArr[pos] = Character.toLowerCase(strArr[pos]);
        permHelper( strArr, pos + 1, list);

    }
}
