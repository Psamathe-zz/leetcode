package com.example.leetcode.challenge.test2020.June.week4;

import java.util.*;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order.
 * All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 *
 * Note:
 *
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * One must use all the tickets once and only once.
 * Example 1:
 *
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * Example 2:
 *
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 *              But it is larger in lexical order.
 */
public class ReconstructItinerary {

    public static void main(String[] args) {

    }

    private HashMap<String, List<String>> adj = new HashMap<String, List<String>>();
    public List<String> findItinerary(List<List<String>> tickets) {
        for (int i = 0; i < tickets.size(); i++) {
            String from = tickets.get(i).get(0);
            String to = tickets.get(i).get(1);
            if (!adj.containsKey(from)) {
                adj.put(from, new ArrayList<String>());
            }
            insert(to, adj.get(from));
        }
        int total = tickets.size() + 1;
        List<String> ret = new ArrayList<String>();
        ret.add("JFK");
        helper("JFK", ret, 1, total);

        return ret;
    }

    private boolean helper(String from, List<String> ret, int num, int total) {
        if (num >= total) {
            return true;
        }
        else if (!adj.containsKey(from) || adj.get(from).size() == 0) {
            return false;
        }

        for (int i = 0; i < adj.get(from).size(); i++) {
            String to = adj.get(from).get(i);
            ret.add(to);
            adj.get(from).remove(i);
            boolean flag = helper(to, ret, num + 1, total);
            if (flag) {
                return true;
            }
            else {
                ret.remove(ret.size() - 1);
                adj.get(from).add(i, to);
            }
        }

        return false;
    }

    private void insert(String s, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (s.compareTo(list.get(i)) <= 0) {
                list.add(i, s);
                return;
            }
        }

        list.add(s);
    }


    /**
     * faster soluition
     */
    static HashMap<String,List<String>> map;

    public static void addEdge(String x,String y){
        if(map.containsKey(x)){
            List<String> l=map.get(x);
            l.add(y);
            int i=l.size()-1;
            while(i>0 && l.get(i).compareTo(l.get(i-1))>0){

                String temp=l.get(i);
                l.set(i,l.get(i-1));
                l.set(i-1,temp);
                i--;
            }
        }
        else{
            List<String> l=new ArrayList<>();
            l.add(y);
            map.put(x,l);
        }
    }

    public static void dfs(String src,List<String> ans){
        List<String> l=map.get(src);

        if(l==null || l.size()==0){
            ans.add(0,src);
            return;
        }

        while(l.size()!=0){
            int last=l.size()-1;
            String child=l.get(last);
            l.remove(last);
            dfs(child,ans);
        }

        ans.add(0,src);
    }

    public List<String> findItineraryV1(List<List<String>> tickets) {
        map=new HashMap<>();

        for(List<String> list:tickets){
            addEdge(list.get(0),list.get(1));
        }

        List<String> ans=new ArrayList<>();
        String src="JFK";
        dfs(src,ans);
        return ans;
    }
}
