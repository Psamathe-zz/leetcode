package com.example.leetcode.weeklycontest.test187;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given the array paths, where paths[i] = [cityAi, cityBi] means there exists a direct path going from cityAi to cityBi.
 * Return the destination city, that is, the city without any path outgoing to another city.
 *
 * It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly one destination city.
 *
 *
 *
 * Example 1:
 *
 * Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
 * Output: "Sao Paulo"
 * Explanation: Starting at "London" city you will reach "Sao Paulo" city which is the destination city.
 * Your trip consist of: "London" -> "New York" -> "Lima" -> "Sao Paulo".
 * Example 2:
 *
 * Input: paths = [["B","C"],["D","B"],["C","A"]]
 * Output: "A"
 * Explanation: All possible trips are:
 * "D" -> "B" -> "C" -> "A".
 * "B" -> "C" -> "A".
 * "C" -> "A".
 * "A".
 * Clearly the destination city is "A".
 * Example 3:
 *
 * Input: paths = [["A","Z"]]
 * Output: "Z"
 */
public class DestinationCity {

    public static void main(String[] args) {
        List<List<String>> paths = new ArrayList<>();
        paths.add(Arrays.asList("London","New York"));
        paths.add(Arrays.asList("New York","Lima"));
        paths.add(Arrays.asList("Lima","Sao Paulo"));
        DestinationCity destinationCity = new DestinationCity();
        String result = destinationCity.destCity(paths);
        System.out.println(result);
    }

    public String destCity(List<List<String>> paths) {
        String start = paths.get(0).get(0);
        String destination = paths.get(0).get(1);
        boolean[] isVisited = new boolean[paths.size()];
        isVisited[0] = true;
        boolean isStable;
        do {
            isStable = true;
            for (int i = 0; i < paths.size();i++) {
                if(paths.get(i).get(0).equals(destination) && isVisited[i] == false){
                    destination = paths.get(i).get(1);
                    isStable = false;
                    isVisited[i] = true;
                }
                if(paths.get(i).get(1).equals(start) && isVisited[i] == false){
                    start = paths.get(i).get(0);
                    isStable = false;
                    isVisited[i] = true;
                }
            }
        } while (!isStable);



        return destination;
    }
}
