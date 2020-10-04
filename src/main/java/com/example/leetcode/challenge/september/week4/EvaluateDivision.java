package com.example.leetcode.challenge.september.week4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given equations in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating-point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 *
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 *
 *
 *
 * Example 1:
 *
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * Example 2:
 *
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 * Example 3:
 *
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 *
 *
 * Constraints:
 *
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= equations[i][0], equations[i][1] <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= queries[i][0], queries[i][1] <= 5
 * equations[i][0], equations[i][1], queries[i][0], queries[i][1] consist of lower case English letters and digits.
 */
public class EvaluateDivision {
    public static void main(String[] args) {

    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] result = new double[queries.size()];
        List<String> toCheckList = new ArrayList<>();

        for(int i = 0; i < equations.size();i++){
            if(!toCheckList.contains(equations.get(i).get(0)))
                toCheckList.add(equations.get(i).get(0));
            if(!toCheckList.contains(equations.get(i).get(1)))
                toCheckList.add(equations.get(i).get(1));
        }

        double[][] checkMap = new double[toCheckList.size()][toCheckList.size()];
        for(int i = 0; i<toCheckList.size();i++){
            for (int j = 0; j < toCheckList.size(); j++) {
                if(i==j)
                    checkMap[i][j] = 1;
                else
                    checkMap[i][j] = -1;
            }
        }

        for(int i = 0; i < equations.size();i++){

            List<String> equation = equations.get(i);
            int x = toCheckList.indexOf(equation.get(0));
            int y = toCheckList.indexOf(equation.get(1));
            double value = values[i];
            checkMap[x][y] = value;
            checkMap[y][x] = 1/value;

        }

        double[] dis  =  new double[toCheckList.size()];
        boolean[] visited  =  new boolean[toCheckList.size()];
        for(int i = 0 ; i < queries.size() ; i++ ){
            String key1 = queries.get(i).get(0);
            String key2 = queries.get(i).get(1);

            int x = toCheckList.indexOf(key1);
            int y = toCheckList.indexOf(key2);

            if(x==-1 || y == -1){
                result[i] = -1.0;
                continue;
            }
            Arrays.fill(visited,false);
            visited[x] = true;
            Arrays.fill(dis,-1);

            for(int index = 0; index<toCheckList.size();index++){
                dis[index] = checkMap[x][index];
            }
            int visit;
            do {
                visit=findMinIndex(dis,visited);
                if(visit == -1)
                    break;
                visited[visit] = true;

                for(int index = 0; index<toCheckList.size();index++){
                    if(visited[index] == false && checkMap[visit][index] > 0){
                        dis[index] = dis[index] == -1 ? dis[visit] * checkMap[visit][index] :Math.min(dis[visit] * checkMap[visit][index],dis[index]);
                    }
                }
            } while(y != visit);
            result[i] = dis[y];
        }

        return result;
    }


    public int findMinIndex(double[] toCheck,boolean[] visited){
        int index = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < toCheck.length; i++) {
            if(toCheck[i] > 0 && toCheck[i] < min && !visited[i]){
                index = i;
            }
        }
        return index;
    }
}
