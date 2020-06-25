package com.example.leetcode.easy;

import java.util.*;

/**
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 *
 * Note that the row index starts from 0.
 *
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 *
 * Input: 3
 * Output: [1,3,3,1]
 * Follow up:
 *
 * Could you optimize your algorithm to use only O(k) extra space?
 */
public class PascalTriangleII {
    public static void main(String[] args) {
        PascalTriangleII pascalTriangleII = new PascalTriangleII();
        pascalTriangleII.getRow(1);
    }

    public List<Integer> getRow(int rowIndex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        if(rowIndex == 0)
            return new ArrayList<>(queue);
        while (rowIndex >= 1){
            int size = queue.size();
            int pre = 0;
            int index = 1;
            int current;
            while (index <= size){
                current = queue.poll();
                queue.add(current + pre);
                pre = current;
                index++;
            }
            queue.add(pre);

            rowIndex--;
        }
        return  new ArrayList<>(queue);
    }


    /**
     * faster solution
     * @param rowIndex
     * @return
     */
    public List<Integer> getRowV1(int rowIndex) {
        Integer[] arr=new Integer[rowIndex+1];
        int prev=0;
        for(int line=0;line<=rowIndex;line++){
            for(int i=0;i<=line;i++){
                if(i==0||i==line){
                    arr[i]=1;
                    prev=1;
                }else{
                    int temp=arr[i];
                    arr[i]=arr[i]+prev;
                    prev=temp;

                }
            }
        }
        return Arrays.asList(arr);
    }
}
