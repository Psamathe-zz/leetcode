package com.example.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string.
 * If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.
 *
 * Example 1:
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 *
 * Output:
 * "apple"
 *
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
public class LongestWordinDictionarythroughDeleting {
    public static void main(String[] args) {
        String s = "abpcplea";
        List<String> d = Arrays.asList(new String[]{"ale","apple","monkey","plea"});
        LongestWordinDictionarythroughDeleting longestWordinDictionarythroughDeleting = new LongestWordinDictionarythroughDeleting();
        String result = longestWordinDictionarythroughDeleting.findLongestWord(s,d);
        System.out.println(result);
    }

    public String findLongestWord(String s, List<String> d) {
        d = d.stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o2.length() != o1.length())
                    return o2.length() - o1.length();
                else
                    return o1.compareTo(o2);
            }
        }).collect(Collectors.toList());

        for (String dic : d){
            if(isInDic(s,dic)){
                return dic;
            }
        }

        return "";
    }
    public boolean isInDic(String s,String dic){
        int pos1 = 0;
        int pos2 = 0;
        while ( pos1 < s.length() && pos2 < dic.length()){
            if(s.charAt(pos1) == dic.charAt(pos2)){
                pos2++;
            }
            pos1++;
        }

        return pos2 == dic.length();
    }

    /**
     * faster solution
     * @param s
     * @param d
     * @return
     */

    public String findLongestWordV1(String s, List<String> d) {
        final int n = s.length();
        int[][] jump = new int[n + 1][26];
        Arrays.fill(jump[n], -1);
        for (int i = n - 1; i >= 0; i--) {
            jump[i] = Arrays.copyOf(jump[i + 1], 26);
            jump[i][s.charAt(i) - 'a'] = i + 1;
        }

        String res = "";
        for (String dict : d) {
            if (dict.length() >= res.length() && isSubstring(dict, jump)) {
                if (res.length() < dict.length()
                        || res.compareTo(dict) > 0) {
                    res = dict;
                }
            }
        }
        return res;
    }

    private boolean isSubstring(String dict, int[][] jump) {
        for (int i = 0, j = 0; j < dict.length(); j++) {
            i = jump[i][dict.charAt(j) - 'a'];
            if (i < 0) {
                return false;
            }
        }
        return true;
    }
}
