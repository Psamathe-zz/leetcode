package com.example.leetcode.weeklycontest.test317;

import java.util.*;
import java.util.stream.Collectors;

/**
 * You are given two string arrays creators and ids, and an integer array views, all of length n. The ith video on a platform was created by creator[i], has an id of ids[i], and has views[i] views.
 *
 * The popularity of a creator is the sum of the number of views on all of the creator's videos. Find the creator with the highest popularity and the id of their most viewed video.
 *
 * If multiple creators have the highest popularity, find all of them.
 * If multiple videos have the highest view count for a creator, find the lexicographically smallest id.
 * Return a 2D array of strings answer where answer[i] = [creatori, idi] means that creatori has the highest popularity and idi is the id of their most popular video. The answer can be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: creators = ["alice","bob","alice","chris"], ids = ["one","two","three","four"], views = [5,10,5,4]
 * Output: [["alice","one"],["bob","two"]]
 * Explanation:
 * The popularity of alice is 5 + 5 = 10.
 * The popularity of bob is 10.
 * The popularity of chris is 4.
 * alice and bob are the most popular creators.
 * For bob, the video with the highest view count is "two".
 * For alice, the videos with the highest view count are "one" and "three". Since "one" is lexicographically smaller than "three", it is included in the answer.
 * Example 2:
 *
 * Input: creators = ["alice","alice","alice"], ids = ["a","b","c"], views = [1,2,2]
 * Output: [["alice","b"]]
 * Explanation:
 * The videos with id "b" and "c" have the highest view count.
 * Since "b" is lexicographically smaller than "c", it is included in the answer.
 *
 */
public class MostPopularVideoCreator {
    public static void main(String[] args) {

    }

    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        Map<String, Integer> count = new HashMap<>();
        Map<String, Integer> max = new HashMap<>();
        Map<String, String> id = new HashMap<>();
        int length = creators.length;
        for (int i = 0; i < length; i++) {
            int finalI = i;
            count.compute(creators[i], (k, v) -> {
                if(v == null)
                    return views[finalI];
                else
                    return v+views[finalI];
            });
            if(max.containsKey(creators[i])) {
                if(max.get(creators[i]) < views[i]) {
                    max.put(creators[i], views[i]);
                    id.put(creators[i], ids[i]);
                } else if(max.get(creators[i]) == views[i] && id.get(creators[i]).compareTo(ids[i])  > 0) {
                    id.put(creators[i], ids[i]);
                }
            } else {
                max.put(creators[i], views[i]);
                id.put(creators[i], ids[i]);
            }
        }
        List<List<String>> res = new ArrayList<>();
        List<String> list;
        List<Map.Entry<String,Integer>> lis = count.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(Collectors.toList());

        Integer maxV = null;
        for (Map.Entry<String,Integer> entry: lis) {
            if(maxV == null) {
                maxV = entry.getValue();
            } else if(!maxV.equals(entry.getValue())) {
                break;
            }
            list = new ArrayList<>();
            list.add(entry.getKey());
            list.add(id.get(entry.getKey()));
            res.add(list);
        }
        return res;
    }
}
