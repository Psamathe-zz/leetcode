package com.example.leetcode.biweeklycontest.old.contest65;

/**
 * A width x height grid is on an XY-plane with the bottom-left cell at (0, 0) and the top-right cell at (width - 1, height - 1). The grid is aligned with the four cardinal directions ("North", "East", "South", and "West"). A robot is initially at cell (0, 0) facing direction "East".
 *
 * The robot can be instructed to move for a specific number of steps. For each step, it does the following.
 *
 * Attempts to move forward one cell in the direction it is facing.
 * If the cell the robot is moving to is out of bounds, the robot instead turns 90 degrees counterclockwise and retries the step.
 * After the robot finishes moving the number of steps required, it stops and awaits the next instruction.
 *
 * Implement the Robot class:
 *
 * Robot(int width, int height) Initializes the width x height grid with the robot at (0, 0) facing "East".
 * void move(int num) Instructs the robot to move forward num steps.
 * int[] getPos() Returns the current cell the robot is at, as an array of length 2, [x, y].
 * String getDir() Returns the current direction of the robot, "North", "East", "South", or "West".
 *
 *
 * Example 1:
 *
 * example-1
 * Input
 * ["Robot", "move", "move", "getPos", "getDir", "move", "move", "move", "getPos", "getDir"]
 * [[6, 3], [2], [2], [], [], [2], [1], [4], [], []]
 * Output
 * [null, null, null, [4, 0], "East", null, null, null, [1, 2], "West"]
 *
 * Explanation
 * Robot robot = new Robot(6, 3); // Initialize the grid and the robot at (0, 0) facing East.
 * robot.move(2);  // It moves two steps East to (2, 0), and faces East.
 * robot.move(2);  // It moves two steps East to (4, 0), and faces East.
 * robot.getPos(); // return [4, 0]
 * robot.getDir(); // return "East"
 * robot.move(2);  // It moves one step East to (5, 0), and faces East.
 *                 // Moving the next step East would be out of bounds, so it turns and faces North.
 *                 // Then, it moves one step North to (5, 1), and faces North.
 * robot.move(1);  // It moves one step North to (5, 2), and faces North (not West).
 * robot.move(4);  // Moving the next step North would be out of bounds, so it turns and faces West.
 *                 // Then, it moves four steps West to (1, 2), and faces West.
 * robot.getPos(); // return [1, 2]
 * robot.getDir(); // return "West"
 *
 *
 */
public class WalkingRobotSimulationII {
    public static void main(String[] args) {
        WalkingRobotSimulationII walkingRobotSimulationII = new WalkingRobotSimulationII(6, 3);
        walkingRobotSimulationII.move(2);
        walkingRobotSimulationII.move(2);
        System.out.println(walkingRobotSimulationII.getPos()[0]);
        System.out.println(walkingRobotSimulationII.getPos()[1]);
        System.out.println(walkingRobotSimulationII.getDir());
        walkingRobotSimulationII.move(2);
        walkingRobotSimulationII.move(1);
        walkingRobotSimulationII.move(4);
        System.out.println(walkingRobotSimulationII.getPos()[0]);
        System.out.println(walkingRobotSimulationII.getPos()[1]);
        System.out.println(walkingRobotSimulationII.getDir());

    }
    int dir;
    int[][] dirs = new int[][]{
            {1,0},
            {0,1},
            {-1,0},
            {0,-1},
    };
    int x;
    int y;
    int[] max = new int[4];
    String[] dirString = new String[]{"East", "North", "West", "South"};
    public WalkingRobotSimulationII(int width, int height) {
        this.dir = 0;
        max[0] = width - 1;
        max[1] = height - 1;
        max[2] = 0;
        max[3] = 0;
        this.x = 0;
        this.y = 0;
    }

    public void move(int num) {
        // *特殊处理：当前在起点，且需要走的步数要绕一周回到原点，则需要修改走完后的方向为“下”
        if(num >= (max[0]*2 + max[1]*2) && (num % (max[0]*2 + max[1]*2)) == 0 && x == 0 && y == 0){
            dir = 3;
        }
        num = num % (max[0]*2 + max[1]*2);
        int val;
        if(dir % 2 == 0){
            val = Math.abs(max[dir] - x);
            if( val > num ){
                x += dirs[dir][0] * num;
            } else {
                x = max[dir];
                dir = (dir + 1) % 4;
                move(num - val);
            }
        } else {
            val =  Math.abs(max[dir] - y);
            if(val > num ){
                y += dirs[dir][1] * num;
            } else {
                y = max[dir];
                dir = (dir + 1) % 4;
                move(num - val);
            }
        }
    }

    public int[] getPos() {
        return new int[]{x, y};
    }

    public String getDir() {
        return dirString[dir];
    }

}
