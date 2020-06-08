package com.example.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class MapSum {
    public static void main(String[] args) {

    }
    Map<String,Integer> map;
    public MapSum() {
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        map.put(key,val);
    }

    public int sum(String prefix) {
        int result = 0;
        for(Map.Entry<String,Integer> entry:map.entrySet()){
            if(entry.getKey().startsWith(prefix)){
                result += entry.getValue();
            }
        }
        return result;
    }

    /**
     * faster solution
     */

    private static class Trie {

        private static class Item {

            private Item[] data = new Item[26];
            private int val;
        }

        private Item root;

        private Trie() {
            root = new Item();
        }

        private void add(String w, int val) {
            Item tmp = root;
            for (char c : w.toCharArray()) {
                if (tmp.data[c - 'a'] == null) tmp.data[c - 'a'] = new Item();
                tmp = tmp.data[c - 'a'];
            }
            tmp.val = val;
        }

        private int dfs(Item n) {
            if (n == null) return 0;
            int tmp = n.val;
            for (Item sub : n.data) tmp += dfs(sub);
            return tmp;
        }

        private int sum(String px) {
            Item tmp = root;
            for (char c : px.toCharArray()) {
                if (tmp.data[c - 'a'] == null) return 0;
                tmp = tmp.data[c - 'a'];
            }
            return dfs(tmp);
        }
    }

    private Trie trie;

    /**
     * Initialize your data structure here.
     */
    public MapSum(double d) {
        trie = new Trie();
    }

    public void insertV1(String key, int val) {
        trie.add(key, val);
    }

    public int sumV1(String prefix) {
        return trie.sum(prefix);
    }
}
