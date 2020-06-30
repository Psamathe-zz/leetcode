package com.example.leetcode.medium;

import java.util.*;

/**
 * Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.
 *
 * Example :
 * Input:
 * S = "abcde"
 * words = ["a", "bb", "acd", "ace"]
 * Output: 3
 * Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
 * Note:
 *
 * All words in words and S will only consists of lowercase letters.
 * The length of S will be in the range of [1, 50000].
 * The length of words will be in the range of [1, 5000].
 * The length of words[i] will be in the range of [1, 50].
 *
 * "dsahjpjauf"
 * ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 */
public class NumberMatchingSubsequences {
    public static void main(String[] args) {
        String S = "dsahjpjauf";
        String[] words = new String[]{"ahjpjau","ja","ahbwzgqnuk","tnmlanowax"};
        NumberMatchingSubsequences numberMatchingSubsequences = new NumberMatchingSubsequences();
        numberMatchingSubsequences.numMatchingSubseq(S,words);
    }
    public int numMatchingSubseq(String S, String[] words) {
        int result = 0;
        HashSet<String> subSeqSet = new HashSet();
        HashSet<String> nonSeqSet = new HashSet();
        for(String word : words){
            if(subSeqSet.contains(word)){
                result++;
                continue;
            }else if(nonSeqSet.contains(word)){
                continue;
            }
            if(helper(S,word)) {
                subSeqSet.add(word);
                result++;
            }else{
                nonSeqSet.add(word);
            }
        }
        return result;
    }

    public boolean helper(String S,String target){
        int index=-1;
        for(char ch:target.toCharArray()){
            index=S.indexOf(ch,index+1);
            if(index==-1)
                return false;

        }
        return true;
    }


    /**
     * faster solution
     * @param S
     * @param words
     * @return
     */
    public int numMatchingSubseqV1(String S, String[] words) {

        LinkedList<Item>[] arr = new LinkedList[26];
        for (int i = 0; i < 26; i++)
            arr[i] = new LinkedList<Item>();

        for (String w : words)
            arr[w.charAt(0) - 'a'].addLast(new Item(w, 0));

        int res = 0;
        for (char c : S.toCharArray()) {
            LinkedList<Item> list = arr[c - 'a'];
            for (int i = 0, len = list.size(); i < len; i++) {
                Item item = list.removeFirst();
                if (item.index == item.word.length() - 1)
                    res++;
                else {
                    item.index++;
                    arr[item.word.charAt(item.index) - 'a'].addLast(item);
                }

            }
        }
        return res;
    }

    class Item {
        String word;
        int index;

        public Item(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }
}
