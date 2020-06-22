package com.example.leetcode.weeklycontest.test194;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Given a weighted undirected connected graph with n vertices numbered from 0 to n-1, and an array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between nodes fromi and toi. A minimum spanning tree (MST) is a subset of the edges of the graph that connects all vertices without cycles and with the minimum possible total edge weight.
 *
 * Find all the critical and pseudo-critical edges in the minimum spanning tree (MST) of the given graph. An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical edge. A pseudo-critical edge, on the other hand, is that which can appear in some MSTs but not all.
 *
 * Note that you can return the indices of the edges in any order.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
 * Output: [[0,1],[2,3,4,5]]
 * Explanation: The figure above describes the graph.
 * The following figure shows all the possible MSTs:
 *
 * Notice that the two edges 0 and 1 appear in all MSTs, therefore they are critical edges, so we return them in the first list of the output.
 * The edges 2, 3, 4, and 5 are only part of some MSTs, therefore they are considered pseudo-critical edges. We add them to the second list of the output.
 * Example 2:
 *
 *
 *
 * Input: n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
 * Output: [[],[0,1,2,3]]
 * Explanation: We can observe that since all 4 edges have equal weight, choosing any 3 edges from the given 4 will yield an MST. Therefore all 4 edges are pseudo-critical.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 100
 * 1 <= edges.length <= min(200, n * (n - 1) / 2)
 * edges[i].length == 3
 * 0 <= fromi < toi < n
 * 1 <= weighti <= 1000
 * All pairs (fromi, toi) are distinct.
 * https://leetcode.com/contest/weekly-contest-194/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/
 *
 * 6
 * [[0,1,1],[1,2,1],[0,2,1],[2,3,4],[3,4,2],[3,5,2],[4,5,2]]
 */
public class FindCritical {
    public static void main(String[] args) {
        int n = 6;
        int[][] edges = new int[][]{
                {0,1,1},
                {1,2,1},
                {0,2,1},
                {2,3,4},
                {3,4,2},
                {3,5,2},
                {4,5,2}
        };
        FindCritical findCritical = new FindCritical();
        List<List<Integer>> result = findCritical.findCriticalAndPseudoCriticalEdges(n,edges);
        System.out.println("");
    }


    int N = 100 + 10;
    int f[] = new int[N];


    int find(int x) {
        if (f[x] != f[f[x]])
            f[x] = find(f[x]);
        return f[x];
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        int[][] edgesWithIndex = new int[m][4];
        for (int i = 0; i < m; i++) {
            edgesWithIndex[i][0] = edges[i][0];
            edgesWithIndex[i][1] = edges[i][1];
            edgesWithIndex[i][2] = edges[i][2];
            edgesWithIndex[i][3] = i;
        }
        edges = edgesWithIndex;
        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        for (int i = 0; i < n; ++i)
            f[i] = i;
        int best = 0;
        for (int[] e : edges) {
            int x = e[0], y = e[1], z = e[2];
            int rx = find(x), ry = find(y);
            if (rx != ry) {
                best += z;
                f[ry] = rx;
            }
        }
        List<Integer> retA = new ArrayList<>();
        List<Integer> retB = new ArrayList<>();
        boolean[] A = new boolean[m];
        for (int k = 0; k < m; ++k) {
            for (int i = 0; i < n; ++i) f[i] = i;
            int sum = 0;
            for (int i = 0; i < m; ++i) {
                if (i == k)
                    continue;
                int x = edges[i][0], y = edges[i][1], z = edges[i][2];
                int rx = find(x), ry = find(y);
                if (rx != ry) {
                    sum += z;
                    f[ry] = rx;
                }
            }
            if (sum != best) {
                retA.add(edges[k][3]);
                A[k] = true;
            }
        }
        for (int k = 0; k < m; ++k) {
            if (A[k])
                continue;
            for (int i = 0; i < n; ++i) f[i] = i;
            int sum = 0;
            int x = edges[k][0], y = edges[k][1], z = edges[k][2];
            int rx = find(x), ry = find(y);
            sum += z;
            f[ry] = rx;
            for (int i = 0; i < m; ++i) {
                if (i == k)
                    continue;
                int xTemp = edges[i][0], yTemp = edges[i][1], zTemp = edges[i][2];
                int rxTemp = find(xTemp), ryTemp = find(yTemp);

                if (rxTemp != ryTemp) {
                    sum += zTemp;
                    f[ryTemp] = rxTemp;
                }
            }
            if (sum == best)
                retB.add(edges[k][3]);
        }
        List<List<Integer>>  result = new ArrayList<List<Integer>>();
        result.add(retA);
        result.add(retB);
        return result;
    }

    /**
     * faster solution
     * @param n
     * @param edges
     * @return
     */
    public List<List<Integer>> findCriticalAndPseudoCriticalEdgesV1(int n, int[][] edges) {
        TreeMap<Integer, List<Edge>> e = new TreeMap<>();
        for (int i = 0; i < edges.length; i++) {
            e.computeIfAbsent(edges[i][2], k -> new ArrayList<>()).add(new Edge(i, edges[i][0], edges[i][1], edges[i][2]));
        }

        List<Integer> c = new ArrayList<>();
        List<Integer> pc = new ArrayList<>();

        UF uf = new UF(n);
        for (Map.Entry<Integer, List<Edge>> entry : e.entrySet()) {
            List<Edge> cOrPc = new ArrayList<>();
            Set<Integer> index = new HashSet<>();
            for (Edge edge : entry.getValue()) {
                if (!uf.isConnected(edge.getFrom(), edge.getTo())) {
                    cOrPc.add(edge);
                }
            }

            for (int i = 0; i < cOrPc.size(); i++) {
                UF ufCopy = uf.copy();
                for (int j = 0; j < cOrPc.size(); j++) {
                    if (j == i) {
                        continue;
                    }
                    ufCopy.connect(cOrPc.get(j).getFrom(), cOrPc.get(j).getTo());
                }
                if (ufCopy.isConnected(cOrPc.get(i).getFrom(), cOrPc.get(i).getTo())) {
                    pc.add(cOrPc.get(i).getIndex());
                } else {
                    c.add(cOrPc.get(i).getIndex());
                }
            }

            for (int i = 0; i < cOrPc.size(); i++) {
                uf.connect(cOrPc.get(i).getFrom(), cOrPc.get(i).getTo());
            }
        }

        return Arrays.asList(c, pc);
    }

    class Edge {
        int index;
        int from;
        int to;
        int weight;

        public Edge(int index, int from, int to, int weight) {
            this.index = index;
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getIndex() {
            return index;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }
    }

    class UF {

        private int[] root;

        public UF(int n) {
            this.root = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i;
            }
        }

        private UF(int[] root) {
            this.root = root;
        }

        public UF copy() {
            int[] copy = Arrays.copyOf(this.root, this.root.length);
            return new UF(copy);
        }

        private int root(int i) {
            if (root[i] == i) {
                return i;
            } else {
                root[i] = root[root[i]];
                return root(root[i]);
            }
        }

        public void connect(int i, int j) {
            root[root(i)] = root(j);
        }

        public boolean isConnected(int i, int j) {
            return root(i) == root(j);
        }
    }

}
