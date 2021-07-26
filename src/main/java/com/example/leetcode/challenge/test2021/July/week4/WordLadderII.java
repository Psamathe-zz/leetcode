package com.example.leetcode.challenge.test2021.July.week4;

import java.util.*;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
 *
 *
 *
 * Example 1:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * Explanation: There are 2 shortest transformation sequences:
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 * Example 2:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: []
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 */
public class WordLadderII {
    public static void main(String[] args) {

    }



    public List<List<String>> findLadders(String start, String end, List<String> dict) {
        List<List<String>> res = new ArrayList<List<String>>();
        if(start.compareTo(end) == 0) return res;
        Set<String> visited = new HashSet<String>();
        Set<String> cur = new HashSet<String>();
        HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();
        graph.put(end,new ArrayList<String>());
        cur.add(start);
        boolean found = false;
        while (cur.isEmpty() == false && found == false) {
            Set<String> queue = new HashSet<String>();
            for(Iterator<String> it=cur.iterator();it.hasNext();) {
                visited.add(it.next());
            }
            for(Iterator<String> it=cur.iterator();it.hasNext();) {
                String str = it.next();
                char[] word = str.toCharArray();
                for (int j = 0; j < word.length; ++j) {
                    char before = word[j];
                    for (char ch = 'a'; ch <= 'z'; ++ch) {
                        if (ch == before) continue;
                        word[j] = ch;
                        String temp = new String(word);
                        if (dict.contains(temp) == false) continue;
                        if (found == true && end.compareTo(temp) != 0) continue;
                        if (end.compareTo(temp) == 0) {
                            found = true;
                            (graph.get(end)).add(str);
                            continue;
                        }
                        if (visited.contains(temp) == false) {
                            queue.add(temp);
                            if (graph.containsKey(temp) == false) {
                                ArrayList<String> l = new ArrayList<String>();
                                l.add(str);
                                graph.put(temp,l);
                            } else {
                                (graph.get(temp)).add(str);
                            }
                        }
                    }
                    word[j] = before;
                }
            }
            cur = queue;
        }
        if (found == true) {
            ArrayList<String> path = new ArrayList<String>();
            trace(res, graph, path, start, end);
        }
        return res;
    }
    public void trace(List<List<String>> res,
                      HashMap<String, ArrayList<String>> graph,
                      ArrayList<String> path,
                      String start, String now) {
        path.add(now);
        if (now.compareTo(start) == 0) {
            ArrayList<String> ans = new ArrayList<String>(path);
            Collections.reverse(ans);
            res.add(ans);
            path.remove(path.size()-1);
            return;
        }
        ArrayList<String> cur = graph.get(now);
        for (int i = 0; i < cur.size(); ++i) {
            trace(res,graph,path,start,cur.get(i));
        }
        path.remove(path.size()-1);
    }
}
