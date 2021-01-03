package com.example.leetcode.challenge.Test2020.June.week1;

import java.util.*;

/**
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k),
 * where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h.
 * Write an algorithm to reconstruct the queue.
 *
 * Note:
 * The number of people is less than 1,100.
 *
 *
 * Example
 *
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 */
public class QueueReconstructionByHeight {
    public static void main(String[] args) {
        int[][] people = new int[][]{
                {7,0},
                {4,4},
                {7,1},
                {5,0},
                {6,1},
                {5,2}
        };
        QueueReconstructionByHeight queueReconstructionByHeight = new QueueReconstructionByHeight();
        int[][] result = queueReconstructionByHeight.reconstructQueue(people);
        result.toString();
    }

    /**
     * faster solution
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        quickSort(people, 0, people.length-1);
        ArrayList<int[]> res = new ArrayList<>();
        for(int[] person : people) {
            res.add(person[1], person);
        }

        int i = 0;
        for(int[] r : res) {
            people[i++] = r;
        }

        return people;
    }

    private void quickSort(int[][] people, int left, int right) {
        if (left >= right)
            return;
        int[] pivot = people[left];
        int i = left;
        int j = right;
        while (i < j) {
            while ((people[j][0] < pivot[0] || (people[j][0] == pivot[0] && people[j][1] >= pivot[1])) && i < j) {
                j--;
            }
            while ((people[i][0] > pivot[0] || (people[i][0] == pivot[0] && people[i][1] <= pivot[1])) && i < j) {
                i++;
            }
            if (i < j) {
                int[] tmp = people[i];
                people[i] = people[j];
                people[j] = tmp;
            }
        }
        // Swap pivot with left(start)
        people[left] = people[i];
        people[i] = pivot;
        quickSort(people, left, i - 1);
        quickSort(people, i + 1, right);
    }

    public int[][] reconstructQueueV0(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0]){
                    return o2[0] - o1[0];
                } else{
                    return o1[1] - o2[1];
                }
            }
        });
        int[] t;
        for (int i = 1; i < people.length; i++) {
            int cnt = 0;
            for (int j = 0; j < i; ++j) {
                if (cnt == people[i][1]) {
                    t = people[i];
                    for (int k = i - 1; k >= j; --k) {
                        people[k + 1] = people[k];
                    }
                    people[j] = t;
                    break;
                }
                ++cnt;
            }
        }

        return people;
    }
    public int[][] reconstructQueueOld(int[][] people) {
        int[][] res = new int[people.length][2];
        List<People> list = new ArrayList<>();
        for(int[] one : people){
            list.add( new People(one[0],one[1]));
        }
        Collections.sort(list);

        List<People> result = new LinkedList<>();

        for(People peopleT:list){
            result.add(peopleT.before,peopleT);
        }
        int index = 0;
        for (People people1 : result){
            res[index][0] = people1.height;
            res[index][1] = people1.before;
            index++;
        }
        return res;
    }

    public class People implements Comparable<People> {
        int height;
        int before;

        public People(int height, int before) {
            this.height = height;
            this.before = before;
        }


        @Override
        public int compareTo(People o) {
            if(height != o.height){
                return o.height - height;
            } else {
                return before - o.before;
            }
        }
    }

}
