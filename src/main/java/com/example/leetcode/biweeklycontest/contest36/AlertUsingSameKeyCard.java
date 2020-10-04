package com.example.leetcode.biweeklycontest.contest36;

import com.example.leetcode.sometest.A;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Leetcode company workers use key-cards to unlock office doors. Each time a worker uses their key-card, the security system saves the worker's name and the time when it was used. The system emits an alert if any worker uses the key-card three or more times in a one-hour period.
 *
 * You are given a list of strings keyName and keyTime where [keyName[i], keyTime[i]] corresponds to a person's name and the time when their key-card was used in a single day.
 *
 * Access times are given in the 24-hour time format "HH:MM", such as "23:51" and "09:49".
 *
 * Return a list of unique worker names who received an alert for frequent keycard use. Sort the names in ascending order alphabetically.
 *
 * Notice that "10:00" - "11:00" is considered to be within a one-hour period, while "23:51" - "00:10" is not considered to be within a one-hour period.
 *
 *
 *
 * Example 1:
 *
 * Input: keyName = ["daniel","daniel","daniel","luis","luis","luis","luis"], keyTime = ["10:00","10:40","11:00","09:00","11:00","13:00","15:00"]
 * Output: ["daniel"]
 * Explanation: "daniel" used the keycard 3 times in a one-hour period ("10:00","10:40", "11:00").
 * Example 2:
 *
 * Input: keyName = ["alice","alice","alice","bob","bob","bob","bob"], keyTime = ["12:01","12:00","18:00","21:00","21:20","21:30","23:00"]
 * Output: ["bob"]
 * Explanation: "bob" used the keycard 3 times in a one-hour period ("21:00","21:20", "21:30").
 * Example 3:
 *
 * Input: keyName = ["john","john","john"], keyTime = ["23:58","23:59","00:01"]
 * Output: []
 * Example 4:
 *
 * Input: keyName = ["leslie","leslie","leslie","clare","clare","clare","clare"], keyTime = ["13:00","13:20","14:00","18:00","18:51","19:30","19:49"]
 * Output: ["clare","leslie"]
 *
 *
 * Constraints:
 *
 * 1 <= keyName.length, keyTime.length <= 105
 * keyName.length == keyTime.length
 * keyTime are in the format "HH:MM".
 * [keyName[i], keyTime[i]] is unique.
 * 1 <= keyName[i].length <= 10
 * keyName[i] contains only lowercase English letters.
 * ["a","a","a","a","b","b","b","b","b","b","c","c","c","c"]
 * ["01:35","08:43","20:49","00:01","17:44","02:50","18:48","22:27","14:12","18:00","12:38","20:40","03:59","22:24"]
 *
 * ["a","a","a"]
 * ["12:57","13:35","13:18"]
 * ["a","a","a","a","a","a","b","b","b","b","b"]
 * ["23:27","03:14","12:57","13:35","13:18","21:58","22:39","10:49","19:37","14:14","10:41"]
 */
public class AlertUsingSameKeyCard {
    public static void main(String[] args) {
        String[] name = new String[]{"b","b","b","b","b","b"};
        String[] time = new String[]{"17:44","02:50","18:48","22:27","14:12","18:00","12:38"};
        AlertUsingSameKeyCard alertUsingSameKeyCard = new AlertUsingSameKeyCard();
        alertUsingSameKeyCard.alertNames(name,time);
    }

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Set<String> res = new HashSet<>();
        Map<String,PriorityQueue<Integer>> count = new HashMap<>();
        int length = keyName.length;
        String name;
        int time;
        PriorityQueue<Integer> temp;
        for (int i = 0; i < length; i++) {
            name = keyName[i];
            time = convert(keyTime[i]);
            temp = count.getOrDefault(name,new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            }));
            temp.add(time);
            count.put(name,temp);
            if(res.contains(name) || toAlert(time,temp)){
                res.add(name);
            }
        }

        return res.stream().sorted().collect(Collectors.toList());
    }

    public int convert(String keyTime){
        String[] sp = keyTime.split(":");
        int res = Integer.parseInt(sp[0]) * 60 + Integer.parseInt(sp[1]);
        return res;
    }

    public boolean toAlert(int time,PriorityQueue<Integer> list){
        int va = time % 60;
        int temp = time / 60;
        int up = Math.max((temp - 1)*60+va,0) ;
        int down = Math.min((temp + 1)*60+va,24*60);
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(Integer t : list){
            if(t >= up && t <= down){
                queue.add(t);
                count++;
                while (t - queue.peek() > 60){
                    queue.poll();
                    count--;
                }
            }
            if(count >= 3)
                return true;
        }
        return false;
    }

}
