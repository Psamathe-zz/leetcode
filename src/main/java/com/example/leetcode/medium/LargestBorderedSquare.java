package com.example.leetcode.medium;

/**
 * Given a 2D grid of 0s and 1s, return the number of elements in the largest square subgrid that has all 1s on its border, or 0 if such a subgrid doesn't exist in the grid.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: 9
 * Example 2:
 *
 * Input: grid = [[1,1,0,0]]
 * Output: 1
 *
 * [
 * [1,1,0],
 * [1,1,1],
 * [1,1,1],
 * [1,1,1]]
 */
public class LargestBorderedSquare {
    public static void main(String[] args) {
        int[][] grid = new int[][]{{0,1,1,1,1,0},{1,1,0,1,1,0},{1,1,0,1,0,1},{1,1,0,1,1,1},{1,1,0,1,1,1},{1,1,1,1,1,1},{1,0,1,1,1,1},{0,0,1,1,1,1},{1,1,1,1,1,1}};
        LargestBorderedSquare largestBorderedSquare = new LargestBorderedSquare();
        int result = largestBorderedSquare.largest1BorderedSquare(grid);
        System.out.println(result);
    }

    public int largest1BorderedSquare(int[][] grid) {
        int result = 0;

        int u = grid.length;
        int v = grid[0].length;

        for(int i = 0;i < u;i++){
            for(int j = 0; j < v; j++){
                if(grid[i][j] == 1 && result == 0)
                    result = 1;
                if(grid[i][j] == 1 && i < u - 1 && j < v - 1 && grid[i + 1][j] == 1 && grid[i][j + 1] == 1){
                    int x = i;
                    int y = j;
                    while ( x < u){
                        if(grid[x][j] == 1)
                            x++;
                        else
                            break;
                    }
                    while ( y < v){
                        if(grid[i][y] == 1)
                            y++;
                        else
                            break;
                    }
                    int length = Math.min(x-i,y-j);
                    if(length < result) {
                        continue;
                    }
                    while (length> 0){
                        x = i;
                        y = j;
                        while ( x < u && x < i + length){
                            if(grid[x][j + length - 1] == 1)
                                x++;
                            else
                                break;
                        }
                        while ( y < v && y < j + length){
                            if(grid[i + length  - 1][y] == 1)
                                y++;
                            else
                                break;
                        }
                        if(x-i==y-j && length==x-i)
                            break;
                        length--;

                    }
                    if(result < length)
                        result = length;
                }
            }
        }

        return (int)Math.pow(result,2);
    }

    /**
     * faster solution
     */
    private static class Cell {
        private int down;
        private int right;

        public Cell(int right, int down) {
            this.down = down;
            this.right = right;
        }

        public int getDown() {
            return down;
        }

        public int getRight() {
            return right;
        }

        @Override
        public String toString() {
            return "Cell [down=" + down + ", right=" + right + "]";
        }
    }
    private int result = 0;

    public int largest1BorderedSquareV2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Cell[][] board = new Cell[m][n];

        for (int i = 0; i < m; ++i) {
            board[i] = new Cell[n];
        }

        BuildBoard(0, 0, board, grid);
        //printBoard(board);
        return result * result;
    }

    private void BuildBoard(int x, int y, Cell[][] board, int[][] grid) {
        if (x >= grid.length || y >= grid[0].length) {
            return;
        }
        if (board[x][y] != null) {
            return;
        }
        BuildBoard(x + 1, y, board, grid);
        BuildBoard(x, y + 1, board, grid);
        if (grid[x][y] == 0) {
            board[x][y] = new Cell(0, 0);
            return;
        }
        int down = 1 + (x + 1 < grid.length? board[x + 1][y].getDown():0);
        int right = 1 + (y + 1 < grid[0].length? board[x][y + 1].getRight():0);
        board[x][y] = new Cell(right, down);

        int length = Math.min(down, right);
        for (int l = length; l >= 0; --l) {
            if (l <= result) {
                break;
            }
            if (board[x][y + l - 1].getDown() >= l &&
                    board[x + l - 1][y].getRight() >= l) {
                result = l;
                break;
            }
        }
    }

    private int findLargest1BoarderedSquare(Cell[][] board) {
        int result = 0;

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                int length = Math.min(board[i][j].getRight(),
                        board[i][j].getDown());
                for (int l = length; l >= 0; --l) {
                    if (l <= result) {
                        break;
                    }
                    if (board[i][j + l - 1].getDown() >= l &&
                            board[i + l - 1][j].getRight() >= l) {
                        result = l;
                        break;
                    }
                }
            }
        }

        return result * result;
    }

    private void printBoard(Cell[][] board) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * faster version
     */
    public int largest1BorderedSquareV3(int[][] arr) {
        int m=arr.length,n=arr[0].length;

        int[][] right=new int[m][n];
        int[][] down=new int[m][n];

        for(int i=0;i<m;i++)
        {
            for(int j=n-1;j>=0;j--)
            {
                if(arr[i][j]==1)
                {
                    if(j==n-1)
                    {
                        right[i][j]=arr[i][j];
                    }
                    else
                    {
                        right[i][j]=right[i][j+1]+1;
                    }
                }
            }
        }
        for(int i=m-1;i>=0;i--)
        {
            for(int j=0;j<n;j++)
            {
                if(arr[i][j]==1)
                {
                    if(i==m-1)
                    {
                        down[i][j]=arr[i][j];
                    }
                    else
                    {
                        down[i][j]=down[i+1][j]+1;
                    }
                }
            }
        }
        int ans=0;
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(arr[i][j]==1)
                {
                    int small=Math.min(right[i][j],down[i][j]);
                    while(small>ans)
                    {
                        if(down[i][j+small-1]>=small && right[i+small-1][j]>=small)
                            ans=small;
                        small--;
                    }
                }
            }
        }
        return ans*ans;
    }
}
