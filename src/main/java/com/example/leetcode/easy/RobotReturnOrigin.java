package com.example.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * There is a robot starting at position (0, 0), the origin, on a 2D plane. Given a sequence of its moves, judge if this robot ends up at (0, 0) after it completes its moves.
 *
 * The move sequence is represented by a string, and the character moves[i] represents its ith move. Valid moves are R (right), L (left), U (up), and D (down). If the robot returns to the origin after it finishes all of its moves, return true. Otherwise, return false.
 *
 * Note: The way that the robot is "facing" is irrelevant. "R" will always make the robot move to the right once, "L" will always make it move left, etc. Also, assume that the magnitude of the robot's movement is the same for each move.
 *
 * Example 1:
 *
 * Input: "UD"
 * Output: true
 * Explanation: The robot moves up once, and then down once. All moves have the same magnitude, so it ended up at the origin where it started. Therefore, we return true.
 *
 *
 * Example 2:
 *
 * Input: "LL"
 * Output: false
 * Explanation: The robot moves left twice. It ends up two "moves" to the left of the origin. We return false because it is not at the origin at the end of its moves.
 */
public class RobotReturnOrigin {
    public static void main(String[] args) {
        String moves = "LL";
        RobotReturnOrigin robotReturnOrigin = new RobotReturnOrigin();
        boolean result = robotReturnOrigin.judgeCircle(moves);
        System.out.println(result);
    }

    public boolean judgeCircle(String moves) {
        Map<String, int[]> moveMap = new HashMap<>();
        moveMap.put("L", new int[]{-1,0});
        moveMap.put("R", new int[]{1,0});
        moveMap.put("U", new int[]{0,1});
        moveMap.put("D", new int[]{0,-1});

        int x = 0;
        int y = 0;
        for(String move : moves.split("")){
            int[] moveTmp = moveMap.get(move);
            x += moveTmp[0];
            y += moveTmp[1];
        }

        return x==0&&y==0;
    }

    /**
     * faster solution
     * @param moves
     * @return
     */
    public boolean judgeCircleV2(String moves) {
        int[] map=new int[26];
        for(char c:moves.toCharArray())
            map[c-'A']++;
        return map['U'-'A']-map['D'-'A']==0 && map['R'-'A']-map['L'-'A']==0;
    }
}
