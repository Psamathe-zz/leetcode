package com.example.leetcode.challenge.test2020.December.week3;

/**
 * An encoded string S is given.  To find and write the decoded string to a tape, the encoded string is read one character at a time and the following steps are taken:
 *
 * If the character read is a letter, that letter is written onto the tape.
 * If the character read is a digit (say d), the entire current tape is repeatedly written d-1 more times in total.
 * Now for some encoded string S, and an index K, find and return the K-th letter (1 indexed) in the decoded string.
 *
 *
 *
 * Example 1:
 *
 * Input: S = "leet2code3", K = 10
 * Output: "o"
 * Explanation:
 * The decoded string is "leetleetcodeleetleetcodeleetleetcode".
 * The 10th letter in the string is "o".
 * Example 2:
 *
 * Input: S = "ha22", K = 5
 * Output: "h"
 * Explanation:
 * The decoded string is "hahahaha".  The 5th letter is "h".
 * Example 3:
 *
 * Input: S = "a2345678999999999999999", K = 1
 * Output: "a"
 * Explanation:
 * The decoded string is "a" repeated 8301530446056247680 times.  The 1st letter is "a".
 *
 *
 * Constraints:
 *
 * 2 <= S.length <= 100
 * S will only contain lowercase letters and digits 2 through 9.
 * S starts with a letter.
 * 1 <= K <= 10^9
 * It's guaranteed that K is less than or equal to the length of the decoded string.
 * The decoded string is guaranteed to have less than 2^63 letters.
 * "a2b3c4d5e6f7g8h9"
 * 10
 */
public class DecodedStringIndex {
    public static void main(String[] args) {
        DecodedStringIndex decodedStringIndex = new DecodedStringIndex();
        decodedStringIndex.decodeAtIndex("a2b3c4d5e6f7g8h9",10);
    }

    public String decodeAtIndex(String S, int K) {
        long count = 0;
        int len = S.length();
        for(int i = 0; i < len; i++) {
            char c = S.charAt(i);
            if (c >= 'a' && c <= 'z') {
                count++;
                if (count == K) {
                    return String.valueOf(c);
                }
            } else {
                long count2 = count * (c - '0');
                if (count2 >= K) {
                    long mod = K % count;
                    if (mod == 0) {
                        mod = count;
                    }
                    return decodeAtIndex(S, (int)mod);
                } else {
                    count = count2;
                }
            }
        }

        return "";
    }


    /*
        If we have a decoded string like appleappleappleappleappleapple and an index like K = 24, the answer is the same if K = 4.
        In general, when a decoded string is equal to some word with size length repeated some number of times (such as apple with size = 5           repeated 6 times), the answer is the same for the index K as it is for the index K % size
    */
    public String decodeAtIndexV1(String S, int K) {
        // Size of the decoded string
        long size = 0;

        // length of the string
        int N = S.length();

        // Calculate the length of the decoded string
        for (int i = 0; i < N; ++i) {
            // Get the character at the ith position
            char c = S.charAt(i);

            // If it is a digit then multiply previous size by the digit
            if (Character.isDigit(c))
                size *= c - '0';
            else
                size++;
        }

        // Start traversing from the right
        for (int i = N-1; i >= 0; --i) {
            // If the
            char c = S.charAt(i);

            K %= size;
            if (K == 0 && Character.isLetter(c))
                return Character.toString(c);

            if (Character.isDigit(c)) {
                size /= c - '0';

            }
            else {
                size--;
            }
        }

        throw null;
    }
}
