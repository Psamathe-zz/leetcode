package com.example.leetcode.biweeklycontest.old.contest42;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers 0 and 1 respectively. All students stand in a queue. Each student either prefers square or circular sandwiches.
 *
 * The number of sandwiches in the cafeteria is equal to the number of students. The sandwiches are placed in a stack. At each step:
 *
 * If the student at the front of the queue prefers the sandwich on the top of the stack, they will take it and leave the queue.
 * Otherwise, they will leave it and go to the queue's end.
 * This continues until none of the queue students want to take the top sandwich and are thus unable to eat.
 *
 * You are given two integer arrays students and sandwiches where sandwiches[i] is the type of the i​​​​​​th sandwich in the stack (i = 0 is the top of the stack) and students[j] is the preference of the j​​​​​​th student in the initial queue (j = 0 is the front of the queue). Return the number of students that are unable to eat.
 *
 *
 *
 * Example 1:
 *
 * Input: students = [1,1,0,0], sandwiches = [0,1,0,1]
 * Output: 0
 * Explanation:
 * - Front student leaves the top sandwich and returns to the end of the line making students = [1,0,0,1].
 * - Front student leaves the top sandwich and returns to the end of the line making students = [0,0,1,1].
 * - Front student takes the top sandwich and leaves the line making students = [0,1,1] and sandwiches = [1,0,1].
 * - Front student leaves the top sandwich and returns to the end of the line making students = [1,1,0].
 * - Front student takes the top sandwich and leaves the line making students = [1,0] and sandwiches = [0,1].
 * - Front student leaves the top sandwich and returns to the end of the line making students = [0,1].
 * - Front student takes the top sandwich and leaves the line making students = [1] and sandwiches = [1].
 * - Front student takes the top sandwich and leaves the line making students = [] and sandwiches = [].
 * Hence all students are able to eat.
 * Example 2:
 *
 * Input: students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= students.length, sandwiches.length <= 100
 * students.length == sandwiches.length
 * sandwiches[i] is 0 or 1.
 * students[i] is 0 or 1.
 */
public class NumberStudentsUnableEatLunch {
    public static void main(String[] args) {
        int[] students = new int[]{1,1,0,0};
        int[] sandwiches = new int[]{0,1,0,1};
        NumberStudentsUnableEatLunch numberStudentsUnableEatLunch = new NumberStudentsUnableEatLunch();
        int res = numberStudentsUnableEatLunch.countStudents(students, sandwiches );
        System.out.println(res);
    }

    public int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> queue1 = new LinkedList<>();
        for (int s : students){
            queue1.offer(s);
        }
        Queue<Integer> queue2 = new LinkedList<>();
        for(int s: sandwiches){
            queue2.offer(s);
        }
        int sandwich;
        int student;
        int size;
        while (!queue2.isEmpty()){
            sandwich = queue2.peek();
            size = queue1.size();
            while ( (student = queue1.poll()) != sandwich){
                queue1.offer(student);
                size--;
                if(size == 0)
                    break;
            }
            if(student != sandwich)
                break;
            queue2.poll();
        }
        return queue1.size();
    }
}
