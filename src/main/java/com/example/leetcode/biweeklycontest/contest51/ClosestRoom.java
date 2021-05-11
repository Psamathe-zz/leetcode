package com.example.leetcode.biweeklycontest.contest51;


import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * There is a hotel with n rooms. The rooms are represented by a 2D integer array rooms where rooms[i] = [roomIdi, sizei] denotes that there is a room with room number roomIdi and size equal to sizei. Each roomIdi is guaranteed to be unique.
 *
 * You are also given k queries in a 2D array queries where queries[j] = [preferredj, minSizej]. The answer to the jth query is the room number id of a room such that:
 *
 * The room has a size of at least minSizej, and
 * abs(id - preferredj) is minimized, where abs(x) is the absolute value of x.
 * If there is a tie in the absolute difference, then use the room with the smallest such id. If there is no such room, the answer is -1.
 *
 * Return an array answer of length k where answer[j] contains the answer to the jth query.
 *
 *
 *
 * Example 1:
 *
 * Input: rooms = [[2,2],[1,2],[3,2]], queries = [[3,1],[3,3],[5,2]]
 * Output: [3,-1,3]
 * Explanation: The answers to the queries are as follows:
 * Query = [3,1]: Room number 3 is the closest as abs(3 - 3) = 0, and its size of 2 is at least 1. The answer is 3.
 * Query = [3,3]: There are no rooms with a size of at least 3, so the answer is -1.
 * Query = [5,2]: Room number 3 is the closest as abs(3 - 5) = 2, and its size of 2 is at least 2. The answer is 3.
 * Example 2:
 *
 * Input: rooms = [[1,4],[2,3],[3,5],[4,1],[5,2]], queries = [[2,3],[2,4],[2,5]]
 * Output: [2,1,3]
 * Explanation: The answers to the queries are as follows:
 * Query = [2,3]: Room number 2 is the closest as abs(2 - 2) = 0, and its size of 3 is at least 3. The answer is 2.
 * Query = [2,4]: Room numbers 1 and 3 both have sizes of at least 4. The answer is 1 since it is smaller.
 * Query = [2,5]: Room number 3 is the only room with a size of at least 5. The answer is 3.
 *
 *
 * Constraints:
 *
 * n == rooms.length
 * 1 <= n <= 105
 * k == queries.length
 * 1 <= k <= 104
 * 1 <= roomIdi, preferredj <= 107
 * 1 <= sizei, minSizej <= 107
 *
 */
public class ClosestRoom {
    public static void main(String[] args) {

    }

    public int[] closestRoom(int[][] rooms, int[][] queries) {
        int n = rooms.length, k = queries.length;
        Integer[] index = new Integer[k];
        for (int i = 0; i < k; i++) index[i] = i;
        Arrays.sort(rooms, (a, b) -> Integer.compare(b[1], a[1])); //Sort by decreasing order of room size
        Arrays.sort(index, (a, b) -> Integer.compare(queries[b][1], queries[a][1])); // Sort by decreasing order of query minSize
        TreeSet<Integer> roomIdsSoFar = new TreeSet<>();
        int[] ans = new int[k];
        for (int i = 0, j = 0; i < k; i++) {
            while (j < n && rooms[j][1] >= queries[index[i]][1]) { // Add id of the room which its size >= query minSize
                roomIdsSoFar.add(rooms[j++][0]);
            }
            ans[index[i]] = searchClosetRoom(roomIdsSoFar, queries[index[i]][0]);
        }
        return ans;
    }
    int searchClosetRoom(TreeSet<Integer> treeSet, int prefferedId) {
        Integer floor = treeSet.floor(prefferedId);
        Integer ceiling = treeSet.ceiling(prefferedId);
        int ansAbs = Integer.MAX_VALUE, ans = -1;
        if (ceiling != null) {
            ansAbs = Math.abs(prefferedId - ceiling);
            ans = ceiling;
        }
        if (floor != null) {
            if (ansAbs >= Math.abs(prefferedId - floor)) {
                ans = floor;
            }
        }
        return ans;
    }
}
