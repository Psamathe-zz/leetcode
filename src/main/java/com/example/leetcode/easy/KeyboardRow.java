package com.example.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.
 *
 * Example:
 *
 * Input: ["Hello", "Alaska", "Dad", "Peace"]
 * Output: ["Alaska", "Dad"]
 *
 *
 * Note:
 *
 * You may use one character in the keyboard more than once.
 * You may assume the input string will only contain letters of alphabet.
 */
public class KeyboardRow {

    public static void main(String[] args) {

        String[] words = new String[]{"Hello", "Alaska", "Dad", "Peace"};
        KeyboardRow keyboardRow = new KeyboardRow();
        String[] result = keyboardRow.findWords(words);
        for (String var:result){
            System.out.println(var);
        }
    }


    public String[] findWords(String[] words) {
        return Arrays.stream(words).filter(e-> isWhatIWant(e)).toArray(String[]::new);
    }

    public boolean isWhatIWant(String input){
        String str1 = "qwertyuiop";
        String str2 = "asdfghjkl";
        String str3 = "zxcvbnm";
        int index = 0;
        int temp = 0;
        for(char var : input.toCharArray()){
            if(str1.contains(String.valueOf(var))){
                temp = 1;
            } else if (str2.contains(String.valueOf(var))){
                temp = 2;
            } else if (str3.contains(String.valueOf(var))){
                temp = 3;
            }
            if(index == 0)
                index = temp;
            if( index != temp )
                return false;
        }
        return true;
    }


    /**
     * less memory
     * @param words
     * @return
     */
    public String[] findWordsV2(String[] words) {

        return Stream.of(words).filter(s -> s.toLowerCase().matches("[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*")).toArray(String[]::new);

    }


    /**
     * faster solution
     * @param words
     * @return
     */
    public String[] findWordsV3(String[] words) {
        List<String> list = new ArrayList<>();
        int[] loc = {2, 1, 1, 2, 3, 2, 2, 2, 3, 2, 2, 2, 1, 1, 3, 3, 3, 3, 2, 3, 3, 1, 3, 1, 3, 1};
        for (String word : words) {
            int cur = -1;
            boolean mark = true;
            for (char c : word.toLowerCase().toCharArray()) {
                if (cur != -1 && cur != loc[c - 'a']) {
                    mark = false;
                    break;
                }
                if (cur == -1) {
                    cur = loc[c - 'a'];
                }
            }
            if (mark) {
                list.add(word);
            }
        }
        String[] res = new String[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
