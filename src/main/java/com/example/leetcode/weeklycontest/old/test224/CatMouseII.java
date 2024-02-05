package com.example.leetcode.weeklycontest.old.test224;


import java.util.ArrayDeque;

/**
 * A game is played by a cat and a mouse named Cat and Mouse.
 *
 * The environment is represented by a grid of size rows x cols, where each element is a wall, floor, player (Cat, Mouse), or food.
 *
 * Players are represented by the characters 'C'(Cat),'M'(Mouse).
 * Floors are represented by the character '.' and can be walked on.
 * Walls are represented by the character '#' and cannot be walked on.
 * Food is represented by the character 'F' and can be walked on.
 * There is only one of each character 'C', 'M', and 'F' in grid.
 * Mouse and Cat play according to the following rules:
 *
 * Mouse moves first, then they take turns to move.
 * During each turn, Cat and Mouse can jump in one of the four directions (left, right, up, down). They cannot jump over the wall nor outside of the grid.
 * catJump, mouseJump are the maximum lengths Cat and Mouse can jump at a time, respectively. Cat and Mouse can jump less than the maximum length.
 * Staying in the same position is allowed.
 * Mouse can jump over Cat.
 * The game can end in 4 ways:
 *
 * If Cat occupies the same position as Mouse, Cat wins.
 * If Cat reaches the food first, Cat wins.
 * If Mouse reaches the food first, Mouse wins.
 * If Mouse cannot get to the food within 1000 turns, Cat wins.
 * Given a rows x cols matrix grid and two integers catJump and mouseJump, return true if Mouse can win the game if both Cat and Mouse play optimally, otherwise return false.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: grid = ["####F","#C...","M...."], catJump = 1, mouseJump = 2
 * Output: true
 * Explanation: Cat cannot catch Mouse on its turn nor can it get the food before Mouse.
 * Example 2:
 *
 *
 *
 * Input: grid = ["M.C...F"], catJump = 1, mouseJump = 4
 * Output: true
 * Example 3:
 *
 * Input: grid = ["M.C...F"], catJump = 1, mouseJump = 3
 * Output: false
 * Example 4:
 *
 * Input: grid = ["C...#","...#F","....#","M...."], catJump = 2, mouseJump = 5
 * Output: false
 * Example 5:
 *
 * Input: grid = [".M...","..#..","#..#.","C#.#.","...#F"], catJump = 3, mouseJump = 1
 * Output: true
 */
public class CatMouseII {
    public static void main(String[] args) {

    }


    /**
     * https://leetcode-cn.com/problems/cat-and-mouse-ii/solution/ji-yi-hua-sou-suo-dpmo-ni-by-cyz3209-jdkj/
     这题有点毒 dp[t][x][y] t表示第几步，x为老鼠状态，y为Tom状态
     * @param grid
     * @param catJump
     * @param mouseJump
     * @return
     */
    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        int row = grid.length;
        int col = grid[0].length();
        //单元格从0编号，编号到n-1
        int n = row * col;
        int[] state = new int[row * col];

        int cat = 0;
        int mouse = 0;
        int target = 0;
        int cur = 0;
        //收集C M F的位置，以及标记某个单元格是否是墙壁
        for (int i = 0; i < row; i++) {
            for (char chars : grid[i].toCharArray()) {
                if (chars == 'C') {
                    cat = cur;
                }
                if (chars == 'M') {
                    mouse = cur;
                }
                if (chars == 'F') {
                    target = cur;
                }

                if (chars == '#') {
                    state[cur++] = 1;
                } else {
                    state[cur++] = 0;
                }
            }
        }

        //color[mouse][cat][turn] = result
        //i 老鼠 j 猫
        //turn: 0 老鼠行动轮  1 猫行动轮
        //result: 0 未知， 1 老鼠win， 2 猫win, -1不合法
        int[][][] color = new int[n][n][2];

        //初始化，抓到老鼠都是猫赢 2 catWin
        for (int i = 0; i < n; i++) {
            color[i][i][0] = 2;
            color[i][i][1] = 2;
        }

        //初始化， 到达目的地，谁到谁赢 result: 1-mouseWin, 2-catWin
        for (int i = 0; i < n; i++) {
            color[target][i][1] = 1;
            color[i][target][0] = 2;
        }

        //初始化，不合法的状态，都到达终端
        color[target][target][0] = -1;
        color[target][target][1] = -1;

        ArrayDeque<int[]> queue = new ArrayDeque<>();

