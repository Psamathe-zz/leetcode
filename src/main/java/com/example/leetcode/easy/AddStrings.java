package com.example.leetcode.easy;

/**
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 *
 * Note:
 *
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class AddStrings {

    public static void main(String[] args) {
        String num1 = "1";
        String num2 = "9";
        AddStrings addStrings = new AddStrings();
        String result = addStrings.addStrings(num1,num2);
        System.out.println(result);
    }
    public String addStrings(String num1, String num2) {
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars1 = new StringBuilder(num1).reverse().toString().toCharArray();
        char[] chars2 = new StringBuilder(num2).reverse().toString().toCharArray();
        int size1 = chars1.length;
        int size2 = chars2.length;
        int max = size1>size2?size1:size2;
        int next = 0;
        int value = 0;
        int temp = 0;
        int index = 0;
        while (index < max || next != 0){
            int v1 = index<size1?(chars1[index] - '0'):0;
            int v2 = index<size2?(chars2[index] - '0'):0;
            temp =  v1 + v2 + next;
            value = temp % 10;
            next = temp / 10;
            stringBuffer.append(value);
            index++;
        }
        return stringBuffer.reverse().toString();
    }

    /**
     * faster solution
     * @param num1
     * @param num2
     * @return
     */
    public String addStringsV1(String num1, String num2) {
        int nl1 = num1.length();
        int nl2 = num2.length();
        int maxLength = nl1 > nl2 ? nl1 : nl2;

        char result[] = new char[maxLength+1];
        int i = 0, carry = 0;
        for (; i < maxLength; i++) {
            int index1 = nl1 - (i+1);
            int index2 = nl2 - (i+1);
            int n1 = (index1 >= 0) ? num1.charAt(index1) - '0' : 0 ;
            int n2 = (index2 >= 0) ? num2.charAt(index2) - '0' : 0 ;

            int sum = carry + n1 + n2;
            result[maxLength-i] = (char) ((sum % 10) + '0');
            carry = sum/10;
        }
        result[0] = (char) (carry + '0');
        return carry != 0 ? new String(result) : new String(result, 1, maxLength);
    }
    /**
     * less memory
     * @param num1
     * @param num2
     * @return
     */
    public String addStringsV3(String num1, String num2) {
        int idx1 = num1.length() - 1;
        int idx2 = num2.length() - 1;
        int add = 0;
        StringBuilder sb = new StringBuilder();
        while (idx1 >= 0 || idx2 >= 0) {
            int n1 = idx1 >= 0 ? num1.charAt(idx1--) - '0' : 0;
            int n2 = idx2 >= 0 ? num2.charAt(idx2--) - '0' : 0;
            int sum = n1 + n2 + add;
            sb.append((char)(sum%10 + '0'));
            add = sum/10;
        }
        if (add != 0) {
            sb.append((char)(add + '0'));
        }
        return sb.reverse().toString();
    }
}
