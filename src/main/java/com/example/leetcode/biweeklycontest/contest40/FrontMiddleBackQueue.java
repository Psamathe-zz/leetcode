package com.example.leetcode.biweeklycontest.contest40;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Design a queue that supports push and pop operations in the front, middle, and back.
 *
 * Implement the FrontMiddleBack class:
 *
 * FrontMiddleBack() Initializes the queue.
 * void pushFront(int val) Adds val to the front of the queue.
 * void pushMiddle(int val) Adds val to the middle of the queue.
 * void pushBack(int val) Adds val to the back of the queue.
 * int popFront() Removes the front element of the queue and returns it. If the queue is empty, return -1.
 * int popMiddle() Removes the middle element of the queue and returns it. If the queue is empty, return -1.
 * int popBack() Removes the back element of the queue and returns it. If the queue is empty, return -1.
 * Notice that when there are two middle position choices, the operation is performed on the frontmost middle position choice. For example:
 *
 * Pushing 6 into the middle of [1, 2, 3, 4, 5] results in [1, 2, 6, 3, 4, 5].
 * Popping the middle from [1, 2, 3, 4, 5, 6] returns 3 and results in [1, 2, 4, 5, 6].
 *
 *
 * Example 1:
 *
 * Input:
 * ["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
 * [[], [1], [2], [3], [4], [], [], [], [], []]
 * Output:
 * [null, null, null, null, null, 1, 3, 4, 2, -1]
 *
 * Explanation:
 * FrontMiddleBackQueue q = new FrontMiddleBackQueue();
 * q.pushFront(1);   // [1]
 * q.pushBack(2);    // [1, 2]
 * q.pushMiddle(3);  // [1, 3, 2]
 * q.pushMiddle(4);  // [1, 4, 3, 2]
 * q.popFront();     // return 1 -> [4, 3, 2]
 * q.popMiddle();    // return 3 -> [4, 2]
 * q.popMiddle();    // return 4 -> [2]
 * q.popBack();      // return 2 -> []
 * q.popFront();     // return -1 -> [] (The queue is empty)
 *
 *
 * Constraints:
 *
 * 1 <= val <= 109
 * At most 1000 calls will be made to pushFront, pushMiddle, pushBack, popFront, popMiddle, and popBack.
 */
public class FrontMiddleBackQueue {
    public static void main(String[] args) {
        FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
        obj.pushFront(1);
        obj.pushMiddle(2);
        obj.pushBack(3);
        int param_4 = obj.popFront();
        int param_5 = obj.popMiddle();
        int param_6 = obj.popBack();
    }

    /**
     * https://leetcode.com/submissions/detail/425510857/
     * https://blog.csdn.net/qq_46105170/article/details/110299282
     * 要求设计一个双端队列，除了要实现队首队尾添加删除元素之外，还需要实现从中间添加删除的操作。中间的定义是，如果整个队列有偶数个元素，则取靠近队头的那个中间元素。在中间添加元素时，如果元素个数奇数个，则在正中间元素之前添加元素。如果执行pop的时候队列空，则返回− 1 -1−1。
     *
     * 思路是用两个双端队列，一个存前半部分元素，一个存后半部分元素。同时，我们保持后半部分元素个数不少于前半部分，并且比前半部分最多多一个。这样在pushMiddle的时候永远都是向前队列队尾插入元素；popMiddle的时候要判断下两个队列size是否一样，如果一样则pop前队列队尾，否则pop后队列队头；这里需要注意，popFront的时候要看一下前队列是否为空，否则会NPE。代码如下：
     */
    private Deque<Integer> fDeq, rDeq;
    private int size;

    public FrontMiddleBackQueue() {
        fDeq = new ArrayDeque<>();
        rDeq = new ArrayDeque<>();
    }

    public void pushFront(int val) {
        fDeq.offerFirst(val);
        adjust();
        size++;
    }

    public void pushMiddle(int val) {
        fDeq.offerLast(val);
        adjust();
        size++;
    }

    public void pushBack(int val) {
        rDeq.offerLast(val);
        adjust();
        size++;
    }

    public int popFront() {
        if (size == 0) {
            return -1;
        }

        // 注意要判断一下前队列是否空
        int res = !fDeq.isEmpty() ? fDeq.pollFirst() : rDeq.pollFirst();
        adjust();
        size--;
        return res;
    }

    public int popMiddle() {
        if (size == 0) {
            return -1;
        }

        // 如果两个队列size一样，则pop前队列队尾，否则pop后队列队头
        int res = fDeq.size() == rDeq.size() ? fDeq.pollLast() : rDeq.pollFirst();
        adjust();
        size--;
        return res;
    }

    public int popBack() {
        if (size == 0) {
            return -1;
        }

        int res = rDeq.pollLast();
        adjust();
        size--;
        return res;
    }

    // 用于调整两个队列的size，保持后半始终不少于前半，并且多不超过1个
    private void adjust() {
        if (rDeq.size() - fDeq.size() >= 2) {
            fDeq.offerLast(rDeq.pollFirst());
        }
        if (rDeq.size() < fDeq.size()) {
            rDeq.offerFirst(fDeq.pollLast());
        }
    }
}
