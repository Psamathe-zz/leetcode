package com.example.leetcode.weeklycontest.test383;

/**
 * You are given a 0-indexed m x n grid image which represents a grayscale image, where image[i][j] represents a pixel with intensity in the range[0..255]. You are also given a non-negative integer threshold.
 * <p>
 * Two pixels image[a][b] and image[c][d] are said to be adjacent if |a - c| + |b - d| == 1.
 * <p>
 * A region is a 3 x 3 subgrid where the absolute difference in intensity between any two adjacent pixels is less than or equal to threshold.
 * <p>
 * All pixels in a region belong to that region, note that a pixel can belong to multiple regions.
 * <p>
 * You need to calculate a 0-indexed m x n grid result, where result[i][j] is the average intensity of the region to which image[i][j] belongs, rounded down to the nearest integer. If image[i][j] belongs to multiple regions, result[i][j] is the average of the rounded down average intensities of these regions, rounded down to the nearest integer. If image[i][j] does not belong to any region, result[i][j] is equal to image[i][j].
 * <p>
 * Return the grid result.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: image = [[5,6,7,10],[8,9,10,10],[11,12,13,10]], threshold = 3
 * Output: [[9,9,9,9],[9,9,9,9],[9,9,9,9]]
 * Explanation: There exist two regions in the image, which are shown as the shaded areas in the picture. The average intensity of the first region is 9, while the average intensity of the second region is 9.67 which is rounded down to 9. The average intensity of both of the regions is (9 + 9) / 2 = 9. As all the pixels belong to either region 1, region 2, or both of them, the intensity of every pixel in the result is 9.
 * Please note that the rounded-down values are used when calculating the average of multiple regions, hence the calculation is done using 9 as the average intensity of region 2, not 9.67.
 * Example 2:
 * <p>
 * <p>
 * Input: image = [[10,20,30],[15,25,35],[20,30,40],[25,35,45]], threshold = 12
 * Output: [[25,25,25],[27,27,27],[27,27,27],[30,30,30]]
 * Explanation: There exist two regions in the image, which are shown as the shaded areas in the picture. The average intensity of the first region is 25, while the average intensity of the second region is 30. The average intensity of both of the regions is (25 + 30) / 2 = 27.5 which is rounded down to 27. All the pixels in row 0 of the image belong to region 1, hence all the pixels in row 0 in the result are 25. Similarly, all the pixels in row 3 in the result are 30. The pixels in rows 1 and 2 of the image belong to region 1 and region 2, hence their assigned value is 27 in the result.
 * Example 3:
 * <p>
 * Input: image = [[5,6,7],[8,9,10],[11,12,13]], threshold = 1
 * Output: [[5,6,7],[8,9,10],[11,12,13]]
 * Explanation: There does not exist any region in image, hence result[i][j] == image[i][j] for all the pixels.
 */
public class FindGridRegionAverage {
    public static void main(String[] args) {

    }

    int m = 0, n = 0;
    int[][] result = null;
    int[][] count = null;
    public int[][] resultGrid(int[][] image, int threshold) {
        m = image.length; n = image[0].length;
        result = new int[m][n];
        count = new int[m][n];

        // Preprocessing: prefill the result array with MIN_VALUE
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                result[i][j] = Integer.MIN_VALUE;

        // For each possible grid of 3x3
        for(int col = 0; col <= n-3; col++) {
            for(int row = 0; row <= m-3; row++) {
                int currentAverage = calculateCurrentAverage(image, row, col, threshold);
                if(currentAverage != Integer.MIN_VALUE) // it means current grid is valid
                    updateAverage(image, row, col, currentAverage, threshold);
            }
        }

        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                if(result[i][j] != Integer.MIN_VALUE) // When VALID
                    result[i][j] = (result[i][j] / count[i][j]);
                else
                    result[i][j] = image[i][j]; // When INVALID: update with original value

        return result;
    }

    public void updateAverage(int[][] image, int row, int col, int currentAverage, int threshold) {
        for(int i = row; i < row+3; i++) {
            for(int j = col; j < col+3; j++) {
                if(result[i][j] == Integer.MIN_VALUE) {
                    result[i][j] = currentAverage;
                } else {
                    result[i][j] += currentAverage;
                }
                count[i][j]++;
            }
        }
    }

    public int calculateCurrentAverage(int[][] image, int row, int col, int threshold) {
        int tempSum = 0;
        for(int i = row; i < row+3; i++) {
            for(int j = col; j < col+3; j++) {
                // If the current position is invalid, the current grid is invalid too, so return MIN_VALUE as average
                if(!isCurrentPositionValid(image, i, j, threshold, row, col))
                    return Integer.MIN_VALUE;
                tempSum += image[i][j];
            }
        }
        return tempSum/9;
    }

    public boolean isCurrentPositionValid(int[][] image, int row, int col, int threshold, int startRow, int startCol) {
        if(row - 1 >= startRow && Math.abs(image[row-1][col] - image[row][col]) > threshold)
            return false;
        if(row + 1 <= startRow + 2 && Math.abs(image[row+1][col] - image[row][col]) > threshold)
            return false;
        if(col - 1 >= startCol && Math.abs(image[row][col-1] - image[row][col]) > threshold)
            return false;
        if(col + 1 <= startCol + 2 && Math.abs(image[row][col+1] - image[row][col]) > threshold)
            return false;
        return true;
    }
}
