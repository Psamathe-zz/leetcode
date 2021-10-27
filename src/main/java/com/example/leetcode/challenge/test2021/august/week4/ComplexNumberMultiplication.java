package com.example.leetcode.challenge.test2021.august.week4;

/**
 * A complex number can be represented as a string on the form "real+imaginaryi" where:
 *
 * real is the real part and is an integer in the range [-100, 100].
 * imaginary is the imaginary part and is an integer in the range [-100, 100].
 * i2 == -1.
 * Given two complex numbers num1 and num2 as strings, return a string of the complex number that represents their multiplications.
 *
 *
 *
 * Example 1:
 *
 * Input: num1 = "1+1i", num2 = "1+1i"
 * Output: "0+2i"
 * Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
 * Example 2:
 *
 * Input: num1 = "1+-1i", num2 = "1+-1i"
 * Output: "0+-2i"
 * Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
 *
 *
 * Constraints:
 */
public class ComplexNumberMultiplication {
    public static void main(String[] args) {
        ComplexNumberMultiplication complexNumberMultiplication = new ComplexNumberMultiplication();
        String res = complexNumberMultiplication.complexNumberMultiply("1+1i","1+1i");
        System.out.println(res);
    }

    public String complexNumberMultiply(String num1, String num2) {
        String[] strings1 = num1.split("\\+");
        String[] strings2 = num2.split("\\+");
        int val1 = Integer.valueOf(strings1[0]);
        int val2 = Integer.valueOf(strings1[1].substring(0,strings1[1].length() - 1));
        int val3 = Integer.valueOf(strings2[0]);
        int val4 = Integer.valueOf(strings2[1].substring(0,strings2[1].length() - 1));
        int real = val1 * val3 - val2 * val4;
        int imaginary = val1 * val4 + val2 * val3;
        return real + "+" + (imaginary + "i");
    }
}
