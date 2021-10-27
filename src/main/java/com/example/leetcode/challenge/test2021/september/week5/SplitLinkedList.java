package com.example.leetcode.challenge.test2021.september.week5;

import com.example.leetcode.model.ListNode;

/**
 *Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.
 *
 * The length of each part should be as equal as possible: no two parts should have a size differing by more than one. This may lead to some parts being null.
 *
 * The parts should be in the order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal to parts occurring later.
 *
 * Return an array of the k parts.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3], k = 5
 * Output: [[1],[2],[3],[],[]]
 * Explanation:
 * The first element output[0] has output[0].val = 1, output[0].next = null.
 * The last element output[4] is null, but its string representation as a ListNode is [].
 * Example 2:
 *
 *
 * Input: head = [1,2,3,4,5,6,7,8,9,10], k = 3
 * Output: [[1,2,3,4],[5,6,7],[8,9,10]]
 * Explanation:
 * The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.
 *
 *
 * Constraints:
 */
public class SplitLinkedList {
    public static void main(String[] args) {

    }

    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] res = new ListNode[k];
        ListNode[] pRes = new ListNode[k];
        int[] count = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = new ListNode();
            pRes[i] = res[i];
        }
        ListNode p = head;
        int index = 0;
        int v;
        while (null != p){
            v = index % k;
            count[v]++;
            index++;
            p = p.next;
        }
        int cur = 0;
        p = head;
        while (null != p){

            pRes[cur].next = p;
            pRes[cur] = pRes[cur].next;
            count[cur]--;

            p = p.next;
            if(count[cur] == 0) {
                pRes[cur].next = null;
                cur++;
            }
        }
        for (int i = 0; i < k; i++) {
            res[i] = res[i].next;
        }
        return res;
    }
}
