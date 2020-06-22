package com.example.leetcode.medium;

import java.util.Arrays;

/**
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.
 *
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 *
 * The . character does not represent a decimal point and is used to separate number sequences.
 *
 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
 *
 * You may assume the default revision number for each level of a version number to be 0. For example, version number 3.4 has a revision number of 3 and 4 for its first and second level revision number. Its third and fourth level revision number are both 0.
 *
 *
 *
 * Example 1:
 *
 * Input: version1 = "0.1", version2 = "1.1"
 * Output: -1
 * Example 2:
 *
 * Input: version1 = "1.0.1", version2 = "1"
 * Output: 1
 * Example 3:
 *
 * Input: version1 = "7.5.2.4", version2 = "7.5.3"
 * Output: -1
 * Example 4:
 *
 * Input: version1 = "1.01", version2 = "1.001"
 * Output: 0
 * Explanation: Ignoring leading zeroes, both “01” and “001" represent the same number “1”
 * Example 5:
 *
 * Input: version1 = "1.0", version2 = "1.0.0"
 * Output: 0
 * Explanation: The first version number does not have a third level revision number, which means its third level revision number is default to "0"
 *
 *
 * Note:
 *
 * Version strings are composed of numeric strings separated by dots . and this numeric strings may have leading zeroes.
 * Version strings do not start or end with dots, and they will not be two consecutive dots.
 */
public class CompareVersionNumbers {

    public static void main(String[] args) {
        String version1 = "1.0";
        String version2 = "1";

        CompareVersionNumbers compareVersionNumbers = new CompareVersionNumbers();
        int result = compareVersionNumbers.compareVersion(version1,version2);
        System.out.println(result);
    }

    public int compareVersion(String version1, String version2) {

        String[] version1list = removeZero(version1.split("\\."));
        String[] version2list = removeZero(version2.split("\\."));
        int index = 0;
        while (true){
            if(index == version2list.length && index < version1list.length)
                return 1;
            if(index == version1list.length && index < version2list.length)
                return -1;
            if(index == version1list.length && index == version2list.length)
                return 0;
            if(Integer.valueOf(version1list[index]) > Integer.valueOf(version2list[index])){
                return 1;
            } else if(Integer.valueOf(version1list[index]) < Integer.valueOf(version2list[index])){
                return -1;
            } else{
                index++;
            }
        }
    }

    public String[] removeZero(String[] input){
        int i;
        for(i=input.length-1; i>=0;i--){
            if(Integer.valueOf(input[i]) != 0){
                break;
            }
        }
        return Arrays.copyOfRange(input,0,i+1);
    }


    /**
     * less memory
     * @param version1
     * @param version2
     * @return
     */

    public int compareVersionV2(String version1, String version2) {
        int temp1 = 0,temp2 = 0;
        int len1 = version1.length(),len2 = version2.length();
        int i = 0,j = 0;
        while(i<len1 || j<len2) {
            temp1 = 0;
            temp2 = 0;
            while(i<len1 && version1.charAt(i) != '.') {
                temp1 = temp1*10 + version1.charAt(i++)-'0';

            }
            while(j<len2 && version2.charAt(j) != '.') {
                temp2 = temp2*10 + version2.charAt(j++)-'0';

            }
            if(temp1>temp2) return 1;
            else if(temp1<temp2) return -1;
            else {
                i++;
                j++;

            }

        }
        return 0;
    }
}
