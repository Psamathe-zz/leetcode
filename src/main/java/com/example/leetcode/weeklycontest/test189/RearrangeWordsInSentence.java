package com.example.leetcode.weeklycontest.test189;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a sentence text (A sentence is a string of space-separated words) in the following format:
 *
 * First letter is in upper case.
 * Each word in text are separated by a single space.
 * Your task is to rearrange the words in text such that all words are rearranged in an increasing order of their lengths.
 * If two words have the same length, arrange them in their original order.
 *
 * Return the new text following the format shown above.
 *
 *
 *
 * Example 1:
 *
 * Input: text = "Leetcode is cool"
 * Output: "Is cool leetcode"
 * Explanation: There are 3 words, "Leetcode" of length 8, "is" of length 2 and "cool" of length 4.
 * Output is ordered by length and the new first word starts with capital letter.
 * Example 2:
 *
 * Input: text = "Keep calm and code on"
 * Output: "On and keep calm code"
 * Explanation: Output is ordered as follows:
 * "On" 2 letters.
 * "and" 3 letters.
 * "keep" 4 letters in case of tie order by position in original text.
 * "calm" 4 letters.
 * "code" 4 letters.
 * Example 3:
 *
 * Input: text = "To be or not to be"
 * Output: "To be or to be not"
 */
public class RearrangeWordsInSentence {
    public static void main(String[] args) {
        String text = "Keep calm and code on";
        RearrangeWordsInSentence rearrangeWordsInSentence = new RearrangeWordsInSentence();
        String result = rearrangeWordsInSentence.arrangeWords(text);
        System.out.println(result);
    }
    public String arrangeWords(String text) {
        String[] strs = text.split(" ");
        List<String> list = Arrays.stream(strs).sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        }).map(e -> e.toLowerCase()).collect(Collectors.toList());
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0; i < list.size() - 1; i++){
            stringBuffer.append(list.get(i) + " ");
        }
        stringBuffer.append(list.get(list.size() - 1));
        if(stringBuffer.length() > 0){
            stringBuffer.replace(0,1,stringBuffer.substring(0,1).toUpperCase());
        }
        return stringBuffer.toString();
    }


    /**
     * faster solution
     * @param text
     * @return
     */
    public String arrangeWordsV1(String text) {
        String[] strs = text.split(" ");
        strs[0] = strs[0].toLowerCase();
        Arrays.sort(strs, new Comparator<String>(){
            public int compare(String a, String b) {
                return a.length() - b.length();
            }
        });

        char[] ch = strs[0].toCharArray();
        ch[0] = (char)(ch[0] - 'a' + 'A');
        strs[0] = String.valueOf(ch);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
            if (i != strs.length - 1) sb.append(" ");
        }

        return sb.toString();
    }

    /**
     * faster solution
     * @param text
     * @return
     */
    public String arrangeWordsV2(String text) {
        Map<Integer, List<String>> map = new TreeMap<>();
        int i = 0, j = 0;
        // get all words and put into TreeMap
        while (i < text.length()) {
            while (i < text.length() && text.charAt(i) != ' ') i++;
            String s = text.substring(j, i);
            if (!map.containsKey(i - j)) {
                map.put(i - j, new ArrayList<>());
                map.get(i - j).add(s);
            } else {
                map.get(i - j).add(s);
            }
            i++;
            j = i;
        }
        StringBuilder res = new StringBuilder();
        int k = 0;
        // traverse TreeMap and add to result
        for (int num : map.keySet()) {
            List<String> list = map.get(num);
            for (i = 0; i < list.size(); i++) {
                k++;
                char[] ch = list.get(i).toCharArray();
                // only the first to change to upper case
                if (k == 1) ch[0] = Character.toUpperCase(ch[0]);
                    // others keep lower case
                else ch[0] = Character.toLowerCase(ch[0]);
                res.append(ch);
                res.append(" ");
            }
        }
        // finally delete the final " "
        return res.deleteCharAt(res.length() - 1).toString();
    }


     public String arrangeWordsV3(String text) {
         String []s = text.toLowerCase().split(" ");
         Arrays.sort(s, (a,b) ->  a.length() - b.length());
         String ans = String.join(" ", s);
         return Character.toUpperCase(ans.charAt(0)) + ans.substring(1);
     }

}
