package com.example.leetcode.medium;

/**
 * Given two strings representing two complex numbers.
 *
 * You need to return a string representing their multiplication. Note i2 = -1 according to the definition.
 *
 * Example 1:
 * Input: "1+1i", "1+1i"
 * Output: "0+2i"
 * Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
 * Example 2:
 * Input: "1+-1i", "1+-1i"
 * Output: "0+-2i"
 * Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
 * Note:
 *
 * The input strings will not have extra blank.
 * The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.
 */
public class ComplexNumberMultiplication {

    public static void main(String[] args) {
        String a = "1+-1i";
        String b = "1+-1i";
        ComplexNumberMultiplication complexNumberMultiplication = new ComplexNumberMultiplication();
        String res = complexNumberMultiplication.complexNumberMultiply(a,b);
        System.out.println(res);
    }

    public String complexNumberMultiply(String a, String b) {
        int[] aList = getNumber(a);
        int[] bList = getNumber(b);
        int first = aList[0] * bList[0];
        int second = aList[1] * bList[1];
        int third = aList[0] * bList[1] + aList[1] * bList[0];

        StringBuilder stringBuilder = new StringBuilder();
        if(first - second != 0)
            stringBuilder.append(first - second);

        stringBuilder.append(third + "i");
        return stringBuilder.toString();
    }

    public int[] getNumber(String a){
        String[] parts = a.split("\\+");
        int[] res = new int[2];
        boolean first = true;
        int i;
        for ( i = 0; i < a.length(); i++) {
            if(a.charAt(i) == '+')
                break;
        }
        res[0] = Integer.valueOf(a.substring(0,i));
        res[1] = Integer.valueOf(a.substring(i+1,a.length() - 1));
        return res;
    }
}
