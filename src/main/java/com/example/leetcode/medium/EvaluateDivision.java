package com.example.leetcode.medium;

import java.util.*;

/**
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number).
 * Given some queries, return the answers. If the answer does not exist, return -1.0.
 *
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive.
 * This represents the equations. Return vector<double>.
 *
 * According to the example above:
 *
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 *
 * [["a","b"],["e","f"],["b","e"]]
 * [3.4,1.4,2.3]
 * [["b","a"],["a","f"],["f","f"],["e","e"],["c","c"],["a","c"],["f","e"]]
 *
 * [["x1","x2"],["x2","x3"],["x3","x4"],["x4","x5"]]
 * [3.0,4.0,5.0,6.0]
 * [["x1","x5"],["x5","x2"],["x2","x4"],["x2","x2"],["x2","x9"],["x9","x9"]]
 * Output:
 * [28800.00000,0.00010,80.00000,1.00000,-1.00000,-1.00000]
 * Expected:
 * [360.00000,0.00833,20.00000,1.00000,-1.00000,-1.00000]
 */
public class EvaluateDivision {
    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("x1","x2"));
        equations.add(Arrays.asList("x2","x3"));
        equations.add(Arrays.asList("x3","x4"));
        equations.add(Arrays.asList("x4","x5"));
        equations.add(Arrays.asList("x6","x7"));
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("x1","x5"));
        queries.add(Arrays.asList("x5","x2"));
        queries.add(Arrays.asList("x2","x4"));
        queries.add(Arrays.asList("x2","x2"));
        queries.add(Arrays.asList("x2","x9"));
        queries.add(Arrays.asList("x9","x9"));
        queries.add(Arrays.asList("x1","x6"));
        double[] values = new double[]{3.0,4.0,5.0,6.0,2.0};
        EvaluateDivision evaluateDivision = new EvaluateDivision();
        double[] result = evaluateDivision.calcEquation(equations,values,queries);
        result.toString();

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


    /**
     * less memory
     */
    class RepresentantAndMultiplier {
        String rep;
        double mult;

        RepresentantAndMultiplier (String rep, double mult) {
            this.rep = rep;
            this.mult = mult;
        }
    }

    public double[] calcEquationV2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> nodeToEdges = new HashMap<>();
        int n = equations.size();
        for (int i = 0; i < n; ++i) {
            String nominator = equations.get(i).get(0);
            String denominator = equations.get(i).get(1);
            double value = values[i];
            nodeToEdges.computeIfAbsent(nominator, nom -> new HashMap<>()).put(denominator, value);
            nodeToEdges.computeIfAbsent(denominator, de -> new HashMap<>()).put(nominator, 1.0 / value);
        }

        Map<String, RepresentantAndMultiplier> nodeToRepresentant = new HashMap<>();
        for (String nominator : nodeToEdges.keySet()) {
            if (!nodeToRepresentant.containsKey(nominator)) {
                double multiplier = 1.0;
                nodeToRepresentant.put(nominator, new RepresentantAndMultiplier(nominator, multiplier));

                LinkedList<String> stack = new LinkedList<>();
                stack.push(nominator);

                while (!stack.isEmpty()) {
                    String node = stack.pop();
                    RepresentantAndMultiplier ram = nodeToRepresentant.get(node);
                    for (Map.Entry<String, Double> outEdge : nodeToEdges.get(node).entrySet()) {
                        String outName = outEdge.getKey();
                        Double outValue = outEdge.getValue();
                        if (!nodeToRepresentant.containsKey(outName)) {
                            stack.push(outName);
                            nodeToRepresentant.put(
                                    outName, new RepresentantAndMultiplier(ram.rep, ram.mult * outValue));
                        }
                    }
                }
            }
        }

        int nQueries = queries.size();
        double[] result = new double[nQueries];
        for (int i = 0; i < nQueries; ++i) {
            String nominator = queries.get(i).get(0);
            String denominator = queries.get(i).get(1);

            RepresentantAndMultiplier nRam = nodeToRepresentant.get(nominator);
            RepresentantAndMultiplier dRam = nodeToRepresentant.get(denominator);

            if (nRam != null && dRam != null && nRam.rep.equals(dRam.rep)) {
                result[i] = dRam.mult / nRam.mult;
            } else {
                result[i] = -1.0;
            }
        }

        return result;
    }
}
