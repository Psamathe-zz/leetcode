package com.example.leetcode.challenge.June;


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
 *
 * Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops.
 * If there is no such route, output -1.
 *
 * Example 1:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph looks like this:
 *
 *
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 * Example 2:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph looks like this:
 *
 *
 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 *
 *
 * Constraints:
 *
 * The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 * The size of flights will be in range [0, n * (n - 1) / 2].
 * The format of each flight will be (src, dst, price).
 * The price of each flight will be in the range [1, 10000].
 * k is in the range of [0, n - 1].
 * There will not be any duplicated flights or self cycles.
 *
 * https://www.jianshu.com/p/8a6bcb85ce0e
 */
public class CheapestFlightsWithinKStops {
    public static void main(String[] args) {
        int n = 3;
        int[][] flights = new int[][]{
                {0,1,100},
                {1,2,100},
                {0,2,500}
        };
        int src = 0;
        int dst = 2;
        int k = 0;
        CheapestFlightsWithinKStops cheapestFlightsWithinKStops = new CheapestFlightsWithinKStops();
        int result = cheapestFlightsWithinKStops.findCheapestPrice(n,flights,src,dst,k);
        System.out.println(result);
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        HashMap<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            map.put(i, new HashMap<>());
        }

        for (int[] flight : flights) {
            map.get(flight[0]).put(flight[1], flight[2]);
        }

        PriorityQueue<Tuple> queue
                = new PriorityQueue<>((a, b) -> a.price - b.price);
        queue.offer(new Tuple(src, 0, -1));

        while (!queue.isEmpty()) {
            Tuple curr = queue.poll();

            if (curr.city == dst) {
                return curr.price;
            }

            if (curr.stops == K) {
                continue;
            }

            for (Map.Entry<Integer, Integer> next : map.get(curr.city).entrySet()) {
                queue.offer(new Tuple(next.getKey()
                        , curr.price + next.getValue(), curr.stops + 1));
            }
        }

        return -1;
    }

    class Tuple {
        int city;
        int price;
        int stops;

        public Tuple(int c, int p, int s) {
            city = c;
            price = p;
            stops = s;
        }
    }
}
