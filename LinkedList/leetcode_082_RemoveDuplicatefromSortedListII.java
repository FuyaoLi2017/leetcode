/*
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Example 1:

Input: 1->2->3->3->4->4->5
Output: 1->2->5
Example 2:

Input: 1->1->1->2->3
Output: 2->3
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 class Solution {
     public ListNode deleteDuplicates(ListNode head) {
         if (head == null) return null;
         ListNode dummy = new ListNode(0);
         dummy.next = head;
         ListNode prev = dummy;
         ListNode cur = head;
         while (cur != null) {
             // use a while loop to skip all same values for current
             while (cur.next != null && cur.val == cur.next.val) {
                 cur = cur.next;
             }
             // there is only one element without duplicate,
             // the code didn't enter the while loop above
             // can move pre to pre.next
             if (prev.next == cur) {
                 prev = prev.next;
             }
             // else, point the NEXT!! pointer of pre to cur.next
             else {
                 prev.next = cur.next;
             }
             // advance the cur pointer after each operation
             cur = cur.next;
         }
         return dummy.next;
     }
 }
