package com.example.leetcode.challenge.August.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Design a HashSet without using any built-in hash table libraries.
 *
 * To be specific, your design should include these functions:
 *
 * add(value): Insert a value into the HashSet.
 * contains(value) : Return whether the value exists in the HashSet or not.
 * remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.
 *
 * Example:
 *
 * MyHashSet hashSet = new MyHashSet();
 * hashSet.add(1);
 * hashSet.add(2);
 * hashSet.contains(1);    // returns true
 * hashSet.contains(3);    // returns false (not found)
 * hashSet.add(2);
 * hashSet.contains(2);    // returns true
 * hashSet.remove(2);
 * hashSet.contains(2);    // returns false (already removed)
 *
 * Note:
 *
 * All values will be in the range of [0, 1000000].
 * The number of operations will be in the range of [1, 10000].
 * Please do not use the built-in HashSet library.
 */
public class DesignHashSet {

    List<Integer> set;
    public DesignHashSet() {
        set = new ArrayList<>();
    }

    public void add(int key) {
        if(!set.contains(key))
            set.add(key);
    }

    public void remove(int key) {
        if(set.contains(Integer.valueOf(key)))
            set.remove(Integer.valueOf(key));
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return set.contains(key);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/9966807.html
     * https://leetcode.com/submissions/detail/374866795/?from=/explore/challenge/card/august-leetcoding-challenge/549/week-1-august-1st-august-7th/3410/
     */
    int[][] data;
    public DesignHashSet(int a) {
        data = new int[1000][];
    }

    void addV1(int key) {
        int hashKey = key % 1000;
        if (data[hashKey] == null) {
            data[hashKey] = new int[1000];
            Arrays.fill(data[hashKey],-1);
        }
        data[hashKey][key / 1000] = 1;
    }

    void removeV1(int key) {
        int hashKey = key % 1000;
        if (data[hashKey] != null) {
            data[hashKey][key / 1000] = 0;
        }
    }

    /** Returns true if this set contains the specified element */
    boolean containsV1(int key) {
        int hashKey = key % 1000;
        return data[hashKey] != null && data[hashKey][key / 1000] != -1;
    }
}
