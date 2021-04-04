package com.example.leetcode.challenge.test2021.april.week1;


/**
 * Given the head of a singly linked list, return true if it is a palindrome.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,2,1]
 * Output: true
 * Example 2:
 *
 *
 * Input: head = [1,2]
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [1, 105].
 * 0 <= Node.val <= 9
 *
 *
 * Follow up: Could you do it in O(n) time and O(1) space?
 */
public class PalindromeLinkedList {
    public static void main(String[] args) {

    }

    public boolean isPalindrome(ListNode head) {
        StringBuilder stringBuilder = new StringBuilder();
        ListNode p = head;
        while (p!=null){
            stringBuilder.append(p.val);
            p = p.next;
        }
        return stringBuilder.toString().equals(stringBuilder.reverse().toString());
    }

    public boolean isPalindromeV0(ListNode head) {
        if (head == null || head.next == null) return true;

        ListNode newHead = null;
        ListNode fast = head;

        while (fast != null) {
            if (fast.next == null) {
                head = head.next;
                break;
            } else {
                fast = fast.next.next;
            }

            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }

        while (newHead != null) {
            if (newHead.val != head.val) return false;
            newHead = newHead.next;
            head = head.next;
        }

        return true;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
