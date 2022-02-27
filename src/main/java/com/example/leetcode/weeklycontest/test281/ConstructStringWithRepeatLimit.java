package com.example.leetcode.weeklycontest.test281;

import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * You are given a string s and an integer repeatLimit. Construct a new string repeatLimitedString using the characters of s such that no letter appears more than repeatLimit times in a row. You do not have to use all characters from s.
 *
 * Return the lexicographically largest repeatLimitedString possible.
 *
 * A string a is lexicographically larger than a string b if in the first position where a and b differ, string a has a letter that appears later in the alphabet than the corresponding letter in b. If the first min(a.length, b.length) characters do not differ, then the longer string is the lexicographically larger one.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "cczazcc", repeatLimit = 3
 * Output: "zzcccac"
 * Explanation: We use all of the characters from s to construct the repeatLimitedString "zzcccac".
 * The letter 'a' appears at most 1 time in a row.
 * The letter 'c' appears at most 3 times in a row.
 * The letter 'z' appears at most 2 times in a row.
 * Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
 * The string is the lexicographically largest repeatLimitedString possible so we return "zzcccac".
 * Note that the string "zzcccca" is lexicographically larger but the letter 'c' appears more than 3 times in a row, so it is not a valid repeatLimitedString.
 * Example 2:
 *
 * Input: s = "aababab", repeatLimit = 2
 * Output: "bbabaa"
 * Explanation: We use only some of the characters from s to construct the repeatLimitedString "bbabaa".
 * The letter 'a' appears at most 2 times in a row.
 * The letter 'b' appears at most 2 times in a row.
 * Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
 * The string is the lexicographically largest repeatLimitedString possible so we return "bbabaa".
 * Note that the string "bbabaaa" is lexicographically larger but the letter 'a' appears more than 2 times in a row, so it is not a valid repeatLimitedString.
 */
public class ConstructStringWithRepeatLimit {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/count-integers-with-even-digit-sum/solution/di-281chang-zhou-sai-hong-hai-you-xi-zhu-z7bm/
     * @param s
     * @param repeatLimit
     * @return
     */
    public String repeatLimitedString(String s, int repeatLimit) {
        // 用于存放每个字符的个数
        int[] count = new int[26];
        // 转化为字符数组
        char[] ss = s.toCharArray();
        // 逐一进行遍历，并且记录每个字符出现的次数
        for(int i=0;i<ss.length;i++) {
            // 对应字符位置上的个数 0-aCnt 1-bCnt
            count[ss[i]-'a']++;
        }
        // 用于返回结果字符串
        StringBuilder res = new StringBuilder();
        // 按照字符在26个字符当中的顺序倒叙添加到优先队列当中
        PriorityQueue<Integer> pq = new PriorityQueue<>((char1, char2)->char2-char1);
        // 遍历每个字符的个数 如果存在字符就将其添加到优先队列当中
        for(int i=0;i<26;i++) {
            if(count[i]>0)
                pq.add(i);
        }
        int checkLimitNum = 0;
        // 如果当前队列中还存在元素就继续构建结果字符串
        while(pq.size()>0) {
            // 取出当前字符的字符序号 例如 0-a 1-b ... 25-z
            int maxCntCharIdx = pq.poll();
            // 如果当前字符串 长度为0 或者 当前拼接的字符与上一个字符相同时 此时我们就要考虑repeatLimit的限制了
            if(res.length() == 0 || (maxCntCharIdx+'a') == res.charAt(res.length()-1)) {
                // 连续相同字符数量+1
                checkLimitNum++;
            } else {
                // 反之重置为1
                checkLimitNum=1;
            }
            // 然后对连续相同字符的限制进行判断
            if(checkLimitNum<=repeatLimit) {
                // 如果满足条件就直接将其加入到我们的结果字符串当中，并且字符数量减少
                res.append((char)('a'+maxCntCharIdx));
                count[maxCntCharIdx] --;
                // 如果当前还有剩余数量的字符 我们还需要继续把他返回到优先队列中进行下次判断 直到用完该字符序号的所有字符
                if(count[maxCntCharIdx]>0) pq.add(maxCntCharIdx);
            } else {
                // 反之到达了限制时 我们此时就要取出次高字典序的字符序号了 这和上周280周赛的第二题有点像
                if(pq.size()>0) {
                    // 重新将连续相同字符数量置为1
                    checkLimitNum = 1;
                    // 取出次字典序高的字符序号
                    int subMaxCntCharIdx = pq.poll();
                    // 将次高的字符序号字符加入其中
                    res.append((char)('a'+subMaxCntCharIdx));
                    // 次高字符数量减少
                    count[subMaxCntCharIdx]--;
                    // 将我们之前取出的第一个最高数量的字符返回到优先队列当中，因为咱们没有用这个 重复操作
                    pq.add(maxCntCharIdx);
                    // 如果当前次高的字符数量还有剩余 则也将其返回到优先队列当中 重复操作
                    if(count[subMaxCntCharIdx]>0) pq.add(subMaxCntCharIdx);
                } else {
                    // 返回结果
                    return res.toString();
                }
            }
        }
        return res.toString();
    }
}
