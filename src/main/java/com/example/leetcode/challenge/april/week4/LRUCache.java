package com.example.leetcode.challenge.april.week4;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 * The cache is initialized with a positive capacity.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
 * LRUCache cache = new LRUCache(2)
        *
        *cache.put(1,1);
        *cache.put(2,2);
        *cache.get(1);       // returns 1
        *cache.put(3,3);    // evicts key 2
        *cache.get(2);       // returns -1 (not found)
        *cache.put(4,4);    // evicts key 1
        *cache.get(1);       // returns -1 (not found)
        *cache.get(3);       // returns 3
        *cache.get(4);       // returns 4
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(2));
        cache.put(4,4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }

    Map<Integer,Integer> map;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new LinkedHashMap<>();
    }

    public int get(int key) {
        if(map.containsKey(key)){
            int result = map.remove(key);
            map.put(key,result);
            return result;
        } else {
            return -1;
        }

    }

    public void put(int key, int value) {
        get(key);
        if(map.size() == capacity){
            if(!map.containsKey(key)){
                Iterator lterator = map.keySet().iterator();
                map.remove(lterator.next());
            }
            map.put(key,value);
        } else {
            map.put(key,value);
        }
    }
}
