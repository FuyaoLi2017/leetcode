/*
Given a list, rotate the list to the right by k places, where k is non-negative.

Input: 1->2->3->4->5->NULL, k = 2

Output: 4->5->1->2->3->NULL

Input: 1->2->3->4->5->NULL, k = 12

Output: 4->5->1->2->3->NULL
*/

/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */

 // my solution
public class Solution {
  public ListNode rotateKplace(ListNode head, int n) {
    if (head == null)
      return null;
    ListNode slow = head, fast = head;
    int count = 0;
    ListNode scan = head;
    while (scan != null) {
      scan = scan.next;
      count++;
    }
    n = n % count;
    if (n == 0) {
      return head;
    }
    while (n > 0 && fast != null) {
      fast = fast.next;
      n--;
    }
    // k is larger than the linked list's maximum index
    if (fast == null) {
      return head;
    }
    while (fast.next != null) {
      slow = slow.next;
      fast = fast.next;
    }
    ListNode newHead = slow.next;
    slow.next = null;
    fast.next = head;
    return newHead;
  }
}


// a better solution
// form a cycle
public ListNode rotateKplace(ListNode head, int k) {
   //Input your code here
   if (head == null || head.next == null) {
     return head;
   }
   ListNode dummy = head;
   int len = 1;
   while (head.next != null) {
     head = head.next;
     len++;
   }
   head.next = dummy;
   for (int i = 0; i < len - k % len - 1; i++) {
     dummy = dummy.next;
   }
   head = dummy.next;
   dummy.next = null;
   return head;
 }
