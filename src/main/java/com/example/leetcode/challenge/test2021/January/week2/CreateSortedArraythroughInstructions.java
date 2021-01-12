package com.example.leetcode.challenge.test2021.January.week2;


import com.example.leetcode.algo.Treap;

import java.util.*;

/**
 * Given an integer array instructions, you are asked to create a sorted array from the elements in instructions. You start with an empty container nums. For each element from left to right in instructions, insert it into nums. The cost of each insertion is the minimum of the following:
 *
 * The number of elements currently in nums that are strictly less than instructions[i].
 * The number of elements currently in nums that are strictly greater than instructions[i].
 * For example, if inserting element 3 into nums = [1,2,3,5], the cost of insertion is min(2, 1) (elements 1 and 2 are less than 3, element 5 is greater than 3) and nums will become [1,2,3,3,5].
 *
 * Return the total cost to insert all elements from instructions into nums. Since the answer may be large, return it modulo 109 + 7
 *
 *
 *
 * Example 1:
 *
 * Input: instructions = [1,5,6,2]
 * Output: 1
 * Explanation: Begin with nums = [].
 * Insert 1 with cost min(0, 0) = 0, now nums = [1].
 * Insert 5 with cost min(1, 0) = 0, now nums = [1,5].
 * Insert 6 with cost min(2, 0) = 0, now nums = [1,5,6].
 * Insert 2 with cost min(1, 2) = 1, now nums = [1,2,5,6].
 * The total cost is 0 + 0 + 0 + 1 = 1.
 * Example 2:
 *
 * Input: instructions = [1,2,3,6,5,4]
 * Output: 3
 * Explanation: Begin with nums = [].
 * Insert 1 with cost min(0, 0) = 0, now nums = [1].
 * Insert 2 with cost min(1, 0) = 0, now nums = [1,2].
 * Insert 3 with cost min(2, 0) = 0, now nums = [1,2,3].
 * Insert 6 with cost min(3, 0) = 0, now nums = [1,2,3,6].
 * Insert 5 with cost min(3, 1) = 1, now nums = [1,2,3,5,6].
 * Insert 4 with cost min(3, 2) = 2, now nums = [1,2,3,4,5,6].
 * The total cost is 0 + 0 + 0 + 0 + 1 + 2 = 3.
 * Example 3:
 *
 * Input: instructions = [1,3,3,3,2,4,2,1,2]
 * Output: 4
 * Explanation: Begin with nums = [].
 * Insert 1 with cost min(0, 0) = 0, now nums = [1].
 * Insert 3 with cost min(1, 0) = 0, now nums = [1,3].
 * Insert 3 with cost min(1, 0) = 0, now nums = [1,3,3].
 * Insert 3 with cost min(1, 0) = 0, now nums = [1,3,3,3].
 * Insert 2 with cost min(1, 3) = 1, now nums = [1,2,3,3,3].
 * Insert 4 with cost min(5, 0) = 0, now nums = [1,2,3,3,3,4].
 * ​​​​​​​Insert 2 with cost min(1, 4) = 1, now nums = [1,2,2,3,3,3,4].
 * ​​​​​​​Insert 1 with cost min(0, 6) = 0, now nums = [1,1,2,2,3,3,3,4].
 * ​​​​​​​Insert 2 with cost min(2, 4) = 2, now nums = [1,1,2,2,2,3,3,3,4].
 * The total cost is 0 + 0 + 0 + 0 + 1 + 0 + 1 + 0 + 2 = 4.
 */
