/*
Given a linked list, remove the nth node from the end of list and return its head.

Assumptions
If n is not valid, you do not need to do anything to the original list.
Try to do this in one pass.

Examples

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
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
 // 可以handle input不是valid的情况
public class Solution {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    // use two pointers
    if (head == null) {
      return head;
    }
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode slow = dummy;
    ListNode fast = dummy;
    while (n > 0 && fast.next != null) {
      fast = fast.next;
      n--;
    }
    if (n > 0) {
      // the nth node from the end is not valid
      return head;
    }
    while (fast.next != null) {
      fast = fast.next;
      slow = slow.next;
    }
    // cut the node
    slow.next = slow.next.next;
    return dummy.next;
  }
}
