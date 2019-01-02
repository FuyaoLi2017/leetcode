/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

Input: 1->1->2

Output: 1->2
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
 // we just need to keep 2 pointers
public class Solution {
  public ListNode removeDup(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode prev = head;
    ListNode cur = head.next;
    while (cur != null) {
      if (prev.value != cur.value) {
        prev = prev.next;
        cur = cur.next;
      } else {
        if (cur.next == null) {
          prev.next = null;
          return head;
        }
        cur = cur.next;
        prev.next = cur;
      }
    }
    return head;
  }
}
