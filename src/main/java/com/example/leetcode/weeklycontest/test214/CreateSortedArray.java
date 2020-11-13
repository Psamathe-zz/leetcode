package com.example.leetcode.weeklycontest.test214;


import com.example.leetcode.algo.Treap;

public class CreateSortedArray {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/create-sorted-array-through-instructions/solution/javaji-yu-treapping-heng-er-cha-shu-by-onion12138/
     * @param instructions
     * @return
     */
    public int createSortedArray(int[] instructions) {
        Treap treap = new Treap();
        long price = 0;
        for(int i : instructions) {
            treap.insert(i);
            int[] rank = treap.rank(i);
            price = (price + Math.min(rank[0] - 1, treap.getSize() - rank[1])) % 10000_00007;
        }
        return (int)price;
    }

}