public class CreateSortedArraythroughInstructions {
    public static void main(String[] args) {
        int[] ins = new int[]{1,3,3,3,2,4,2,1,2};
        CreateSortedArraythroughInstructions createSortedArraythroughInstructions = new CreateSortedArraythroughInstructions();
        createSortedArraythroughInstructions.createSortedArrayV3(ins);
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


    /**
     * https://leetcode-cn.com/problems/create-sorted-array-through-instructions/solution/java-shu-zhuang-shu-zu-by-ppppjqute-su9o/
     */
    //使用树状数组，每次求小于他的值与大于他的值中的较小值
    class arrTrie{
        int [] arr;
        int n;
        public arrTrie(int n){
            this.n = n;
            arr = new int [n];
        }
        public int lowbit(int i){
            int x = i&(-i);
            return x;
        }
        public int query(int x){
            int ans = 0;
            for(int i=x;i>0;i-=lowbit(i)){
                ans+=arr[i];
            }
            return ans;
        }
        public void update(int x,int add){
            for(int i=x;i<n;i+=lowbit(i)){
                arr[i]+=add;
            }
        }
    }
    public int createSortedArrayV1(int[] instructions) {
        //先进行离散化
        Map<Integer,Integer> map = new HashMap<>();
        TreeSet<Integer> set = new TreeSet<>();
        for(int i:instructions){
            set.add(i);
        }
        int index = 1;
        while(!set.isEmpty()){
            map.put(set.pollFirst(),index++);
        }
        arrTrie tree = new arrTrie(map.size()+1);
        int ans = 0;
        for(int i=0;i<instructions.length;i++){
            int l = map.get(instructions[i]);
            int less = tree.query(l-1);
            int more = i-tree.query(l);//比他大的元素
            int temp = Math.min(less,more);
            ans = ans%1000000007+temp%1000000007;
            tree.update(l, 1);
        }
        return ans;

    }

    /**
     * https://leetcode-cn.com/problems/create-sorted-array-through-instructions/solution/tong-guo-zhi-ling-chuang-jian-you-xu-shu-zu-mo-ban/
     */
    List<Integer> ys = new ArrayList();   //离散化数组
    int[] tr;    //树状数组
    int N;
    int mod = (int) 1e9 + 7;
    public int createSortedArrayV2(int[] a) {
        //把所有需要用到的数值加入离散化数组
        for(int i: a) {
            ys.add(i - 1);
            ys.add(i);
        }
        //离散化常规套路： 排序，去重
        Collections.sort(ys);
        ys = unique(ys);
        N = ys.size();

        tr = new int[N + 1];
        int ans = 0;
        for(int i: a){
            int sm = query(get(ys, i - 1));  //前缀和思想
            int big = query(N) - query(get(ys, i));  //前缀和思想
            ans = (ans + Math.min(sm, big)) % mod;
            add(get(ys, i), 1);
        }
        return ans;
    }


    // ---------------------------------下面是树状数组模板-------------------------------------------
    public void add(int x, int c){
        for(int i = x; i <= N; i += lowbit(i)){
            tr[i] += c;
        }
    }

    public int query(int x){
        int res = 0;
        for(int i = x; i > 0; i -= lowbit(i)){
            res += tr[i];
        }
        return res;
    }

    public int lowbit(int x){
        return x & -x;
    }
    // ---------------------------------上面是树状数组模板-------------------------------------------


    // ---------------------------------下面是离散化模板---------------------------------------------
    public int get(List<Integer> list, int t){
        int l = 0, r = list.size() - 1;
        while(l < r){
            int mid = l + r >> 1;
            if(list.get(mid) >= t) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    public List<Integer> unique(List<Integer> list){
        List<Integer> res = new ArrayList();
        for(int i: list){
            if(res.isEmpty() || res.get(res.size() - 1) != i) res.add(i);
        }
        return res;
    }
    // ---------------------------------上面是离散化模板-------------------------------------------


    /**
     * faster solution
     */
    int[] c;
    public int createSortedArrayV3(int[] A) {
        int res = 0, n = A.length, mod = (int)1e9 + 7;
        c = new int[100001];
        for (int i = 0; i < n; ++i) {
            res = (res + Math.min(get(A[i] - 1), i - get(A[i]))) % mod;
            update(A[i]);
            System.out.println(c);
        }
        return res;
    }

    public void update(int x) {
        while (x < 100001) {
            c[x]++;
            x += x & -x;
        }
    }

    public int get(int x) {
        int res = 0;
        while (x > 0) {
            res += c[x];
            int t = x & -x;
            x -= x & -x;
        }
        return res;
    }
}
