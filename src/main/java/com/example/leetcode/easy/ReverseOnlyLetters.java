package com.example.leetcode.easy;

import java.util.Stack;

public class ReverseOnlyLetters {
    public static void main(String[] args) {
        String S = "ab-cd";
        ReverseOnlyLetters reverseOnlyLetters = new ReverseOnlyLetters();
        String result = reverseOnlyLetters.reverseOnlyLetters(S);
        System.out.println(result);
    }

    /**
     * UPPERCASE_LETTER and LOWERCASE_LETTER
     * @param S
     * @return
     */
    public String reverseOnlyLetters(String S) {
        char[] chars = S.toCharArray();
        Stack<Character> stack = new Stack<>();
        int length = S.length();

        for(char c : chars){
            if(Character.getType(c)  == Character.LOWERCASE_LETTER || Character.getType(c) == Character.UPPERCASE_LETTER)
                stack.push(c);
        }
        for(int i = 0 ; i<length;i++){
            if(Character.getType(chars[i])  == Character.LOWERCASE_LETTER || Character.getType(chars[i]) == Character.UPPERCASE_LETTER){
                chars[i] = stack.pop();
            }
        }
        return  String.valueOf(chars);
    }

    /**
     * faster solution
     * @param s
     * @return
     */
    public String reverseOnlyLettersV2(String s) {
        int low = 0;
        int high = s.length() - 1;

        char[] newString = new char[s.length()];
        while (low <= high) {
            char lowChar = s.charAt(low);
            char highChar = s.charAt(high);

            if (isLetter(lowChar) && isLetter(highChar)) {
                newString[low] = highChar;
                newString[high] = lowChar;
                low++;
                high--;
            } else if (!isLetter(lowChar)) {
                newString[low] = lowChar;
                low++;
            } else if (!isLetter(highChar)) {
                newString[high] = highChar;
                high--;
            }
        }

        return new String(newString);
    }

    public boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }


    /**
     * less memory
     * @param s
     * @return
     */
    public String reverseOnlyLettersV3(String s) {


        char[] strArray = s.toCharArray();
        int j =s.length()-1;

        for(int i =0; i< strArray.length; i++){
            while(j>=i){
                if(Character.isLetter(strArray[i])) {
                    if (Character.isLetter(strArray[j])) {
                        char temp = strArray[i];
                        strArray[i] = strArray[j];
                        strArray[j] = temp;
                        j--;
                        i++;
                    }
                    else{
                        j--;
                    }
                }
                else{
                    i++;
                }
            }
        }
        String result = new String(strArray);
        return result;


    }

}
