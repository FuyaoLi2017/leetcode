/*
Reverse pairs of elements in a singly-linked list.

Examples

L = null, after reverse is null
L = 1 -> null, after reverse is 1 -> null
L = 1 -> 2 -> null, after reverse is 2 -> 1 -> null
L = 1 -> 2 -> 3 -> null, after reverse is 2 -> 1 -> 3 -> null

*/

// recusive method
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
 public class Solution {
   public ListNode reverseInPairs(ListNode head) {
     if (head == null || head.next == null) {
       return head;
     }
     // initialze the newHead position
     ListNode newHead = head.next;
     // set up the direction the cur head points to
     // (the second node in a pair after reverse)
     head.next = reverseInPairs(head.next.next);
     // reverse the point direction between head and head.next
     newHead.next = head;
     return newHead;
   }
 }

// interative method
public class Solution {
  public ListNode reverseInPairs(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode cur = dummy;
    while (cur.next != null && cur.next.next != null) {
      // get the second point in the pair
      ListNode next = cur.next.next;
      // define the node after cur.next
      cur.next.next = cur.next.next.next;
      // link next to cur.next
      next.next = cur.next;
      // link previous point to next
      cur.next = next;
      // advance the cur point
      cur = cur.next.next;
    }
    return dummy.next;
  }
}
