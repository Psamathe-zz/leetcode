package com.example.leetcode.easy;

/**
 * Given a word, you need to judge whether the usage of capitals in it is right or not.
 *
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 *
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital, like "Google".
 * Otherwise, we define that this word doesn't use capitals in a right way.
 *
 *
 * Example 1:
 *
 * Input: "USA"
 * Output: True
 *
 *
 * Example 2:
 *
 * Input: "FlaG"
 * Output: False
 *
 *
 * Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.
 */
public class DetectCapital {
    public static void main(String[] args) {

    }

    public boolean detectCapitalUse(String word) {
        int length = word.length();

        char[] chars = word.toCharArray();
        if(chars[0] >= 'a' && chars[0] <= 'z'){
            for (int i = 1; i < length; i++) {
                if(chars[i] >= 'A' && chars[i] <= 'Z'){
                    return false;
                }
            }
            return true;
        } else {
            if(length > 1){
                if(chars[1] >= 'a' && chars[1] <= 'z'){
                    for (int i = 2; i < length; i++) {
                        if(chars[i] >= 'A' && chars[i] <= 'Z'){
                            return false;
                        }
                    }
                    return true;
                } else {
                    for (int i = 2; i < length; i++) {
                        if(chars[i] >= 'a' && chars[i] <= 'z'){
                            return false;
                        }
                    }
                    return true;
                }

            } else {
                return true;
            }
        }

    }

    /**
     * faster solution
     * @param word
     * @return
     */
    public boolean detectCapitalUseV1(String word) {
        int lastCap = -1;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(c >= 'A' && c <= 'Z') {
                if(lastCap + 1 == i) {
                    lastCap++;
                } else {
                    return false;
                }
            }
        }

        if(lastCap == -1 || lastCap == word.length() - 1 || lastCap == 0) {
            return true;
        }

        return false;
    }

    public boolean detectCapitalUseV2(String word) {
        if(word.length() == 1)
            return true;
        for(int i =1;i<word.length();i++){
            if((i !=1 &&(word.charAt(i) >=97 && word.charAt(i-1) < 97) ||  word.charAt(i-1) >=97 && word.charAt(i) < 97))
                return false;
        }
        return true;
    }
}
