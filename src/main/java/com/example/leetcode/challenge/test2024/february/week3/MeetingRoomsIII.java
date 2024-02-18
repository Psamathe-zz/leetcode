package com.example.leetcode.challenge.test2024.february.week3;

import java.util.*;
import java.util.stream.Collectors;

/**
 * You are given an integer n. There are n rooms numbered from 0 to n - 1.
 * <p>
 * You are given a 2D integer array meetings where meetings[i] = [starti, endi] means that a meeting will be held during the half-closed time interval [starti, endi). All the values of starti are unique.
 * <p>
 * Meetings are allocated to rooms in the following manner:
 * <p>
 * Each meeting will take place in the unused room with the lowest number.
 * If there are no available rooms, the meeting will be delayed until a room becomes free. The delayed meeting should have the same duration as the original meeting.
 * When a room becomes unused, meetings that have an earlier original start time should be given the room.
 * Return the number of the room that held the most meetings. If there are multiple rooms, return the room with the lowest number.
 * <p>
 * A half-closed interval [a, b) is the interval between a and b including a and not including b.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]]
 * Output: 0
 * Explanation:
 * - At time 0, both rooms are not being used. The first meeting starts in room 0.
 * - At time 1, only room 1 is not being used. The second meeting starts in room 1.
 * - At time 2, both rooms are being used. The third meeting is delayed.
 * - At time 3, both rooms are being used. The fourth meeting is delayed.
 * - At time 5, the meeting in room 1 finishes. The third meeting starts in room 1 for the time period [5,10).
 * - At time 10, the meetings in both rooms finish. The fourth meeting starts in room 0 for the time period [10,11).
 * Both rooms 0 and 1 held 2 meetings, so we return 0.
 * Example 2:
 * <p>
 * Input: n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
 * Output: 1
 * Explanation:
 * - At time 1, all three rooms are not being used. The first meeting starts in room 0.
 * - At time 2, rooms 1 and 2 are not being used. The second meeting starts in room 1.
 * - At time 3, only room 2 is not being used. The third meeting starts in room 2.
 * - At time 4, all three rooms are being used. The fourth meeting is delayed.
 * - At time 5, the meeting in room 2 finishes. The fourth meeting starts in room 2 for the time period [5,10).
 * - At time 6, all three rooms are being used. The fifth meeting is delayed.
 * - At time 10, the meetings in rooms 1 and 2 finish. The fifth meeting starts in room 1 for the time period [10,12).
 * Room 0 held 1 meeting while rooms 1 and 2 each held 2 meetings, so we return 1.
 */
public class MeetingRoomsIII {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/xianzhon/p/17416861.html
     * @param n
     * @param meetings
     * @return
     */
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        // track available meeting rooms
        TreeSet<Integer> availableRooms = new TreeSet<>();
        int[] bookCounts = new int[n];
        for(int i = 0; i < n; i++) {
            availableRooms.add(i);
        }

        // track the end time of each live meetings (endTime, roomIdx)
        PriorityQueue<long[]> minHeap = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]); // sort by end time asc
            return Long.compare(a[1], b[1]); // sort by roomIdx asc
        });

        // arrangement
        for(int[] meeting : meetings) {
            long start = meeting[0], end = meeting[1];
            // check if there is any meeting ending
            while (!minHeap.isEmpty() && minHeap.peek()[0] <= start) {
                int roomId = (int)minHeap.poll()[1];
                availableRooms.add(roomId);
            }
            // delay meeting, until first meeting room available (i.e. end time shortest)
            if (availableRooms.isEmpty()) {
                long[] cur = minHeap.poll();
                availableRooms.add((int)cur[1]);
                // delay the meeting
                end = (end - start) + cur[0];
                start = cur[0];
            }

            int roomId = availableRooms.first();
            availableRooms.remove(roomId);
            bookCounts[roomId]++;
            minHeap.offer(new long[] {end, roomId});
        }
        // find most booked meeting room
        int ans = 0;
        for(int i = 1; i < n; i++) {
            if (bookCounts[i] > bookCounts[ans]) {
                ans = i;
            }
        }
        return ans;

    }
}
