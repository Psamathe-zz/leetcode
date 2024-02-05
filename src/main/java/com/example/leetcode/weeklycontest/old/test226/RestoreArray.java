package com.example.leetcode.weeklycontest.old.test226;


import java.util.*;

/**
 * There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of adjacent elements in nums.
 *
 * You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.
 *
 * It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.
 *
 * Return the original array nums. If there are multiple solutions, return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: adjacentPairs = [[2,1],[3,4],[3,2]]
 * Output: [1,2,3,4]
 * Explanation: This array has all its adjacent pairs in adjacentPairs.
 * Notice that adjacentPairs[i] may not be in left-to-right order.
 * Example 2:
 *
 * Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
 * Output: [-2,4,1,-3]
 * Explanation: There can be negative numbers.
 * Another solution is [-3,1,4,-2], which would also be accepted.
 * Example 3:
 *
 * Input: adjacentPairs = [[100000,-100000]]
 * Output: [100000,-100000]
 */
public class RestoreArray {
    public static void main(String[] args) {
    }

    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int size = adjacentPairs.length + 1;
        int[] res = new int[size];
        List<Integer> list;
        for (int[] pairs : adjacentPairs){
            list = map.getOrDefault(pairs[0],new ArrayList<>());
            list.add(pairs[1]);
            map.put(pairs[0],list);
            list = map.getOrDefault(pairs[1],new ArrayList<>());
            list.add(pairs[0]);
            map.put(pairs[1],list);
        }
        int a = Integer.MIN_VALUE;
        int b = Integer.MIN_VALUE;
        for (Map.Entry<Integer,List<Integer>> entry : map.entrySet()){
            if(entry.getValue().size() == 1){
                if(a == Integer.MIN_VALUE)
                    a = entry.getKey();
                else
                    b = entry.getKey();
            }
        }
        res[0] = a;
        int cur = a;
        int next = b;
        int index = 1;
        Set<Integer> set = new HashSet<>();
        do {
            set.add(cur);
            System.out.println(cur);
            for(Integer val :  map.get(cur)){
                if (!set.contains(val)) {
                    next = val;
                    break;
                }
            }
            cur = next;
            res[index++] = cur;
        } while (next != b);
        return res;
    }


    /**
     * faster solution
     * @param adj
     * @return
     */
    public int[] restoreArrayV0(int[][] adj) {
        HashMap<Integer, Node> cache = new HashMap<>();
        for(int[] arr : adj) {
            Node arr0 = cache.getOrDefault(arr[0], new Node(arr[0]));
            Node arr1 = cache.getOrDefault(arr[1], new Node(arr[1]));
            if(arr0.pre == null) {
                if(arr1.nxt != null) {
                    arr0.pre = arr1;
                    arr1.pre = arr0;
                }
                else{
                    arr0.pre = arr1;
                    arr1.nxt = arr0;
                }
            } else if(arr0.nxt == null) {
                if(arr1.pre != null) {
                    arr0.nxt = arr1;
                    arr1.nxt = arr0;
                } else {
                    arr0.nxt = arr1;
                    arr1.pre = arr0;
                }
            }
            cache.put(arr[0], arr0);
            cache.put(arr[1], arr1);
        }
        int[] res = new int[cache.size()];
        Node cur = null;
        int index = 0;
        for(Node node : cache.values())
            if(node.pre == null || node.nxt == null) {
                cur = node;
                break;
            }
        res[index ++] = cur.val;
        cur = cur.nxt == null ? cur.pre : cur.nxt;
        while(cur != null) {
            res[index] = cur.val;
            if(cur.nxt != null ) {
                if(res[index - 1] != cur.nxt.val) cur = cur.nxt;
                else cur = cur.pre;
            } else {
                if(res[index - 1] != cur.pre.val) cur = cur.pre;
                else cur = cur.nxt;
            }
            index ++;
        }
        return res;
    }
    class Node {
        Node pre = null;
        Node nxt = null;
        int val;
        Node(int val) {
            this.val = val;
        }
    }

}
