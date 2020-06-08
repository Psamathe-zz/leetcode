package com.example.leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * To some string S, we will perform some replacement operations that replace groups of letters with new ones (not necessarily the same size).
 *
 * Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.  The rule is that if x starts at position i in the original string S, then we will replace that occurrence of x with y.  If not, we do nothing.
 *
 * For example, if we have S = "abcd" and we have some replacement operation i = 2, x = "cd", y = "ffff", then because "cd" starts at position 2 in the original string S, we will replace it with "ffff".
 *
 * Using another example on S = "abcd", if we have both the replacement operation i = 0, x = "ab", y = "eee", as well as another replacement operation i = 2, x = "ec", y = "ffff", this second operation does nothing because in the original string S[2] = 'c', which doesn't match x[0] = 'e'.
 *
 * All these operations occur simultaneously.  It's guaranteed that there won't be any overlap in replacement: for example, S = "abc", indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.
 *
 * Example 1:
 *
 * Input: S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
 * Output: "eeebffff"
 * Explanation: "a" starts at index 0 in S, so it's replaced by "eee".
 * "cd" starts at index 2 in S, so it's replaced by "ffff".
 * Example 2:
 *
 * Input: S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
 * Output: "eeecd"
 * Explanation: "ab" starts at index 0 in S, so it's replaced by "eee".
 * "ec" doesn't starts at index 2 in the original S, so we do nothing.
 * Notes:
 *
 * 0 <= indexes.length = sources.length = targets.length <= 100
 * 0 < indexes[i] < S.length <= 1000
 * All characters in given inputs are lowercase letters.
 * "vmokgggqzp"
 * [3,5,1]
 * ["kg","ggq","mo"]
 * ["s","so","bfr"]
 */
public class FindAndReplaceString {
    public static void main(String[] args) {
        String S = "vmokgggqzp";
        int[] indexes = new int[]{3,5,1};
        String[] sources = new String[]{"kg","ggq","mo"};
        String[] targets = new String[]{"s","so","bfr"};
        FindAndReplaceString findAndReplaceString = new FindAndReplaceString();
        String result = findAndReplaceString.findReplaceString(S,indexes,sources,targets);
        System.out.println(result);
    }

    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        StringBuffer stringBuffer = new StringBuffer();
        Map<Integer,SourceTarget > map = new HashMap<>();
        int start = 0;
        int end ;
        for (int i = 0; i < indexes.length; i++) {
            map.put(indexes[i],new SourceTarget(sources[i],targets[i]));
        }
        for (Map.Entry<Integer,SourceTarget> entry : map.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList())) {
            end = entry.getKey();
            if(end > 0){
                stringBuffer.append(S, start, end);
            }
            if(S.substring(entry.getKey(),entry.getKey()+ entry.getValue().getSource().length()).equals(entry.getValue().getSource())){
                stringBuffer.append(entry.getValue().getTarget());
            } else {
                stringBuffer.append(S, entry.getKey(), entry.getKey()+ entry.getValue().getSource().length());
            }
            start = entry.getKey() + entry.getValue().getSource().length();
        }
        stringBuffer.append(S, start, S.length());
        return stringBuffer.toString();
    }
    public class SourceTarget{
        String source;
        String target;

        public SourceTarget(String source, String target) {
            this.source = source;
            this.target = target;
        }

        public String getSource() {
            return source;
        }

        public String getTarget() {
            return target;
        }
    }

    /**
     * faster solution
     * @param S
     * @param indexes
     * @param sources
     * @param targets
     * @return
     */
    public String findReplaceStringV1(String S, int[] indexes, String[] sources, String[] targets) {
        int l = S.length();
        int[] pick = new int[l];
        Arrays.fill(pick, -1);
        for(int i = 0; i < indexes.length; i++) {
            pick[indexes[i]] = i;
        }
        StringBuilder sb = new StringBuilder();
        int k = 0;
        while(k < l) {
            if(pick[k] == -1) {
                sb.append(S.charAt(k++));
            } else {
                String src = sources[pick[k]];
                if(!S.startsWith(src, k)) {
                    sb.append(S.charAt(k++));
                    continue;
                }
                String trg = targets[pick[k]];
                sb.append(trg);
                k += src.length();
            }
        }
        return sb.toString();
    }

    /**
     * faster solution
     * @param S
     * @param indexes
     * @param sources
     * @param targets
     * @return
     */
    public String findReplaceStringV2(String S, int[] indexes, String[] sources, String[] targets) {
        String[] stringArray = new String[S.length()];

        for (int j = 0; j < indexes.length; j++) {
            int index = indexes[j];
            String source = sources[j];
            String target = targets[j];

            if (!S.substring(index, index + source.length()).equals(source)) {
                continue;
            }

            stringArray[index] = target;
            for (int i = index+1; i < source.length()+index; i++) {
                stringArray[i]="";
            }
        }

        String res = "";
        for (int index = 0; index < S.length(); index ++) {
            res += stringArray[index] == null ? S.charAt(index) : stringArray[index] ;
        }

        return res;
    }
}
