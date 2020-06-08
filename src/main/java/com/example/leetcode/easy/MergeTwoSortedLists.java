package com.example.leetcode.easy;

/**
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 *
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode l12 = new ListNode(2);
        ListNode l11 = new ListNode(2,l12);
        ListNode l1 = new ListNode(1,l11);
        ListNode l22 = new ListNode(1);
        ListNode l21 = new ListNode(3,l22);
        ListNode l2 = new ListNode(1,l21);
        MergeTwoSortedLists mergeTwoSortedLists = new MergeTwoSortedLists();
        mergeTwoSortedLists.mergeTwoLists(l12,l22);
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        if(p1 == null)
            return p2;
        if(p2 == null)
            return p1;
        if(p2.val < p1.val)
            return mergeTwoLists(p2,p1);
        ListNode pre = null;
        while (p1 != null){
            while (p2 != null && p2.val < p1.val){
                if(pre != null)
                    pre.next = p2;
                pre = p2;
                p2 = p2.next;
            }

            if(pre != null)
                pre.next = p1;
            pre = p1;
            p1 = p1.next;
        }
        while (p2 != null){
            if(pre != null)
                pre.next = p2;
            pre = p2;
            p2 = p2.next;
        }
        return l1;
    }

    /**
     * better solution
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoListsV2(ListNode l1, ListNode l2) {
        ListNode a = new ListNode(0);
        ListNode result = a;
        while (l1!=null && l2!=null) {
            if(l1.val<=l2.val){
                result.next = l1;
                l1 = l1.next;
            }else{
                result.next = l2;
                l2 = l2.next;
            }
            result = result.next;
        }
        if (l1!=null) {
            result.next=l1;
        }else if(l2!=null) {
            result.next=l2;
        }
        return a.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
