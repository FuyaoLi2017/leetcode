/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:

Input: 1->1->2
Output: 1->2
Example 2:

Input: 1->1->2->3->3
Output: 1->2->3
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// iterative
    public ListNode deleteDuplicates(ListNode head) {
        ListNode list = head;

         while(list != null) {
        	 if (list.next == null) {
        		 break;
        	 }
        	 if (list.val == list.next.val) {
        		 list.next = list.next.next;
        	 } else {
        		 list = list.next;
        	 }
         }

         return head;
    }

// recursive
public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)return head;
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
}

// my solution, iterative, June 24, 2019
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = head;
        ListNode cursor = head.next;
        while (cursor != null) {
            if (cursor.val == prev.val) {
                prev.next = cursor.next;
                cursor = cursor.next;
            } else {
                prev = prev.next;
                cursor = cursor.next;
            }
        }
        return head;
    }
}
