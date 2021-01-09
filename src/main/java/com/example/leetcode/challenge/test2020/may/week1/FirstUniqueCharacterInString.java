package com.example.leetcode.challenge.test2020.may.week1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 *
 * Examples:
 *
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode",
 * return 2.
 */
public class FirstUniqueCharacterInString {
    public static void main(String[] args) {
        String s = "loveleetcode";
        FirstUniqueCharacterInString firstUniqueCharacterInString = new FirstUniqueCharacterInString();
        int result = firstUniqueCharacterInString.firstUniqChar(s);
        System.out.println(result);
    }

    public int firstUniqChar(String s) {
        Map<Position,Integer> map = new LinkedHashMap<>();

        int index = 0;
        for(Character c: s.toCharArray()){
            map.put(new Position(c,index),map.getOrDefault(new Position(c,index),0) + 1);
            index++;
        }

        return map.entrySet().stream().filter(e->e.getValue()==1).map(e->e.getKey().getFirstPosition()).findFirst().orElse(-1);

    }

    public class Position{
        Character c;
        int firstPosition;

        public Position(Character c, int firstPosition) {
            this.c = c;
            this.firstPosition = firstPosition;
        }

        public Character getC() {
            return c;
        }

        public int getFirstPosition() {
            return firstPosition;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return Objects.equals(c, position.c);
        }

        @Override
        public int hashCode() {
            return Objects.hash(c);
        }
    }


    /**
     * faster solution
     * @param s
     * @return
     */
    public int firstUniqCharV2(String s) {
        int res = Integer.MAX_VALUE;

        for(char c = 'a'; c <= 'z'; c++){
            int index = s.indexOf(c);

            if(index != -1 && index == s.lastIndexOf(c))
                res = Math.min(res, index);
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }


    /**
     * less memory
     * @param s
     * @return
     */
    public int firstUniqCharV3(String s) {
        Set<Character> usedMoreThanOnce = IntStream.range(0, s.length())
                .mapToObj(s::charAt)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        for (int i = 0; i < s.length(); i++)
            if (! usedMoreThanOnce.contains(s.charAt(i))) return i;

        return -1;
    }
}
