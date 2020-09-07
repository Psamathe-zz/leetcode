package com.example.leetcode.challenge.september.week1;

import java.util.*;

/**
 * A string S of lowercase English letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part,
 * and return a list of integers representing the size of these parts.
 *
 *
 *
 * Example 1:
 *
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 *
 *
 * Note:
 *
 * S will have length in range [1, 500].
 * S will consist of lowercase English letters ('a' to 'z') only.
 *
 */
public class PartitionLabels {
    public static void main(String[] args) {
        String S = "ababcbacadefegdehijhklij";
        PartitionLabels partitionLabels = new PartitionLabels();
        partitionLabels.partitionLabels(S);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/8654822.html
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {
        int length = S.length();
        List<Integer> res = new ArrayList<>();
        int  start = 0, last = 0;
        Map<Character,Integer> m = new HashMap<>();
        for (int i = 0; i < length; ++i)
            m.put(S.charAt(i),i) ;
        for (int i = 0; i < length; ++i) {
            last = Math.max(last, m.get(S.charAt(i)));
            if (i == last) {
                res.add(i - start + 1);
                start = i + 1;
            }
        }
        return res;
    }

    /**
     * faster solution
     * @param S
     * @return
     */
    public List<Integer> partitionLabelsV1(String S) {
        List<Integer> res= new ArrayList<>();
        int[] last= new int[26];
        int len=S.length();
        char[] sc= S.toCharArray();
        for(int i=0;i<len;i++){
            last[sc[i]-'a']=i;
        }
        int start=-1,end=-1;
        for(int i=0;i<len;i++){
            if(start==-1)
                start=i;
            end= Math.max(end,last[sc[i]-'a']);
            if(end==i){ res.add(end-start+1);
                start=-1;
            }
        }
        return res;
    }
}
