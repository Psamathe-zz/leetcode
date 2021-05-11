package com.example.leetcode.challenge.test2021.may.week1;


import java.util.HashMap;
import java.util.Map;

/**
 * Design a special dictionary which has some words and allows you to search the words in it by a prefix and a suffix.
 *
 * Implement the WordFilter class:
 *
 * WordFilter(string[] words) Initializes the object with the words in the dictionary.
 * f(string prefix, string suffix) Returns the index of the word in the dictionary which has the prefix prefix and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.
 *
 *
 * Example 1:
 *
 * Input
 * ["WordFilter", "f"]
 * [[["apple"]], ["a", "e"]]
 * Output
 * [null, 0]
 *
 * Explanation
 * WordFilter wordFilter = new WordFilter(["apple"]);
 * wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 15000
 * 1 <= words[i].length <= 10
 * 1 <= prefix.length, suffix.length <= 10
 * words[i], prefix and suffix consist of lower-case English letters only.
 * At most 15000 calls will be made to the function f.
 */
public class WordFilter {
    public static void main(String[] args) {

    }

    String[] words;
    public WordFilter(String[] words) {
        this.words = words;
    }

    public int f(String prefix, String suffix) {
        for (int i = words.length - 1; i >= 0 ; i--) {
            if(words[i].startsWith(prefix) && words[i].endsWith(suffix)){
                return i;
            }
        }
        return -1;
    }

    Map<String,Integer> map;
    public WordFilter(String[] words,int ix) {
        map = new HashMap<>();
        for (int k = 0; k < words.length; ++k) {
            for (int i = 0; i <= words[k].length(); ++i) {
                for (int j = 0; j <= words[k].length(); ++j) {
                    map.put(words[k].substring(0, i) + "#" + words[k].substring(words[k].length() - j),k);
                }
            }
        }
    }

    public int fV2(String prefix, String suffix) {
        return (map.containsKey(prefix + "#" + suffix)) ?map.get(prefix + "#" + suffix) : -1;
    }
}
