package com.example.leetcode.challenge.test2020.april.week2;

/**
 * Given a non-empty, singly linked list with head node head, return a middle node of linked list.
 *
 * If there are two middle nodes, return the second middle node.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3,4,5]
 * Output: Node 3 from this list (Serialization: [3,4,5])
 * The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
 * Note that we returned a ListNode object ans, such that:
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.
 * Example 2:
 *
 * Input: [1,2,3,4,5,6]
 * Output: Node 4 from this list (Serialization: [4,5,6])
 * Since the list has two middle nodes with values 3 and 4, we return the second one.
 *
 *
 * Note:
 *
 * The number of nodes in the given list will be between 1 and 100.
 */
public class MiddleLinkedList {
    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        MiddleLinkedList middleLinkedList = new MiddleLinkedList();
        ListNode result  = middleLinkedList.middleNodeV2(node1);
        System.out.println(result);
    }

    public ListNode middleNode(ListNode head) {

        int index = 0;
        ListNode temp = head;
        while((temp = temp.next) != null){
            index++;
        }
        int middleIndex = index/2 + index%2;
        index = 0;
        temp = head;
        ListNode result = head;
        while(temp != null){
            if((index++) == middleIndex){
                result = temp;
            }
            temp = temp.next;
        }
        return result;
    }


    /**
     * less memory version
     */
    public ListNode middleNodeV2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next !=null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }



}

