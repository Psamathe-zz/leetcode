package com.example.leetcode.biweeklycontest.old.contest67;

import java.util.Arrays;

/**
 * You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt. This area is in the shape of a circle with the center as the location of the bomb.
 *
 * The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.
 *
 * You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range. These bombs will further detonate the bombs that lie in their ranges.
 *
 * Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: bombs = [[2,1,3],[6,1,4]]
 * Output: 2
 * Explanation:
 * The above figure shows the positions and ranges of the 2 bombs.
 * If we detonate the left bomb, the right bomb will not be affected.
 * But if we detonate the right bomb, both bombs will be detonated.
 * So the maximum bombs that can be detonated is max(1, 2) = 2.
 * Example 2:
 *
 *
 * Input: bombs = [[1,1,5],[10,10,5]]
 * Output: 1
 * Explanation:
 * Detonating either bomb will not detonate the other bomb, so the maximum number of bombs that can be detonated is 1.
 * Example 3:
 *
 *
 * Input: bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
 * Output: 5
 * Explanation:
 * The best bomb to detonate is bomb 0 because:
 * - Bomb 0 detonates bombs 1 and 2. The red circle denotes the range of bomb 0.
 * - Bomb 2 detonates bomb 3. The blue circle denotes the range of bomb 2.
 * - Bomb 3 detonates bomb 4. The green circle denotes the range of bomb 3.
 * Thus all 5 bombs are detonated.
 *
 */
public class DetonateMaximumBombs {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/detonate-the-maximum-bombs/solution/java-dfsti-jie-dai-zhu-shi-ban-by-58888-6tcw/
     */
    // 炸弹引爆最大数量
    int max = 1;
    // 炸弹是否引爆备忘录
    boolean[] mem;
    public int maximumDetonation(int[][] bombs) {
        mem = new boolean[bombs.length];
        for(int i = 0 ; i < bombs.length ; i ++){
            // 先引爆一个
            dfs(bombs, i);
            // 所有炸弹置为未引爆状态
            Arrays.fill(mem,false);
        }
        return max;
    }

    private int dfs(int[][] bombs, int i){
        if(mem[i]){
            return 0;
        }
        mem[i] = true;
        int ret = 1;
        // 遍历所有炸弹，判断是否会连带引爆
        for(int j = 0 ; j < bombs.length ; j ++){
            if(mem[j])
                continue;
            if(canBom(bombs,i,j)){
                // j爆炸之后，会带动其他的炸弹爆炸，继续dfs
                ret += dfs(bombs, j);
            }
        }
        max = Math.max(max,ret);
        return ret;
    }

    private boolean canBom(int[][] bombs, int i, int j){
        int[] b1 = bombs[i];
        int[] b2 = bombs[j];
        long x0 = b1[0], x1 = b2[0], y0 = b1[1], y1 = b2[1], r0 = b1[2];
        long len = (y1-y0)*(y1-y0) + (x1-x0)*(x1-x0);
        long r02 = r0 * r0;
        // 【两点距离的平方】(y1-y0)^2 + (x1-x0)^2 < 【引爆半径的平方】r0^2 则会被引爆
        if(len <= r02)
            return true;

        return false;
    }
}
