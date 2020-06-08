package com.example.leetcode.easy;

public class ConvertBinaryNumberLinkedListInteger {

    public static void main(String[] args) {

        ListNode listNode2 = new ListNode(1);
        ListNode listNode1 = new ListNode(0,listNode2);
        ListNode listNode0 = new ListNode(1,listNode1);
        ConvertBinaryNumberLinkedListInteger convertBinaryNumberLinkedListInteger = new ConvertBinaryNumberLinkedListInteger();
        int result = convertBinaryNumberLinkedListInteger.getDecimalValue(listNode0);
        System.out.println(result);
    }

    public int getDecimalValue(ListNode head) {
        int result = 0;

        while (head != null){
            int temp = result <<1;
            result = temp + head.val;
            head = head.next;
        }
        return result;
    }

      public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

}
