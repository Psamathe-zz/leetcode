package com.example.leetcode.easy;

/**
 * You are given a string representing an attendance record for a student. The record only contains the following three characters:
 * 'A' : Absent.
 * 'L' : Late.
 * 'P' : Present.
 * A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
 *
 * You need to return whether the student could be rewarded according to his attendance record.
 *
 * Example 1:
 * Input: "PPALLP"
 * Output: True
 * Example 2:
 * Input: "PPALLL"
 * Output: False
 */
public class StudentAttendanceRecordI {
    public static void main(String[] args) {
        String s = "PPALLP";
        StudentAttendanceRecordI studentAttendanceRecordI = new StudentAttendanceRecordI();
        boolean result = studentAttendanceRecordI.checkRecord(s);
        System.out.println(result);
    }

    public boolean checkRecord(String s) {
        int absendCount = 0;
        int lateCount = 0;
        for(char c : s.toCharArray()){
            if(c == 'A') {
                absendCount++;
                lateCount = 0;
            } else if (c == 'L'){
                lateCount++;
            } else {
                lateCount = 0;
            }
            if(absendCount > 1 || lateCount > 2)
                return false;
        }
        return true;
    }

    /**
     * less memeory
     * @param s
     * @return
     */
    public boolean checkRecordV1(String s) {
        int countA = 0;
        int countL = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                countA++;
            }
        }
        for (int i = 0; i < s.length()-2; i++) {

            if (s.charAt(i) == 'L' && s.charAt(i+1) == 'L' && s.charAt(i+2) == 'L') {
                countL++;
            }
        }
        if (countA > 1 || countL >=1) {
            return false;
        }
        return true;
    }


}
