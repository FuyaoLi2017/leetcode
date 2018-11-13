/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:

Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.
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
     public ListNode reverseKGroup(ListNode head, int k) {
         if (head == null || head.next == null || k < 2) return head;
         ListNode dummy = new ListNode(0);
         dummy.next = head;
         ListNode tail = dummy, prev = dummy, temp;
         int count;
         // move tail to the right place
         while (true) {
             count = k;
             while (count > 0 && tail != null) {
                 count--;
                 tail = tail.next;
             }
             if (tail == null) break; // has reached the end of the list

             head = prev.next; // assign a new value to head(the start of next k group)
             // prev-->temp-->...--->....--->tail-->....
             // Delete @temp and insert to the next position of @tail
             // prev-->...-->...-->tail-->head-->...
             // Assign @temp to the next node of @prev
             // prev-->temp-->...-->tail-->...-->...
             // Keep doing until @tail is the next node of @prev
             while (prev.next != tail) {
                 temp = prev.next; // assign a new value to temp
                 prev.next = temp.next; // delete the connection between temp and prev
                 temp.next = tail.next;
                 tail.next = temp;
             }
             // now, head is the last element of the first K element
             tail = head;
             prev = head;
         }
         return dummy.next;
     }
 }