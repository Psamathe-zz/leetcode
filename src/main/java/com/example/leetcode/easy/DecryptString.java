package com.example.leetcode.easy;

/**
 * Given a string s formed by digits ('0' - '9') and '#' . We want to map s to English lowercase characters as follows:
 *
 * Characters ('a' to 'i') are represented by ('1' to '9') respectively.
 * Characters ('j' to 'z') are represented by ('10#' to '26#') respectively.
 * Return the string formed after mapping.
 *
 * It's guaranteed that a unique mapping will always exist.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "10#11#12"
 * Output: "jkab"
 * Explanation: "j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".
 * Example 2:
 *
 * Input: s = "1326#"
 * Output: "acz"
 * Example 3:
 *
 * Input: s = "25#"
 * Output: "y"
 * Example 4:
 *
 * Input: s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"
 * Output: "abcdefghijklmnopqrstuvwxyz"
 *
 */
public class DecryptString {
    public static void main(String[] args) {
        String s = "10#11#12";
        DecryptString decryptString = new DecryptString();
        String result = decryptString.freqAlphabets(s);
        System.out.println(result);
    }

    public String freqAlphabets(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBufferTemp = new StringBuffer();
        char[] chars = s.toCharArray();
        int length = chars.length;
        boolean bigMode = false;
        int indexBig = 0;
        int temp;
        for (int i = length - 1; i >= 0 ; i--) {
            if(chars[i] == '#'){
                stringBufferTemp.delete(0,stringBufferTemp.length());
                indexBig = 2;
                bigMode = true;
            } else if(bigMode){
                if(indexBig ==2) {
                    stringBufferTemp.append(chars[i]);
                    indexBig--;
                } else {
                    stringBufferTemp.append(chars[i]);
                    temp = Integer.valueOf(stringBufferTemp.reverse().toString());
                    stringBuffer.append((char) (temp - 1 + 'a'));
                    bigMode = false;
                }
            } else {
                temp = Integer.valueOf(chars[i]);
                stringBuffer.append((char) ('a' + temp - '1'));
            }
        }

        return stringBuffer.reverse().toString();
    }

    /**
     * faster solution
     * @param s
     * @return
     */
    public String freqAlphabetsV1(String s) {
        if(s == null) return "";
        int len = s.length();
        char buf = 0;
        StringBuilder result = new StringBuilder();

        for(int i = len - 1; i >= 0; i--){
            buf = s.charAt(i);
            if(buf == '#'){
                buf = (char)('a' - '1' + s.charAt(i - 1));
                if(s.charAt(i - 2) == '1'){ // - 1X
                    buf = (char)(buf + 10);
                }
                else{ // s.charAt(i) == '2' - 2X
                    buf = (char)(buf + 20);
                }
                i -= 2;
            }
            else{
                buf = (char)(s.charAt(i) + 48);
            }
            result.insert(0,buf);
        }
        return result.toString();
    }
}