        //收集所有的确定为1-mouseWin，2-catWin的状态
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (state[i] == 1 || state[j] == 1) {
                    continue;
                }
                if (color[i][j][0] == 1) {
                    queue.offerLast(new int[]{i, j, 0});
                }
                if (color[i][j][1] == 1) {
                    queue.offerLast(new int[]{i, j, 1});
                }
                if (color[i][j][0] == 2) {
                    queue.offerLast(new int[]{i, j, 0});
                }
                if (color[i][j][1] == 2) {
                    queue.offerLast(new int[]{i, j, 1});
                }
            }
        }

        //可能的出路信息
        int[][][] possible = new int[n][n][2];

        //计算老鼠/猫在到达某个位置的前一个可能的位置
        int[][] mouseJumps = new int[n][];
        int[][] catJumps = new int[n][];
        for (int i = 0; i < n; i++) {
            mouseJumps[i] = getJumpPos(row, col, i, mouseJump, grid);
            catJumps[i] = getJumpPos(row, col, i, catJump, grid);
        }

        //每次前一次可能的位置，也是当前位置可以往跳的后一个位置
        //所以prePosLength = nextPosLength = PossibleLength
        //计算每种状态可能转移成功的其他状态的数量。
        for (int i = 0; i < n; i++) {
            int mousePossible = mouseJumps[i].length;
            int catPossible = catJumps[i].length;
            for (int j = 0; j < n; j++) {
                possible[i][j][0] = mousePossible;
                possible[j][i][1] = catPossible;
            }
        }

        int step = 0;
        while (step < 1000 && !queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] popStatus = queue.removeFirst();
                int a = popStatus[0];
                int b = popStatus[1];
                int c = popStatus[2];
                if (color[a][b][c] == 1) { //result = 1 ---- mouse win
                    if (c == 1) { //当前轮是猫轮，那么上一轮是老鼠轮，老鼠轮一定往必胜的方案跳【ref1】
                        int[] mousePoses = mouseJumps[a];
                        for (int prePos : mousePoses) {
                            if (color[prePos][b][0] == 0) {
                                color[prePos][b][0] = 1;
                                queue.offerLast(new int[]{prePos, b, 0});
                            }
                        }
                    } else {
                        //当前轮是老鼠轮，那么上一轮是猫轮，所有可以转移到此状态的猫轮不输的出路减一【ref2】
                        for (int preCatPos : catJumps[b]) {
                            if (color[a][preCatPos][1] == 0) {
                                possible[a][preCatPos][1]--;
                                //当前轮是老鼠轮，那么上一轮是猫轮，所有可以转移到此状态的猫轮不输的出路减一
                                //当减一后没有其他出路，则这个状态虽然是猫轮选择，但所有的选择都是猫输
                                //节点状态置为老鼠赢 1 --- mouseWin
                                if (possible[a][preCatPos][1] == 0) {
                                    color[a][preCatPos][1] = 1;
                                    queue.offerLast(new int[] {a, preCatPos, 1});
                                }
                            }
                        }
                    }
                } else { //result = 2 ------ cat win 2
                    if (c == 0) { //当前轮是老鼠轮，上一轮是猫轮，思路同 【ref1】
                        int[] preCatPoses = catJumps[b];
                        for (int preCatPos : preCatPoses) {
                            if (color[a][preCatPos][1] == 0) {
                                color[a][preCatPos][1] = 2;
                                queue.offerLast(new int[]{a, preCatPos, 1});
                            }
                        }
                    } else {
                        //思路同【ref2】
                        int[] preMousePoses = mouseJumps[a];
                        for (int preMousePos : preMousePoses) {
                            if (color[preMousePos][b][0] == 0) {
                                possible[preMousePos][b][0]--;
                                if (possible[preMousePos][b][0] == 0) {
                                    color[preMousePos][b][0] = 2;
                                    queue.offerLast(new int[]{preMousePos, b, 0});
                                }
                            }
                        }
                    }
                }
            }
            //初始状态有结果了，直接退出循环
            if (color[mouse][cat][0] != 0) {
                break;
            }

            step += 1;
        }

        //判断初始状态的结果，由于不是老鼠赢，总是算猫赢，所以可以直接判断初始状态有没有变成老鼠赢
        return color[mouse][cat][0] == 1;
    }

    //计算老鼠/猫在到达某个位置的前一个可能的位置
    private int[] getJumpPos(int row, int col, int a, int jump, String[] grid) {
        int i = a / col;
        int j = a % col;
        int li = i;
        while (li > i - jump && li > 0) {
            if (grid[li-1].charAt(j) == '#') {
                break;
            }
            li--;
        }

        int ri = i;
        while (ri < i + jump && ri < row - 1) {
            if (grid[ri + 1].charAt(j) == '#') {
                break;
            }
            ri++;
        }


        int tj = j;
        while (tj > j - jump && tj > 0) {
            if (grid[i].charAt(tj - 1) == '#') {
                break;
            }
            tj--;
        }
        int bj = j;
        while (bj < j + jump && bj < col - 1) {
            if (grid[i].charAt(bj + 1) == '#') {
                break;
            }
            bj++;
        }

        int[] ans = new int[ri - li + 1 + bj - tj];
        int cur = 0;
        for (int x = li; x <= ri; x++) {
            ans[cur++] = x * col + j;
        }
        for (int y = tj; y <= bj; y++) {
            if (y == j) {
                continue;
            }
            ans[cur++] = i * col + y;
        }
        return ans;
    }

}
