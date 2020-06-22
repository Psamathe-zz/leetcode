package com.example.leetcode.hard;

import java.util.*;

/**
 * You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.
 *
 * We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.
 *
 *
 *
 * Example 1:
 *
 * Input: [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * Output: [20,24]
 * Explanation:
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 *
 *
 * Note:
 *
 * The given list may contain duplicates, so ascending order means >= here.
 * 1 <= k <= 3500
 * -105 <= value of elements <= 105.
 *
 * [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
 */
public class SmallestRangeCoveringElementsfromKLists {

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(4,10,15,24,26));
        list.add(Arrays.asList(0,9,12,20));
        list.add(Arrays.asList(5,18,22,30));


        SmallestRangeCoveringElementsfromKLists smallestRangeCoveringElementsfromKLists = new SmallestRangeCoveringElementsfromKLists();
        int[] result = smallestRangeCoveringElementsfromKLists.smallestRange(list);
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        int res[]=new int[2];
        Queue<Node> queue = new PriorityQueue<>( (a,b) ->{
            return a.getValue() - b.getValue();
        });

        int queueindex = 0;
        for(List<Integer> list: nums){
            Node node = new Node(list.get(0),queueindex,0);
            queue.add(node);
            min = Math.min(min,node.getValue());
            max = Math.max(max,node.getValue());
            queueindex++;
        }
        res[0]=min;res[1]=max;
        while (true){
            Node node = queue.poll();
            queueindex = node.getQueue();
            int index = node.getIndex();
            if( index == nums.get(queueindex).size()-1)
                break;
            Node next = new Node(nums.get(queueindex).get(index + 1),queueindex,index + 1);
            max = Math.max(max,next.getValue());
            queue.add(next);
            if(res[1]-res[0]>max-queue.peek().getValue()){
                res[0]=queue.peek().getValue();
                res[1]=max;
            }

        }

        return res;
    }

    public class Node{
        int value;
        int queue;
        int index;
        Node(int value,int queue,int index){
            this.value = value;
            this.queue = queue;
            this.index = index;
        }

        public int getValue() {
            return value;
        }

        public int getIndex() {
            return index;
        }

        public int getQueue() {
            return queue;
        }
    }
}
