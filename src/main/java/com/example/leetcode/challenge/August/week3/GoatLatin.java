package com.example.leetcode.challenge.August.week3;

import java.util.ArrayList;
import java.util.List;

/**
 * A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.
 *
 * We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)
 *
 * The rules of Goat Latin are as follows:
 *
 * If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
 * For example, the word 'apple' becomes 'applema'.
 *
 * If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
 * For example, the word "goat" becomes "oatgma".
 *
 * Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
 * For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
 * Return the final sentence representing the conversion from S to Goat Latin.
 *
 *
 *
 * Example 1:
 *
 * Input: "I speak Goat Latin"
 * Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
 * Example 2:
 *
 * Input: "The quick brown fox jumped over the lazy dog"
 * Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 *
 *
 * Notes:
 *
 * S contains only uppercase, lowercase and spaces. Exactly one space between each word.
 * 1 <= S.length <= 150.
 */
public class GoatLatin {

    public static void main(String[] args) {

    }

    List<Character> vowel = new ArrayList<>();
    public String toGoatLatin(String S) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] strings = S.split(" ");
        vowel.add('a');
        vowel.add('i');
        vowel.add('e');
        vowel.add('o');
        vowel.add('u');
        vowel.add('A');
        vowel.add('I');
        vowel.add('E');
        vowel.add('O');
        vowel.add('U');
        for (int i = 0; i < strings.length; i++) {
            stringBuilder.append(reset(strings[i],i+1));
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }

    public String reset(String str,int index){
        StringBuilder stringBuilder = new StringBuilder();
        if(vowel.contains(str.charAt(0))){
            stringBuilder.append(str);
        } else {
            stringBuilder.append(str.substring(1,str.length()));
            stringBuilder.append(str.charAt(0));
        }
        stringBuilder.append("ma");
        for (int i = 0; i < index; i++) {
            stringBuilder.append('a');
        }
        return stringBuilder.toString();
    }
}
