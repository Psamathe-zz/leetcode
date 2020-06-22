package com.example.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * On an 8x8 chessboard, there can be multiple Black Queens and one White King.
 *
 * Given an array of integer coordinates queens that represents the positions of the Black Queens, and a pair of coordinates king that represent the position of the White King, return the coordinates of all the queens (in any order) that can attack the King.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
 * Output: [[0,1],[1,0],[3,3]]
 * Explanation:
 * The queen at [0,1] can attack the king cause they're in the same row.
 * The queen at [1,0] can attack the king cause they're in the same column.
 * The queen at [3,3] can attack the king cause they're in the same diagnal.
 * The queen at [0,4] can't attack the king cause it's blocked by the queen at [0,1].
 * The queen at [4,0] can't attack the king cause it's blocked by the queen at [1,0].
 * The queen at [2,4] can't attack the king cause it's not in the same row/column/diagnal as the king.
 * Example 2:
 *
 *
 *
 * Input: queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
 * Output: [[2,2],[3,4],[4,4]]
 * Example 3:
 *
 *
 *
 * Input: queens = [[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],[2,2],[1,1],[6,4],[5,4],[0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]], king = [3,4]
 * Output: [[2,3],[1,4],[1,6],[3,7],[4,3],[5,4],[4,5]]
 * 3 4
 * 5 2
 */
public class QueensThatCanAttackKing {


    public static void main(String[] args) {
        QueensThatCanAttackKing queensThatCanAttackKing = new QueensThatCanAttackKing();
        int[][] queens = new int[][]{{5,6},{7,7},{2,1},{0,7},{1,6},{5,1},{3,7},{0,3},{4,0},{1,2},{6,3},{5,0},{0,4},{2,2},{1,1},{6,4},{5,4},{0,0},{2,6},{4,5},{5,2},{1,4},{7,5},{2,3},{0,5},{4,2},{1,0},{2,7},{0,1},{4,6},{6,1},{0,6},{4,3},{1,7}};
        int[] king = new int[]{3,4};

        queensThatCanAttackKing.queensAttacktheKing(queens,king);
    }
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> result = new ArrayList<>();
        int x = king[0];
        int y = king[1];
        Position left = null;
        Position right = null;
        Position top = null;
        Position bottom = null;
        Position lt = null;
        Position rt = null;
        Position lb = null;
        Position rb = null;
        List<Position> positions = new ArrayList<>();
        for(int[] queen : queens){
            if(queen[0] == x ){
                if(queen[1] < y ){
                    if(top == null)
                        top = new Position(x,queen[1]);
                    else if(queen[1] > top.y ){
                        top.y = queen[1];
                    }
                } else {
                    if(bottom == null)
                        bottom = new Position(x,queen[1]);
                    else if(queen[1] < bottom.y ){
                        bottom.y = queen[1];
                    }
                }
            }else if(queen[1] == y){
                if(queen[0] < x ){
                    if(left == null)
                        left = new Position(queen[0],y);
                    else if(queen[0] > left.x ){
                        left.x = queen[0];
                    }
                } else {
                    if(right == null)
                        right = new Position(queen[0],y);
                    else if(queen[0] < right.x ){
                        right.x = queen[0];
                    }
                }
            } else if(queen[0] - x == queen[1] - y){
                if(queen[0] < x ){
                    if(lt == null)
                        lt = new Position(queen[0],queen[1]);
                    else if(queen[0] > lt.x ){
                        lt.x = queen[0];
                        lt.y = queen[1];
                    }
                } else {
                    if(rb == null)
                        rb = new Position(queen[0],queen[1]);
                    else if(queen[0] < rb.x ){
                        rb.x = queen[0];
                        rb.y = queen[1];
                    }
                }
            } else if(queen[0] - x == y - queen[1]){
                if(queen[0] < x ){
                    if(lb == null)
                        lb = new Position(queen[0],queen[1]);
                    else if(queen[0] > lb.x ){
                        lb.x = queen[0];
                        lb.y = queen[1];
                    }
                } else {
                    if(rt == null)
                        rt = new Position(queen[0],queen[1]);
                    else if(queen[0] < rt.x ){
                        rt.x = queen[0];
                        rt.y = queen[1];
                    }
                }
            }
        }
        positions.add(top);
        positions.add(right);
        positions.add(left);
        positions.add(bottom);
        positions.add(lb);
        positions.add(lt);
        positions.add(rb);
        positions.add(rt);
        for(Position position : positions){
            if(position != null) {
                List<Integer> queen = new ArrayList<>();
                queen.add(position.x);
                queen.add(position.y);
                result.add(queen);
            }
        }
        return result;
    }

    class Position{
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }


    /**
     *
     * @param queens
     * @param king
     * @return
     */
    public List<List<Integer>> queensAttacktheKingV2(int[][] queens, int[] king) {

        List<List<Integer>>result=new ArrayList();
        boolean[][]seen=new boolean[8][8];
        for(int[]queen:queens)
        {
            seen[queen[0]][queen[1]]=true;
        }

        int[]directions={-1,0,1};
        for(int dx:directions)
        {
            for(int dy:directions)
            {
                if(dx==0 && dy==0)
                {
                    continue;
                }

                int x=king[0];
                int y=king[1];

                while(x+dx >=0 && x+dx<8 && y+dy>=0 && y+dy<8)
                {
                    x+=dx;
                    y+=dy;

                    if(seen[x][y])
                    {
                        result.add(Arrays.asList(x,y));
                        break;
                    }
                }
            }
        }

        return result;
    }

    /**
     *
     */
    int[][] dir = {{1,0}, {0, 1}, {-1, 0}, {0, -1}, {1,1}, {-1,-1}, {-1,1}, {1,-1}};
    public List<List<Integer>> queensAttacktheKingV3(int[][] queens, int[] king) {
        List<List<Integer>> ret = new ArrayList<>();
        boolean[][] queen = new boolean[8][8];
        for(int[] q : queens) {
            queen[q[0]][q[1]] = true;
        }
        for(int i = 0; i < dir.length; i++) {
            helper(ret, king, queen, i);
        }
        return ret;
    }

    private void helper(List<List<Integer>> ret, int[] cell, boolean[][] queen, int d) {
        if(queen[cell[0]][cell[1]]) {
            List<Integer> list = new ArrayList<>();
            list.add(cell[0]); list.add(cell[1]);
            ret.add(list);
            return;
        }
        int nextI = cell[0] + dir[d][0];
        int nextJ = cell[1] + dir[d][1];
        if(nextI < 0 || nextJ < 0 || nextI == 8 || nextJ == 8)
            return;
        helper(ret, new int[]{nextI, nextJ}, queen, d);
    }
}
