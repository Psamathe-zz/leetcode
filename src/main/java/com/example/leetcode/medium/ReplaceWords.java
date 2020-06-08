package com.example.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor. For example, the root an, followed by other, which can form another word another.
 *
 * Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.
 *
 * You need to output the sentence after the replacement.
 *
 *
 *
 * Example 1:
 *
 * Input: dict = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * Output: "the cat was rat by the bat"
 *
 *
 * Constraints:
 *
 * The input will only have lower-case letters.
 * 1 <= dict.length <= 1000
 * 1 <= dict[i].length <= 100
 * 1 <= sentence words number <= 1000
 * 1 <= sentence words length <= 1000
 */
public class ReplaceWords {
    public static void main(String[] args) {
        List<String> dict = new ArrayList<>();
        dict.add("cat");
        dict.add("bat");
        dict.add("rat");
        String sentence = "the cattle was rattled by the battery";
        ReplaceWords replaceWords = new ReplaceWords();
        String result = replaceWords.replaceWords(dict,sentence);
        System.out.println(result);
    }

    public String replaceWords(List<String> dict, String sentence) {
        Collections.sort(dict, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        String[] strings = sentence.split(" ");
        List<String> result = new ArrayList<>();
        boolean find = false;
        for(String str : strings){
            find = false;
            for(String dic : dict){
                if(str.startsWith(dic)){
                    result.add(dic);
                    find = true;
                    break;
                }
            }

            if(!find)
                result.add(str);
        }
        return result.stream().map(e->e + " ").collect(Collectors.joining()).trim();
    }

    /**
     * faster solution
     * @param dict
     * @param sentence
     * @return
     */
    public String replaceWordsV0(List<String> dict, String sentence) {
        StringBuilder sb = new StringBuilder();
        Tree tree = new Tree();
        for(String word: dict)
        {
            tree.addWord(word);
        }
        for(String value: sentence.split(" "))
        {
            String prefix = tree.findPrefix(value);
            //System.out.println(value + " "+ prefix);
            if(prefix != null)
            {
                sb.append(prefix).append(" ");
            }
            else
            {
                sb.append(value).append(" ");
            }
        }

        return sb.toString().trim();
    }

    class Tree
    {
        String finalWord = null;
        Tree[] values = new Tree[26];

        public void addWord(String s)
        {
            Tree aux = this;
            for(int i = 0; i < s.length(); i++)
            {
                if(aux.values[s.charAt(i)-'a'] == null)
                {
                    aux.values[s.charAt(i)-'a'] = new Tree();
                }
                aux = aux.values[s.charAt(i)-'a'];
            }
            aux.finalWord = s;
        }

        public String findPrefix(String s)
        {
            Tree aux = this;
            for(int i = 0; i < s.length(); i++)
            {

                if(aux!= null)
                {
                    if(aux.finalWord != null)
                        return aux.finalWord;
                    else
                        aux = aux.values[s.charAt(i)-'a'];
                }
                else
                {
                    break;
                }
            }
            return null;
        }
    }

    /**
     * faster solution
     * @param dict
     * @param sentence
     * @return
     */
    public String replaceWordsV1(List<String> dict, String sentence) {
        TrieNode trie = new TrieNode();
        for (String root: dict) {
            TrieNode curr = trie;
            for (char ch: root.toCharArray()) {
                if (curr.next[ch - 'a'] == null) {
                    curr.next[ch - 'a'] = new TrieNode();
                }
                curr = curr.next[ch - 'a'];
            }
            curr.word = root;
        }

        StringBuilder res = new StringBuilder();
        for (String word: sentence.split(" ")) {
            if (res.length() != 0) {
                res.append(" ");
            }
            TrieNode curr = trie;
            for (char ch: word.toCharArray()) {
                if (curr.next[ch - 'a'] == null || curr.word != null) {
                    break;
                }
                curr = curr.next[ch - 'a'];
            }
            if (curr.word != null) {
                res.append(curr.word);
            } else {
                res.append(word);
            }
        }
        return res.toString();
    }

    private class TrieNode {
        TrieNode[] next;
        String word;
        TrieNode() {
            next = new TrieNode[26];
        }
    }


}
