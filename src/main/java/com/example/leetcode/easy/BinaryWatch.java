package com.example.leetcode.easy;

import java.util.*;

/**
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
 *
 * Each LED represents a zero or one, with the least significant bit on the right.
 *
 *
 * For example, the above binary watch reads "3:25".
 *
 * Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.
 *
 * Example:
 *
 * Input: n = 1
 * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * Note:
 * The order of output does not matter.
 * The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 * The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 */
public class BinaryWatch {
    public static void main(String[] args) {
        int num = 1;
        BinaryWatch binaryWatch = new BinaryWatch();
        List<String> result = binaryWatch.readBinaryWatch(num);
        result.forEach(e -> System.out.println(e));
    }

    public List<String> readBinaryWatch(int num) {
        List<String> result = new ArrayList<>();
        Map<Integer,List<String>> hourMap = new HashMap<>();
        Map<Integer,List<String>> minuteMap = new HashMap<>();
        List<String> hourList;
        List<String> minuteList;
        for(int numHour = 0; numHour <= 4; numHour++){
            int numMinute = num - numHour;
            if(numMinute>= 0 && numMinute <= 6){
                //hour
                if(!hourMap.containsKey(numHour)){
                    hourList = new ArrayList<>();
                    for(int i=0;i<12;i++){
                        if(Arrays.stream(Integer.toBinaryString(i).split("")).filter(e->e.equals("1")).count() == numHour){
                            hourList.add(String.valueOf(i));
                        }
                    }
                } else {
                    hourList = hourMap.get(numHour);
                }

                //minute
                if(!minuteMap.containsKey(numMinute)){
                    minuteList = new ArrayList<>();
                    for(int i=0;i<60;i++){
                        if(Arrays.stream(Integer.toBinaryString(i).split("")).filter(e->e.equals("1")).count() == numMinute){
                            minuteList.add(("00" + i).substring(String.valueOf(i).length()));
                        }
                    }
                } else {
                    minuteList = minuteMap.get(numMinute);
                }

                for(String hour:hourList){
                    for(String minute:minuteList){
                        result.add(hour+":"+minute);
                    }
                }
            }
        }

        return result;
    }


    /**
     * faster solution
     * @param nm
     * @return
     */
    public int countLEDs(int nm){
        int temp = nm;
        int score = 0;
        while(temp > 0){
            if(temp%2!=0){
                score++;
            }
            temp/=2;
        }
        return score;
    }
    public List<String> readBinaryWatchV2(int num) {
        List<String>output = new ArrayList<>();
        //store the minute hand in the array
        int []buffer = new int[60];
        for(int i=0;i<60;i++){
            buffer[i] = countLEDs(i);
        }
        for(int i=0;i<12;i++){
            for(int j=0;j<60;j++){
                if(buffer[i] + buffer[j] == num){
                    StringBuilder sb = new StringBuilder();
                    sb.append(i).append((j<=9)?":0":":").append(j);
                    output.add(sb.toString());
                }
            }
        }
        return output;
    }


    /**
     * less memory
     */
    int[] possibleHours = {8, 4, 2, 1};
    int[] possibleMins = {32, 16, 8, 4, 2, 1};
    public List<String> readBinaryWatchV3(int num) {
        List<String> res = new ArrayList<>();

        //generate all possible combinations given nums
        for(int i= 0; i <= num; i++) {
            // get all possible hours
            List<Integer> hours = getCombinations(i, possibleHours, 12);

            // get all possible mins with remaining  light up count
            List<Integer> mins = getCombinations(num - i, possibleMins, 60);


            // for every valid hour
            for(int hour: hours) {
                //for every valid min
                for(int min: mins) {
                    // add together and add to result
                    res.add(convertTime(hour, min));
                }
            }


        }
        return res;
    }

    private List<Integer> getCombinations(int numOfLights, int[] possibleNums, int limit) {
        List<Integer> combinations = new ArrayList<>();
        generateCombinations(numOfLights, 0, 0, possibleNums, limit, combinations);
        return combinations;
    }

    private void generateCombinations(int numOfLights, int sum, int idx, int[] possibleNums, int limit, List<Integer> res) {
        if(sum >= limit) return;
        if(numOfLights == 0) {
            res.add(sum);
            return;
        }


        // get all combinations
        for(int i = idx; i < possibleNums.length; i++) {
            generateCombinations(numOfLights - 1, sum + possibleNums[i], i + 1, possibleNums, limit, res);
        }
    }



    private String convertTime(int hours, int mins) {
        StringBuilder sb = new StringBuilder();

        // hour formatting
        if(hours == 0) sb.append("0");
        else sb.append(hours);
        sb.append(":");

        // min formatting
        if(mins < 10) sb.append("0");
        sb.append(mins);

        return sb.toString();
    }
}
