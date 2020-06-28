package com.example.leetcode.weeklycontest.test195;


import java.util.*;

/**
 * Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east, or west, respectively.
 * You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.
 *
 * Return True if the path crosses itself at any point, that is, if at any time you are on a location you've previously visited. Return False otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: path = "NES"
 * Output: false
 * Explanation: Notice that the path doesn't cross any point more than once.
 * Example 2:
 *
 *
 *
 * Input: path = "NESWW"
 * Output: true
 * Explanation: Notice that the path visits the origin twice.
 *
 *
 * Constraints:
 *
 * 1 <= path.length <= 10^4
 * path will only consist of characters in {'N', 'S', 'E', 'W}
 */
public class PathCrossing {

    public static void main(String[] args) {
        String path = "NESWW";
        PathCrossing pathCrossing = new PathCrossing();
        boolean result = pathCrossing.isPathCrossing(path);
        System.out.println(result);
    }

    public boolean isPathCrossing(String path) {
        int x = 0;
        int y = 0;
        char[] chars = path.toCharArray();
        int length = chars.length;
        Set<Position> set = new HashSet<>();
        Position position = new Position(x,y);
        set.add(position);
        for(char c : chars){
            if(c == 'N'){
                y++;
            } else if(c == 'S'){
                y--;
            } else if(c == 'E'){
                x++;
            } else {
                x--;
            }
            position = new Position(x,y);
            if(set.contains(position)){
                return true;
            } else {
                set.add(position);
            }
        }
        return false;
    }

    public class Position{
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x &&
                    y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    /**
     * faster solution
     * Hash
     * @param path
     * @return
     */
    public boolean isPathCrossingV1(String path) {
        Set<Integer> pos = new HashSet<>();
        int x = 0, y = 0;
        pos.add(0);
        for (char c: path.toCharArray()) {
            switch (c) {
                case 'N':
                    y++;
                    break;
                case 'S':
                    y--;
                    break;
                case 'E':
                    x++;
                    break;
                case 'W':
                    x--;
                    break;
            }
            int p = x*10000+y;
            if (pos.contains(p))
                return true;
            pos.add(p);
        }
        return false;
    }
}
