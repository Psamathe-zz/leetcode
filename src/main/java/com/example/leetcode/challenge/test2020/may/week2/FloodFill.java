package com.example.leetcode.challenge.test2020.may.week2;


/**
 * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
 *
 * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.
 *
 * To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel,
 * plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.
 *
 * At the end, return the modified image.
 *
 * Example 1:
 * Input:
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation:
 * From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
 * by a path of the same color as the starting pixel are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected
 * to the starting pixel.
 * [[0,0,0],[0,1,1]]
 * 1
 * 1
 * 1
 */
public class FloodFill {

    public static void main(String[] args) {
        int[][] image = {{0,0,0},{0,1,1}};
        int sr = 1;
        int sc = 1;
        int newColor = 1;

        FloodFill floodFill = new FloodFill();
        int[][] result= floodFill.floodFillV2(image,sr,sc,newColor);
        System.out.println(result);
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originColor = image[sr][sc];
        boolean[][] visited = new boolean[image.length][image[0].length];
        image[sr][sc] = newColor;
        visited[sr][sc] = true;
        help(image, sr, sc, newColor,originColor,visited);
        return image;
    }

    public void help(int[][] image, int sr, int sc, int newColor,int originColor, boolean[][] visited){
        if(sr!=0 && image[sr-1][sc] ==originColor && visited[sr-1][sc] ==false){
            image[sr-1][sc] = newColor;
            visited[sr-1][sc] = true;
            help(image,sr - 1, sc, newColor,originColor,visited);
        }
        if(sr!=image.length - 1 && image[sr+1][sc] ==originColor && visited[sr+1][sc] ==false){
            image[sr+1][sc] = newColor;
            visited[sr+1][sc] = true;
            help(image,sr + 1, sc, newColor,originColor,visited);
        }

        if(sc!=0 && image[sr][sc-1] ==originColor && visited[sr][sc-1] ==false){
            image[sr][sc-1] = newColor;
            visited[sr][sc-1] = true;
            help(image,sr, sc-1, newColor,originColor,visited);
        }
        if(sc!=image[0].length - 1 && image[sr][sc+1] ==originColor && visited[sr][sc+1] ==false){
            image[sr][sc+1] = newColor;
            visited[sr][sc+1]  = true;
            help(image,sr, sc+1, newColor,originColor,visited);
        }
    }


    /**
     * faster solution
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    public int[][] floodFillV2(int[][] image, int sr, int sc, int newColor) {
        if (image==null || image.length==0 || image[sr][sc]==newColor) return image;
        int srColor= image[sr][sc];
        dfs(image, sr,sc, srColor,newColor);
        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int srColor,int newColor){
        if (sr<0 || sr >= image.length || sc<0 || sc>=image[0].length || image[sr][sc]!=srColor)
            return;
        if (image[sr][sc]==srColor)
            image[sr][sc]=newColor;
        dfs(image,sr-1,sc,srColor,newColor);
        dfs(image,sr+1,sc,srColor,newColor);
        dfs(image,sr,sc+1,srColor,newColor);
        dfs(image,sr,sc-1,srColor,newColor);


    }
}
