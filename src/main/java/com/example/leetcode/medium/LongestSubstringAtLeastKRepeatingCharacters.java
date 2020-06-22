package com.example.leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.
 *
 * Example 1:
 *
 * Input:
 * s = "aaabb", k = 3
 *
 * Output:
 * 3
 *
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 *
 * Input:
 * s = "ababbc", k = 2
 *
 * Output:
 * 5
 *
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 *
 * Input
 * "bbaaacbd"
 * 3
 * Output
 * 0
 * Expected
 * 3
 */
public class LongestSubstringAtLeastKRepeatingCharacters {
    public static void main(String[] args) {
        String  s = "bbaaacbd";
        int k = 3;
        LongestSubstringAtLeastKRepeatingCharacters longestSubstringAtLeastKRepeatingCharacters = new LongestSubstringAtLeastKRepeatingCharacters();
        int result = longestSubstringAtLeastKRepeatingCharacters.longestSubstring(s,k);
        System.out.println(result);
    }

    public int longestSubstring(String s, int k) {
        int result = 0;
        int[] times = new int[26];
        char[] chars = s.toCharArray();
        for(char c: chars){
            times[c-'a']++;
        }
        if(Arrays.stream(times).filter( e-> e!=0 && e < k).sum() == 0)
            return s.length();

        int index = 0;
        for(int i = 0;i < chars.length;i++){
            if(times[chars[i]-'a'] < k ){
                int temp = longestSubstring(s.substring(i - index,i),k);
                if(result < temp)
                    result = temp;
                index = 0;
            } else {
                index++;
            }

        }
        if(index != 0){
            int temp = longestSubstring(s.substring(chars.length - index,chars.length),k);
            if(result < temp)
                result = temp;
        }
        return result;
    }


    /**
     * faster solution
     * @param s
     * @param k
     * @return
     */
    public int longestSubstringV2(String s, int k) {
        Map<Character,Integer> map = new HashMap();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
        }

        //如果一个字符的频率小于k 说明其不应该在答案集里 答案应该在它左边或者右边的字符串里
        int i = 0;
        int res = 0;
        for(int j = 0; j < s.length(); j++){
            char c = s.charAt(j);
            if(map.get(c) < k){
                res = Math.max(res,longestSubstring(s.substring(i,j),k));
                i = j + 1;
            }
        }
        if(i==0) return s.length();
        return Math.max(res,longestSubstring(s.substring(i),k));
    }


    /**
     * less memory
     * @param s
     * @param k
     * @return
     */
    public int longestSubstringV3(String s, int k) {
        int max = 0;
        for(int h = 1; h <= 26; h++)
        {
            int i = 0;
            int j = 0;
            int unique = 0;
            int less = 0;
            int index = 0;
            int[] freq = new int[26];
            while(i < s.length())
            {
                index = s.charAt(i) - 'a';
                if(freq[index] == 0)
                {
                    unique++;
                }
                freq[index]++;
                if(freq[index] == k)
                {
                    less++;
                }
                i++;
                while(unique > h)
                {
                    index = s.charAt(j) - 'a';
                    if(freq[index] == k)
                    {
                        less--;
                    }
                    freq[index]--;
                    if(freq[index] == 0)
                    {
                        unique--;
                    }
                    j++;
                }
                if(unique == h && less == h)
                {
                    int local = i - j;
                    max = Math.max(max, local);
                }
            }
        }
        return max;
    }

}
