package com.example.leetcode.challenge.test2021.January.week2;

import java.util.*;

/**
 * Given two words beginWord and endWord, and a dictionary wordList, return the length of the shortest transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list.
 * Return 0 if there is no such transformation sequence.
 *
 *
 *
 * Example 1:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
 * Example 2:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 *
 *
 * Constraints:
 *
 * 1 <= beginWord.length <= 100
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the strings in wordList are unique.
 */
public class WordLadder {
    public static void main(String[] args) {
        String begin = "hit";
        String end = "cog";
        List<String> list = new ArrayList<>(){};
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");
        list.add("log");
        list.add("cog");
        WordLadder wordLadder = new WordLadder();
        int res = wordLadder.ladderLength(begin,end,list);
        System.out.println(res);
    }

    /**
     * faster solution
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLengthVX(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> transformations = new HashMap<>();
        int wordLen = beginWord.length();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1, wordLen);
                List<String> nextList = transformations.getOrDefault(key, new ArrayList<String>());
                nextList.add(word);
                transformations.put(key, nextList);
            }
        }

        int depth = 1;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(beginWord);
        visited.add(beginWord);

        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                for (int j = 0; j < wordLen; j++) {
                    String key = word.substring(0, j) + "*" + word.substring(j + 1, wordLen);
                    for (String nextStr : transformations.getOrDefault(key, new ArrayList<String>())) {
                        if (nextStr.equals(endWord)) return depth;
                        if (visited.contains(nextStr)) continue;
                        visited.add(nextStr);
                        queue.offer(nextStr);
                    }
                }
            }
        }

        return 0;
    }
    /**
     * http://www.noteanddata.com/leetcode-127-Word-Ladder-java-bfs-solution-note.html
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;

        Queue<String> queue1 = new LinkedList<>();
        queue1.add(beginWord);
        Queue<String> queue2 = new LinkedList<>();
        queue2.add(endWord);

        Set<String> visited = new HashSet<>();
        visited.add(endWord);

        int steps = 1;
        while(queue1.size() > 0 && queue2.size() > 0) {
            // always start from smaller number of queue
            if(queue1.size() > queue2.size()) {
                Queue<String> temp = queue1;
                queue1 = queue2;
                queue2 = temp;
            }

            Queue<String> nextQueue = new LinkedList<>();
            while(!queue1.isEmpty()) {
                String cur = queue1.poll();
                for(String word: wordList) {
                    if(valid(cur, word)) {
                        if(queue2.contains(word)) {
                            return steps+1;
                        }

                        if(!visited.contains(word)) {
                            nextQueue.add(word);
                            visited.add(word);
                        }
                    }
                }
            }
            queue1 = nextQueue;
            steps++;
        }
        return 0;
    }

    boolean valid(String a, String b) {
        int diff = 0;
        for(int i = 0; i < a.length(); ++i) {
            if(a.charAt(i) != b.charAt(i)) {
                diff++;
                if(diff >= 2) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4539768.html
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLengthV0(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord))
            return 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int res = 1;
        while (!queue.isEmpty()) {
            for (int k = queue.size(); k > 0; --k) {
                String word = queue.poll();
                if (word.equals(endWord))
                    return res;
                for (int i = 0; i < word.length(); ++i) {
                    for (char ch = 'a'; ch <= 'z'; ++ch) {
                        String mid = word.substring(0,i) + (char)ch + word.substring(i+1);
                        if (wordList.contains(mid) && !mid.equals(word)) {
                            queue.offer(mid);
                            wordList.remove(mid);
                        }
                    }
                }
            }
            ++res;
        }
        return 0;
    }
    public int ladderLengthV1(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord))
            return 0;

        Map<String,Integer> count = new HashMap<>();
        count.put(beginWord,1);

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        char[] newWord;
        String temp;
        while (!queue.isEmpty()) {
            String word = queue.poll();
            for (int i = 0; i < word.length(); ++i) {
                newWord = word.toCharArray();
                for (char ch = 'a'; ch <= 'z'; ++ch) {
                    newWord[i] = ch;
                    temp = String.valueOf(newWord);
                    if (wordList.contains(temp) && temp.equals(endWord))
                        return count.get(word) + 1;
                    if (wordList.contains(temp) && !count.containsKey(temp)) {
                        queue.offer(temp);
                        count.put(temp,count.get(word) + 1);
                    }
                }
            }
        }
        return 0;
    }

}
